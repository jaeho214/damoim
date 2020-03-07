package com.yeongjae.damoim.domain.deal.service;

import com.yeongjae.damoim.domain.deal.entity.Deal;
import com.yeongjae.damoim.domain.deal.repository.DealRepository;
import com.yeongjae.damoim.domain.member.entity.Member;
import com.yeongjae.damoim.domain.member.repository.MemberRepository;
import com.yeongjae.damoim.global.jwt.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class DealDeleteServiceTest {

    @InjectMocks
    private DealDeleteService dealDeleteService;
    @Mock
    private JwtService jwtService;
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private DealRepository dealRepository;

    private String token = "token";
    private String email = "email@email.com";
    private Member memberFixture = Member.builder()
            .email(email)
            .password("1")
            .isVerified(true)
            .location("서울특별시_성북구")
            .build();
    private Deal dealFixture = Deal.builder()
            .title("title")
            .content("content")
            .location("서울특별시_성북구")
            .member(memberFixture)
            .imagePaths(new ArrayList<>())
            .hits(0L)
            .build();

    @Test
    void deleteDeal() {
        //given
        given(jwtService.findEmailByJwt(anyString())).willReturn(email);
        given(memberRepository.findByEmail(anyString())).willReturn(Optional.ofNullable(memberFixture));
        given(dealRepository.findById(anyLong())).willReturn(Optional.ofNullable(dealFixture));

        //when
        ResponseEntity responseEntity = dealDeleteService.deleteDeal(token, 1L);

        //then
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(204);
    }
}