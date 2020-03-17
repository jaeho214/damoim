package com.yeongjae.damoim.domain.board.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.yeongjae.damoim.domain.board.dto.*;
import com.yeongjae.damoim.domain.board.entity.Board;
import com.yeongjae.damoim.domain.board.service.BoardCreateService;
import com.yeongjae.damoim.domain.board.service.BoardDeleteService;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.*;
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
    @Mock
    private BoardDeleteService boardDeleteService;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    private BoardCreateDto boardCreateDto = new EasyRandom().nextObject(BoardCreateDto.class);
    private BoardUpdateDto boardUpdateDto = new EasyRandom().nextObject(BoardUpdateDto.class);
    private BoardGetPagingDto boardGetPagingDto = new EasyRandom().nextObject(BoardGetPagingDto.class);
    private BoardGetDto boardGetDtoFixture = new EasyRandom().nextObject(BoardGetDto.class);
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
        given(boardGetService.getBoards(any(String.class), any(Integer.class), any(String.class))).willReturn(boardGetPagingDto);

        mockMvc.perform(
                get("/damoim/board/list/{location}",location)
                .header("token", "token")
                .contentType(MediaType.APPLICATION_JSON)
                .param("pageNo", String.valueOf(1))
        )
                .andExpect(status().isOk());
    }

    @Test
    void getBoard() throws Exception {
        Long board_id = 1L;
//        Reply reply = new EasyRandom().nextObject(Reply.class);
//        reply.setBoard(boardFixture);
//        boardFixture.setReplyList(Arrays.asList(reply));
        given(boardGetService.getBoard(any(String.class), any(Long.class))).willReturn(boardGetDtoFixture);

        mockMvc.perform(
                get("/damoim/board/{id}", board_id)
                .contentType(MediaType.APPLICATION_JSON)
                .header("token", "token")
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(boardFixture.getTitle()));
    }

    @Test
    void getBoardByMember() throws Exception{
        given(boardGetService.getBoardByMember(anyString(), anyInt())).willReturn(boardGetPagingDto);

        mockMvc.perform(
                get("/damoim/board/user")
                .contentType(MediaType.APPLICATION_JSON)
                .header("token", "token")
                .param("pageNo", String.valueOf(1))
        )
                .andExpect(status().isOk());
    }

    @Test
    void createBoard() throws Exception{
        Board board = boardCreateDto.of(memberFixture);
        given(boardCreateService.createBoard(any(String.class), any(BoardCreateDto.class))).willReturn(boardGetDtoFixture);

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
        given(boardUpdateService.updateBoard(any(String.class), any(BoardUpdateDto.class))).willReturn(boardGetDtoFixture);

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

    @Test
    void deleteBoard() throws Exception {
        Long board_id = 1L;
        given(boardDeleteService.deleteBoard(anyString(), anyLong())).willReturn(ResponseEntity.noContent().build());

        mockMvc.perform(
                delete("/damoim/board/{id}", board_id)
                .contentType(MediaType.APPLICATION_JSON)
                .header("token", "token")
        )
                .andExpect(status().isNoContent());
    }
}