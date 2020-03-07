package com.yeongjae.damoim.domain.member.service;

import com.yeongjae.damoim.domain.member.entity.Member;
import com.yeongjae.damoim.domain.member.repository.MemberRepository;
import com.yeongjae.damoim.global.jwt.JwtService;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class MemberDeleteServiceTest {

    @Mock
    private JwtService jwtService;
    @Mock
    private MemberRepository memberRepository;
    @InjectMocks
    private MemberDeleteService memberDeleteService;

    private String email = "asdf@email.com";
    private Member member = new EasyRandom().nextObject(Member.class);
    @Test
    void deleteMember() {
        //given
        String token = "token";
        given(jwtService.findEmailByJwt(anyString())).willReturn(email);
        given(memberRepository.findByEmail(anyString())).willReturn(Optional.ofNullable(member));

        //when
        ResponseEntity responseEntity = memberDeleteService.deleteMember(token);

        //then
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(204);

    }
}