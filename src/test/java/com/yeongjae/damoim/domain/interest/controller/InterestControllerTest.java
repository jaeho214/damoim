package com.yeongjae.damoim.domain.interest.controller;

import com.yeongjae.damoim.domain.interest.entity.Interest;
import com.yeongjae.damoim.domain.interest.service.InterestCreateService;
import com.yeongjae.damoim.domain.interest.service.InterestDeleteService;
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

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class InterestControllerTest {

    @InjectMocks
    private InterestController interestController;
    @Mock
    private InterestCreateService interestCreateService;
    @Mock
    private InterestDeleteService interestDeleteService;

    private Interest interestFixture = new EasyRandom().nextObject(Interest.class);
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(interestController)
                .alwaysDo(print()).build();
    }

    @Test
    void createInterest() throws Exception {
        Long deal_id = 1L;
        given(interestCreateService.createInterest(anyString(), anyLong())).willReturn(interestFixture);

        mockMvc.perform(
                post("/damoim/interest/{id}", deal_id)
                .contentType(MediaType.APPLICATION_JSON)
                .header("token", "token")
        )
                .andExpect(status().isCreated());
    }

    @Test
    void deleteInterest() throws Exception {
        Long deal_id = 1L;
        given(interestDeleteService.deleteInterest(anyString(), anyLong())).willReturn(ResponseEntity.noContent().build());

        mockMvc.perform(
                delete("/damoim/interest/{id}", deal_id)
                .contentType(MediaType.APPLICATION_JSON)
                .header("token", "token")
        )
                .andExpect(status().isNoContent());
    }
}