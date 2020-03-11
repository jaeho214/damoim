package com.yeongjae.damoim.domain.reply.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.yeongjae.damoim.domain.reply.dto.ReplyCreateDto;
import com.yeongjae.damoim.domain.reply.dto.ReplyUpdateDto;
import com.yeongjae.damoim.domain.reply.entity.Reply;
import com.yeongjae.damoim.domain.reply.service.ReplyCreateService;
import com.yeongjae.damoim.domain.reply.service.ReplyDeleteService;
import com.yeongjae.damoim.domain.reply.service.ReplyUpdateService;
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

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ReplyControllerTest {
    @InjectMocks
    private ReplyController replyController;
    @Mock
    private ReplyCreateService replyCreateService;
    @Mock
    private ReplyUpdateService replyUpdateService;
    @Mock
    private ReplyDeleteService replyDeleteService;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(replyController)
                .alwaysDo(print()).build();
    }

    private Reply replyFixture = new EasyRandom().nextObject(Reply.class);
    private ReplyCreateDto replyCreateDtoFixture = new EasyRandom().nextObject(ReplyCreateDto.class);
    private ReplyUpdateDto replyUpdateDtoFixture = new EasyRandom().nextObject(ReplyUpdateDto.class);


    @Test
    void createReply() throws Exception {
        given(replyCreateService.createReply(anyString(), any(ReplyCreateDto.class))).willReturn(replyFixture);

        mockMvc.perform(
                post("/damoim/reply")
                .contentType(MediaType.APPLICATION_JSON)
                .header("token", "token")
                .content(
                        objectMapper.writeValueAsString(replyCreateDtoFixture)
                )
        )
                .andExpect(status().isCreated());
    }

    @Test
    void updateReply() throws Exception {
        given(replyUpdateService.updateReply(anyString(),any(ReplyUpdateDto.class))).willReturn(replyFixture);

        mockMvc.perform(
                put("/damoim/reply")
                .contentType(MediaType.APPLICATION_JSON)
                .header("token", "token")
                .content(
                        objectMapper.writeValueAsString(replyUpdateDtoFixture)
                )
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value(replyUpdateDtoFixture.getContent()));
    }

    @Test
    void deleteReply() throws Exception{
        Long reply_id = 1L;
        given(replyDeleteService.deleteReply(anyString(), anyLong())).willReturn(ResponseEntity.noContent().build());

        mockMvc.perform(
                delete("/damoim/reply/{id}", reply_id)
                .header("token", "token")
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isNoContent());
    }
}