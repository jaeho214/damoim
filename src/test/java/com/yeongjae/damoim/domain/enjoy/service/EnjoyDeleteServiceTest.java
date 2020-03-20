package com.yeongjae.damoim.domain.enjoy.service;

import com.yeongjae.damoim.domain.enjoy.entity.Enjoy;
import com.yeongjae.damoim.domain.enjoy.entity.EnjoyCategory;
import com.yeongjae.damoim.domain.enjoy.repository.EnjoyRepository;
import com.yeongjae.damoim.domain.member.entity.Member;
import com.yeongjae.damoim.domain.member.repository.MemberRepository;
import com.yeongjae.damoim.global.jwt.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class EnjoyDeleteServiceTest {

    @InjectMocks
    private EnjoyDeleteService enjoyDeleteService;
    @Mock
    private EnjoyRepository enjoyRepository;
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private JwtService jwtService;

    private Member member = Member.builder()
            .email("email")
            .birth("0214")
            .password("1234")
            .isVerified(true)
            .location("강원도_강릉시")
            .build();
    private Enjoy enjoy = Enjoy.builder()
            .member(member)
            .title("title")
            .content("content")
            .hits(0L)
            .category(EnjoyCategory.DRINK)
            .location("강원도_원주시")
            .build();
    private String email = "email@eamil.com";
    private String token = "token";

    @Test
    void deleteEnjoy() {
        //given
        given(jwtService.findMemberByToken(anyString())).willReturn(member);
        given(enjoyRepository.findById(anyLong())).willReturn(Optional.ofNullable(enjoy));

        //when
        ResponseEntity responseEntity = enjoyDeleteService.deleteEnjoy(token, 1L);

        //then
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(204);
    }
}