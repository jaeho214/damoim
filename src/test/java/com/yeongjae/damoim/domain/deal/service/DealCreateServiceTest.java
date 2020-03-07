package com.yeongjae.damoim.domain.deal.service;

import com.yeongjae.damoim.domain.board.entity.BoardImage;
import com.yeongjae.damoim.domain.board.repository.BoardImageRepository;
import com.yeongjae.damoim.domain.board.repository.BoardRepository;
import com.yeongjae.damoim.domain.deal.dto.DealCreateDto;
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
class DealCreateServiceTest {
    @Mock
    private JwtService jwtService;
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private DealRepository dealRepository;
    @Mock
    private DealImageRepository dealImageRepository;
    @InjectMocks
    private DealCreateService dealCreateService;

    private DealCreateDto dealCreateDtoFixture = new EasyRandom().nextObject(DealCreateDto.class);
    private DealImage dealImageFixture = new EasyRandom().nextObject(DealImage.class);
    private List<DealImage> dealImages = new ArrayList<>(Arrays.asList(dealImageFixture));
    private Member memberFixture = new EasyRandom().nextObject(Member.class);
    private String token = "token";
    private String email = "a@email.com";

    @Test
    void createDeal() {
        //given
        Deal deal = dealCreateDtoFixture.of(memberFixture);
        given(jwtService.findEmailByJwt(anyString())).willReturn(email);
        given(memberRepository.findByEmail(anyString())).willReturn(Optional.ofNullable(memberFixture));
        given(dealImageRepository.saveAll(anyList())).willReturn(dealImages);
        given(dealRepository.save(any(Deal.class))).willReturn(deal);

        //when
        Deal savedDeal = dealCreateService.createDeal(token, dealCreateDtoFixture);

        //then
        assertThat(savedDeal.getContent()).isEqualTo(dealCreateDtoFixture.getContent());
        assertThat(savedDeal.getPrice()).isEqualTo(dealCreateDtoFixture.getPrice());
    }
}