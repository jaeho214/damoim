package com.yeongjae.damoim.domain.member.service;

import com.yeongjae.damoim.domain.member.dto.MemberSignInDto;
import com.yeongjae.damoim.domain.member.entity.Member;
import com.yeongjae.damoim.domain.member.repository.MemberRepository;
import com.yeongjae.damoim.global.jwt.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class MemberSignInServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberSignInService memberSignInService;

    @Mock
    private JwtService jwtService;

    @Mock
    private PasswordEncoder passwordEncoder;


    private MemberSignInDto memberSignInDto = MemberSignInDto.builder()
            .email("email@gmail.com")
            .password("1")
            .build();

    private Member member = Member.builder()
            .email("email@gmail.com")
            .password("1")
            .location("강원도_강릉시")
            .isVerified(false)
            .nickName("닉넴")
            .sex("male")
            .phone("010-1111-2222")
            .build();

    private String token = "token";
    @Test
    void signIn() {
        //given
        given(memberRepository.findByEmail(anyString())).willReturn(Optional.of(member));
        given(jwtService.createJwt(anyString())).willReturn(token);
        given(passwordEncoder.matches(anyString(), anyString())).willReturn(true);

        //when
        String token = memberSignInService.signIn(memberSignInDto);

        //then
        assertThat(token).isNotBlank();
        assertThat(token).isEqualTo(token);
    }
}