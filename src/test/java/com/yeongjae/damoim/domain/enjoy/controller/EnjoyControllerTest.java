package com.yeongjae.damoim.domain.enjoy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.yeongjae.damoim.domain.enjoy.dto.EnjoyCreateDto;
import com.yeongjae.damoim.domain.enjoy.dto.EnjoyGetDto;
import com.yeongjae.damoim.domain.enjoy.dto.EnjoyGetPagingDto;
import com.yeongjae.damoim.domain.enjoy.dto.EnjoyUpdateDto;
import com.yeongjae.damoim.domain.enjoy.entity.Enjoy;
import com.yeongjae.damoim.domain.enjoy.service.EnjoyCreateService;
import com.yeongjae.damoim.domain.enjoy.service.EnjoyDeleteService;
import com.yeongjae.damoim.domain.enjoy.service.EnjoyGetService;
import com.yeongjae.damoim.domain.enjoy.service.EnjoyUpdateService;
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

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class EnjoyControllerTest {
    @InjectMocks
    private EnjoyController enjoyController;
    @Mock
    private EnjoyCreateService enjoyCreateService;
    @Mock
    private EnjoyGetService enjoyGetService;
    @Mock
    private EnjoyUpdateService enjoyUpdateService;
    @Mock
    private EnjoyDeleteService enjoyDeleteService;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(enjoyController)
                .alwaysDo(print()).build();
    }

    private Member memberFixture = new EasyRandom().nextObject(Member.class);
    private EnjoyCreateDto enjoyCreateDtoFixture = new EasyRandom().nextObject(EnjoyCreateDto.class);
    private EnjoyUpdateDto enjoyUpdateDtoFixture = new EasyRandom().nextObject(EnjoyUpdateDto.class);
    private Enjoy enjoyFixture = new EasyRandom().nextObject(Enjoy.class);
    private EnjoyGetDto enjoyGetDtoFixture = new EasyRandom().nextObject(EnjoyGetDto.class);
    private EnjoyGetPagingDto enjoyGetPagingDtoFixture = new EasyRandom().nextObject(EnjoyGetPagingDto.class);

    @Test
    void createEnjoy() throws Exception {
        Enjoy enjoy = enjoyCreateDtoFixture.of(memberFixture);
        given(enjoyCreateService.createEnjoy(anyString(), any(EnjoyCreateDto.class))).willReturn(enjoyGetDtoFixture);

        mockMvc.perform(
                post("/damoim/enjoy")
                .contentType(MediaType.APPLICATION_JSON)
                .header("token", "token")
                .content(
                        objectMapper.writeValueAsString(enjoyCreateDtoFixture)
                )
        )
                .andExpect(status().isCreated());
    }

    @Test
    void getEnjoys() throws Exception {
        String location = "강원도_원주시";
        given(enjoyGetService.getEnjoys(anyString(), anyInt())).willReturn(enjoyGetPagingDtoFixture);

        mockMvc.perform(
                get("/damoim/enjoy/list/{location}", location)
                .contentType(MediaType.APPLICATION_JSON)
                .header("token", "token")
                .param("pageNo", String.valueOf(1))
        )
                .andExpect(status().isOk());
    }

    @Test
    void getEnjoy() throws Exception {
        Long enjoy_id = 1L;
        given(enjoyGetService.getEnjoy(anyString(),anyLong())).willReturn(enjoyGetDtoFixture);

        mockMvc.perform(
                get("/damoim/enjoy/{id}", enjoy_id)
                .contentType(MediaType.APPLICATION_JSON)
                .header("token", "token")
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(enjoyGetDtoFixture.getTitle()));
    }

    @Test
    void getEnjoyByMember() throws Exception{
        given(enjoyGetService.getEnjoyByMember(anyString(), anyInt())).willReturn(enjoyGetPagingDtoFixture);

        mockMvc.perform(
                get("/damoim/enjoy/user")
                .contentType(MediaType.APPLICATION_JSON)
                .header("token", "token")
                .param("pageNo", String.valueOf(1))
        )
                .andExpect(status().isOk());

    }

    @Test
    void searchEnjoyByKeyword() throws Exception{
        given(enjoyGetService.searchByKeyword(anyString(), anyString(), anyInt())).willReturn(enjoyGetPagingDtoFixture);

        mockMvc.perform(
                get("/damoim/enjoy/search/{location}/{keyword}", "경기도_시흥시", "키워드")
                .contentType(MediaType.APPLICATION_JSON)
                .header("token", "token")
                .param("pageNo", String.valueOf(1))
        )
                .andExpect(status().isOk());
    }

//    @Test
//    void getEnjoyByCategory() throws Exception{
//        String location = "경기도 시흥시";
//        String category = "카테고리";
//        given(enjoyGetService.getEnjoyByCategory(anyString(), anyString(), anyInt())).willReturn(enjoyGetPagingDtoFixture);
//
//        mockMvc.perform(
//                get("/damoim/enjoy/category/{location}/{category}", location, category)
//                .contentType(MediaType.APPLICATION_JSON)
//                .header("token", "token")
//                .param("pageNo", String.valueOf(1))
//        )
//                .andExpect(status().isOk());
//    }

    @Test
    void updateEnjoy() throws Exception{
        given(enjoyUpdateService.updateEnjoy(anyString(),any(EnjoyUpdateDto.class))).willReturn(enjoyGetDtoFixture);

        mockMvc.perform(
                put("/damoim/enjoy")
                .header("token", "token")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                        objectMapper.writeValueAsString(enjoyUpdateDtoFixture)
                )
        )
                .andExpect(status().isOk());
    }

    @Test
    void deleteEnjoy() throws Exception {
        Long enjoy_id = 1L;
        given(enjoyDeleteService.deleteEnjoy(anyString(),anyLong())).willReturn(ResponseEntity.noContent().build());

        mockMvc.perform(
                delete("/damoim/enjoy/{id}", enjoy_id)
                .header("token", "token")
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isNoContent());
    }
}