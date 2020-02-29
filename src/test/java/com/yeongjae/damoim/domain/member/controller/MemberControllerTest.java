package com.yeongjae.damoim.domain.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.yeongjae.damoim.domain.location.entity.Location;
import com.yeongjae.damoim.domain.member.dto.MemberCreateDto;
import com.yeongjae.damoim.domain.member.dto.MemberSignInDto;
import com.yeongjae.damoim.domain.member.dto.MemberUpdateDto;
import com.yeongjae.damoim.domain.member.entity.Member;
import com.yeongjae.damoim.domain.member.service.MemberCreateService;
import com.yeongjae.damoim.domain.member.service.MemberGetService;
import com.yeongjae.damoim.domain.member.service.MemberSignInService;
import com.yeongjae.damoim.domain.member.service.MemberUpdateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@DisplayName("회원 관련 api 테스트")
class MemberControllerTest {

    @InjectMocks
    private MemberController memberController;

    @Mock
    private MemberCreateService memberCreateService;

    @Mock
    private MemberSignInService memberSignInService;

    @Mock
    private MemberGetService memberGetService;

    @Mock
    private MemberUpdateService memberUpdateService;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    private MemberCreateDto memberCreateDto = MemberCreateDto.builder()
            .email("email@gmail.com")
            .password("1")
            .location(Location.강원도_강릉시)
            .isVerified(false)
            .nickName("닉넴")
            .sex("male")
            .phone("010-1111-2222")
            .build();

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(memberController)
                .alwaysDo(print()).build();
    }

    @Test
    @DisplayName("회원가입 성공")
    void createMember() throws Exception {
        Member member = memberCreateDto.of();
        //아무런 MemberCreateDto 타입의 객체만을 받아도 항상 member 객체를 리턴해주도록 하기
        given(memberCreateService.createMember(any(MemberCreateDto.class)))
                .willReturn(member);

        mockMvc.perform(
                post("/damoim/sign")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                objectMapper.writeValueAsString(memberCreateDto)
                        )
        )
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("로그인 성공")
    void signIn() throws Exception{
        MemberSignInDto memberSignInDto = MemberSignInDto.builder()
                .email("a@gmail.com")
                .password("1")
                .build();

        given(memberSignInService.signIn(any(MemberSignInDto.class))).willReturn(any(String.class));

        mockMvc.perform(
                post("/damoim/sign/signIn")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                        objectMapper.writeValueAsString(memberSignInDto)
                )
        )
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("회원 정보 조회")
    void getMember() throws Exception {
        Member member = memberCreateDto.of();
        given(memberGetService.getMember(any(String.class))).willReturn(member);

        mockMvc.perform(
                get("/damoim/sign")
                    .header("token", "ssssssssssssssss")
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nickName").value(member.getNickName()))
                .andExpect(jsonPath("$.email").value(member.getEmail()));
    }

    @Test
    void updateMember() throws Exception{
        MemberUpdateDto memberUpdateDto = MemberUpdateDto.builder()
                .location(Location.강원도_강릉시)
                .isVerified(true)
                .nickName("닉넴입다")
                .password("11")
                .phone("0000")
                .build();
        Member member = memberUpdateDto.of();
        given(memberUpdateService.updateMember(any(String.class),any(MemberUpdateDto.class))).willReturn(member);
        mockMvc.perform(
                put("/damoim/sign")
                .header("token", "sssssssssssssssss")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                        objectMapper.writeValueAsString(memberUpdateDto)
                )
        )
                .andExpect(status().isOk());
    }

}