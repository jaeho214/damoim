package com.yeongjae.damoim.domain.deal.service;

import com.yeongjae.damoim.domain.deal.dto.DealGetDto;
import com.yeongjae.damoim.domain.deal.dto.DealUpdateDto;
import com.yeongjae.damoim.domain.deal.entity.Deal;
import com.yeongjae.damoim.domain.deal.entity.DealImage;
import com.yeongjae.damoim.domain.deal.repository.DealImageRepository;
import com.yeongjae.damoim.domain.deal.repository.DealRepository;
import com.yeongjae.damoim.domain.member.entity.Member;
import com.yeongjae.damoim.domain.member.repository.MemberRepository;
import com.yeongjae.damoim.global.jwt.JwtService;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
class DealUpdateServiceTest {
    @InjectMocks
    private DealUpdateService dealUpdateService;
    @Mock
    private DealRepository dealRepository;
    @Mock
    private DealImageUpdateService dealImageUpdateService;
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private JwtService jwtService;


    private String token = "token";
    private String email = "email@email.com";
    private DealUpdateDto dealUpdateDtoFixture = new EasyRandom().nextObject(DealUpdateDto.class);
    private DealImage dealImageFixture = new EasyRandom().nextObject(DealImage.class);
    private List<DealImage> dealImages = new ArrayList<>(Arrays.asList(dealImageFixture));
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
            .interests(new ArrayList<>())
            .imagePaths(new ArrayList<>())
            .hits(0L)
            .build();

    @Test
    void updateDeal() {
        //given
        given(jwtService.findMemberByToken(anyString())).willReturn(memberFixture);
        given(dealRepository.fetchById(anyLong())).willReturn(Optional.ofNullable(dealFixture));

        //when
        DealGetDto updateDeal = dealUpdateService.updateDeal(token, dealUpdateDtoFixture);

        //then
        assertThat(updateDeal.getContent()).isEqualTo(dealUpdateDtoFixture.getContent());

    }
}