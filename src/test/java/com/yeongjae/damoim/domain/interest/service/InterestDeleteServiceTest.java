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
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class InterestDeleteServiceTest {

    @InjectMocks
    private InterestDeleteService interestDeleteService;
    @Mock
    private JwtService jwtService;
    @Mock
    private DealRepository dealRepository;

    @Mock
    private InterestRepository interestRepository;

    private String email = "email@email.com";
    private Member memberFixture = new EasyRandom().nextObject(Member.class);
    private Deal dealFixture = new EasyRandom().nextObject(Deal.class);
    private Interest interestFixture = new EasyRandom().nextObject(Interest.class);


    @Test
    void deleteInterest() {
        //given
        given(jwtService.findMemberByToken(anyString())).willReturn(memberFixture);
        given(dealRepository.findById(anyLong())).willReturn(Optional.ofNullable(dealFixture));
        given(interestRepository.findByMember_IdAndDeal_id(anyLong(), anyLong())).willReturn(Optional.ofNullable(interestFixture));

        //when
        ResponseEntity responseEntity = interestDeleteService.deleteInterest("token", 1L);

        //then
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(204);
    }
}