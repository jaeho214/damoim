package com.yeongjae.damoim.domain.board.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.yeongjae.damoim.domain.board.dto.BoardCreateDto;
import com.yeongjae.damoim.domain.board.dto.BoardUpdateDto;
import com.yeongjae.damoim.domain.board.entity.Board;
import com.yeongjae.damoim.domain.board.service.BoardCreateService;
import com.yeongjae.damoim.domain.board.service.BoardGetService;
import com.yeongjae.damoim.domain.board.service.BoardUpdateService;
import com.yeongjae.damoim.domain.member.entity.Member;
import com.yeongjae.damoim.domain.reply.entity.Reply;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMapOf;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class BoardControllerTest {

    @InjectMocks
    private BoardController boardController;

    @Mock
    private BoardCreateService boardCreateService;
    @Mock
    private BoardGetService boardGetService;
    @Mock
    private BoardUpdateService boardUpdateService;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    private BoardCreateDto boardCreateDto = new EasyRandom().nextObject(BoardCreateDto.class);
    private BoardUpdateDto boardUpdateDto = new EasyRandom().nextObject(BoardUpdateDto.class);
    private Board boardFixture = new EasyRandom().nextObject(Board.class);
    private Member memberFixture = new EasyRandom().nextObject(Member.class);
    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(boardController)
                .alwaysDo(print()).build();
    }

    @Test
    void getBoards() throws Exception {
        Board board = boardCreateDto.of(memberFixture);
        String location = "location";
        given(boardGetService.getBoards(any(Integer.class), any(String.class))).willReturn(Arrays.asList(board));

        mockMvc.perform(
                get("/damoim/board/list/{location}",location)
                .contentType(MediaType.APPLICATION_JSON)
                .param("pageNo", String.valueOf(1))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(board.getTitle()));
    }

    @Test
    void getBoard() throws Exception {
        Long board_id = 1L;
        Reply reply = new EasyRandom().nextObject(Reply.class);
        reply.setBoard(boardFixture);
        boardFixture.setReplyList(Arrays.asList(reply));
        given(boardGetService.getBoard(board_id)).willReturn(boardFixture);

        mockMvc.perform(
                get("/damoim/board/{id}", board_id)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(boardFixture.getTitle()));
    }

    @Test
    void createBoard() throws Exception{
        Board board = boardCreateDto.of(memberFixture);
        given(boardCreateService.createBoard(any(String.class), any(BoardCreateDto.class))).willReturn(board);

        mockMvc.perform(
                post("/damoim/board")
                .contentType(MediaType.APPLICATION_JSON)
                .header("token", "token")
                .content(
                        objectMapper.writeValueAsString(boardCreateDto)
                )
        )
                .andExpect(status().isCreated());
    }

    @Test
    void updateBoard() throws Exception{
        given(boardUpdateService.updateBoard(any(String.class), any(BoardUpdateDto.class))).willReturn(boardFixture);

        mockMvc.perform(
                put("/damoim/board")
                .contentType(MediaType.APPLICATION_JSON)
                        .header("token", "token")
                .content(
                        objectMapper.writeValueAsString(boardUpdateDto)
                )
        )
                .andExpect(status().isOk());
    }

//    @Test
//    void deleteBoard() {
//    }
}