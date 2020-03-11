package com.yeongjae.damoim.domain.interest.service;

import com.yeongjae.damoim.domain.deal.entity.Deal;
import com.yeongjae.damoim.domain.deal.repository.DealRepository;
import com.yeongjae.damoim.domain.interest.entity.Interest;
import com.yeongjae.damoim.domain.interest.repository.InterestRepository;
import com.yeongjae.damoim.domain.member.entity.Member;
import com.yeongjae.damoim.domain.member.repository.MemberRepository;
import com.yeongjae.damoim.global.jwt.JwtService;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class InterestCreateServiceTest {

    @InjectMocks
    private InterestCreateService interestCreateService;
    @Mock
    private JwtService jwtService;
    @Mock
    private DealRepository dealRepository;
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private InterestRepository interestRepository;

    private String email = "email@email.com";
    private Member memberFixture = new EasyRandom().nextObject(Member.class);
    private Deal dealFixture = new EasyRandom().nextObject(Deal.class);
    private Interest interestFixture = new EasyRandom().nextObject(Interest.class);

    @Test
    void createInterest() {
        //given
        given(jwtService.findEmailByJwt(anyString())).willReturn(email);
        given(memberRepository.findByEmail(anyString())).willReturn(Optional.ofNullable(memberFixture));
        given(dealRepository.findById(anyLong())).willReturn(Optional.ofNullable(dealFixture));
        given(interestRepository.save(any(Interest.class))).willReturn(interestFixture);

        //when
        Interest savedInterest = interestCreateService.createInterest("token", 1L);

        //then
        assertThat(savedInterest.getMember().getEmail()).isEqualTo(memberFixture.getEmail());
    }
}