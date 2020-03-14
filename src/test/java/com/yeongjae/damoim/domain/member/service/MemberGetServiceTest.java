package com.yeongjae.damoim.domain.member.service;

import com.yeongjae.damoim.domain.member.entity.Member;
import com.yeongjae.damoim.domain.member.repository.MemberRepository;
import com.yeongjae.damoim.global.jwt.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class MemberGetServiceTest {
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private JwtService jwtService;

    @InjectMocks
    private MemberGetService memberGetService;

    private Member member = Member.builder()
            .email("email@gmail.com")
            .password("1")
            .location("강원도_강릉시")
            .isVerified(false)
            .nickName("닉넴")
            .sex("male")
            .phone("010-1111-2222")
            .build();
    @Test
    void getMember() {
        //given
        given(jwtService.findMemberByToken(any(String.class))).willReturn(member);

        //when
        String token = "asdf";
        Member member = memberGetService.getMember(token);

        //then
        assertThat(member.getLocation()).isEqualTo(this.member.getLocation());
        assertThat(member.getNickName()).isEqualTo(this.member.getNickName());
    }
}