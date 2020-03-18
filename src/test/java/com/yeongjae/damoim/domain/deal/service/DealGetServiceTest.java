package com.yeongjae.damoim.domain.deal.service;


import com.yeongjae.damoim.domain.deal.dto.DealGetByLocationDto;
import com.yeongjae.damoim.domain.deal.dto.DealGetByMemberDto;
import com.yeongjae.damoim.domain.deal.dto.DealGetDto;
import com.yeongjae.damoim.domain.deal.dto.DealGetPagingDto;
import com.yeongjae.damoim.domain.deal.entity.Deal;
import com.yeongjae.damoim.domain.deal.repository.DealRepository;
import com.yeongjae.damoim.domain.member.entity.Member;
import com.yeongjae.damoim.domain.member.repository.MemberRepository;
import com.yeongjae.damoim.global.jwt.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
class DealGetServiceTest {
    @Mock
    private DealRepository dealRepository;
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private JwtService jwtService;
    @InjectMocks
    private DealGetService dealGetService;

    private String token = "token";
    private String email = "email@email.com";
    private Page<DealGetByLocationDto> dealGetByLocationDtoPage = Mockito.mock(Page.class);
    private Page<DealGetByMemberDto> dealGetByMemberDtoPage = Mockito.mock(Page.class);
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
            .hits(0L)
            .build();
    @Test
    void getDeals() {
        //given
        String location = "서울특별시_성북구";
        given(dealRepository.findByLocation(anyString(), any())).willReturn(dealGetByLocationDtoPage);

        //when
        DealGetPagingDto deals = dealGetService.getDeals(location, 1);

        //then
        assertThat(deals.getDealGetByLocationDtos().size()).isEqualTo(dealGetByLocationDtoPage.getSize());
    }

    @Test
    void getDeal() {
        //given
        given(jwtService.findMemberByToken(anyString())).willReturn(memberFixture);
        given(dealRepository.fetchById(anyLong())).willReturn(Optional.ofNullable(dealFixture));

        //when
        DealGetDto deal = dealGetService.getDeal(token, 1L);

        //then
        assertThat(deal.getContent()).isEqualTo(dealFixture.getContent());
        assertThat(deal.getPrice()).isEqualTo(dealFixture.getPrice());
        assertThat(deal.getMember()).isNotNull();
    }

    @Test
    void getDealByMember(){
        //given
        given(jwtService.findMemberByToken(anyString())).willReturn(memberFixture);
        given(dealRepository.findByMember(any(Member.class), any())).willReturn(dealGetByMemberDtoPage);


        //when
        DealGetPagingDto deal = dealGetService.getDealByMember("token", 1);

        //then
        assertThat(deal.getDealGetByMemberDtos().size()).isEqualTo(dealGetByMemberDtoPage.getSize());
    }
}