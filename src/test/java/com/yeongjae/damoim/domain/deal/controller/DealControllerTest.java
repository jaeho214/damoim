package com.yeongjae.damoim.domain.deal.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.yeongjae.damoim.domain.deal.dto.DealCreateDto;
import com.yeongjae.damoim.domain.deal.dto.DealGetDto;
import com.yeongjae.damoim.domain.deal.dto.DealGetPagingDto;
import com.yeongjae.damoim.domain.deal.dto.DealUpdateDto;
import com.yeongjae.damoim.domain.deal.entity.Deal;
import com.yeongjae.damoim.domain.deal.service.DealCreateService;
import com.yeongjae.damoim.domain.deal.service.DealDeleteService;
import com.yeongjae.damoim.domain.deal.service.DealGetService;
import com.yeongjae.damoim.domain.deal.service.DealUpdateService;
import com.yeongjae.damoim.domain.member.entity.Member;
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

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class DealControllerTest {
    @InjectMocks
    private DealController dealController;
    @Mock
    private DealCreateService dealCreateService;
    @Mock
    private DealGetService dealGetService;
    @Mock
    private DealUpdateService dealUpdateService;
    @Mock
    private DealDeleteService dealDeleteService;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    private Member memberFixture = new EasyRandom().nextObject(Member.class);
    private Deal dealFixture = new EasyRandom().nextObject(Deal.class);
    private DealGetDto dealGetDtoFixture = new EasyRandom().nextObject(DealGetDto.class);
    private DealGetPagingDto dealGetPagingDtoFixture = new EasyRandom().nextObject(DealGetPagingDto.class);
    private DealUpdateDto dealUpdateDtoFixture = new EasyRandom().nextObject(DealUpdateDto.class);
    private DealCreateDto dealCreateDtoFixture = DealCreateDto.builder()
            .content("content")
            .location("경기도_부천시")
            .imagePaths(new ArrayList<>())
            .category("디지털/가전")
            .title("title")
            .build();
    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(dealController)
                .alwaysDo(print()).build();
    }

    @Test
    void createDeal() throws Exception {
        Deal deal = dealCreateDtoFixture.of(memberFixture);
        given(dealCreateService.createDeal(anyString(), any(DealCreateDto.class))).willReturn(dealGetDtoFixture);

        mockMvc.perform(
                post("/damoim/deal")
                .contentType(MediaType.APPLICATION_JSON)
                .header("token", "token")
                .content(
                        objectMapper.writeValueAsString(dealCreateDtoFixture)
                )
        )
                .andExpect(status().isCreated());
    }

    @Test
    void getDeals() throws Exception {
        Deal deal = dealCreateDtoFixture.of(memberFixture);
        String location = "강원도_원주시";
        given(dealGetService.getDeals(anyString(), anyInt())).willReturn(dealGetPagingDtoFixture);

        mockMvc.perform(
                get("/damoim/deal/list/{location}", location)
                .header("token", "token")
                .contentType(MediaType.APPLICATION_JSON)
                .param("pageNo", String.valueOf(1))
        )
                .andExpect(status().isOk());
    }

    @Test
    void getDeal() throws Exception {
        Long deal_id = 1L;
        given(dealGetService.getDeal(anyString(), anyLong())).willReturn(dealGetDtoFixture);

        mockMvc.perform(
                get("/damoim/deal/{deal_id}", deal_id)
                .contentType(MediaType.APPLICATION_JSON)
                .header("token", "token")
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(dealFixture.getTitle()));
    }

    @Test
    void getDealByMember() throws Exception{
        given(dealGetService.getDealByMember(anyString(), anyInt())).willReturn(dealGetPagingDtoFixture);

        mockMvc.perform(
                get("/damoim/deal/user")
                .contentType(MediaType.APPLICATION_JSON)
                .header("token", "token")
                .param("pageNo", String.valueOf(1))
        )
                .andExpect(status().isOk());
    }

    @Test
    void getDealByCategory() throws Exception{
        given(dealGetService.getDealByCategory(anyString(), anyString(), anyInt())).willReturn(dealGetPagingDtoFixture);

        mockMvc.perform(
                get("/damoim/deal/category/{location}/{category}", "경기도_시흥시", "유아용품")
                .contentType(MediaType.APPLICATION_JSON)
                .param("pageNo", String.valueOf(1))
        )
                .andExpect(status().isOk());
    }

    @Test
    void searchDealByKeyword() throws Exception{
        given(dealGetService.searchByKeyword(anyString(), anyString(), anyInt())).willReturn(dealGetPagingDtoFixture);

        mockMvc.perform(
                get("/damoim/deal/search/{location}/{keyword}", "경기도_시흥시", "유아용품")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("pageNo", String.valueOf(1))
        )
                .andExpect(status().isOk());
    }


    @Test
    void updateDeal() throws Exception {
        given(dealUpdateService.updateDeal(anyString(), any(DealUpdateDto.class))).willReturn(dealGetDtoFixture);

        mockMvc.perform(
                put("/damoim/deal")
                .contentType(MediaType.APPLICATION_JSON)
                .header("token", "token")
                .content(
                        objectMapper.writeValueAsString(dealUpdateDtoFixture)
                )
        )
                .andExpect(status().isOk());
    }

    @Test
    void deleteDeal() throws Exception {
        Long deal_id = 1L;
        given(dealDeleteService.deleteDeal(anyString(),anyLong())).willReturn(ResponseEntity.noContent().build());

        mockMvc.perform(
                delete("/damoim/deal/{id}", deal_id)
                .contentType(MediaType.APPLICATION_JSON)
                .header("token", "token")
        )
                .andExpect(status().isNoContent());
    }
}