package com.yeongjae.damoim.domain.member.service;

import com.yeongjae.damoim.domain.member.dto.MemberUpdateDto;
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
class MemberUpdateServiceTest {
    @Mock
    private MemberRepository memberRepository;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private MemberUpdateService memberUpdateService;

    private MemberUpdateDto member = MemberUpdateDto.builder()
            .password("1")
            .location("강원도_강릉시")
            .isVerified(false)
            .nickName("닉넴")
            .phone("010-1111-2222")
            .build();

    private String token = "";
    private String email = "a@gmail.com";

    @Test
    void updateMember() {
        //given
        Member updateMember = member.of();
        given(memberRepository.findByEmail(email)).willReturn(Optional.of(updateMember));
        given(jwtService.findEmailByJwt(any(String.class))).willReturn(email);

        //when
        Member member = memberUpdateService.updateMember(token, this.member);

        //then
        assertThat(member.getNickName()).isEqualTo(this.member.getNickName());
        assertThat(member.getLocation()).isEqualTo(this.member.getLocation());
    }
}