package com.yeongjae.damoim.domain.interest.service;

import com.yeongjae.damoim.domain.deal.entity.Deal;
import com.yeongjae.damoim.domain.deal.exception.DealNotFoundException;
import com.yeongjae.damoim.domain.deal.repository.DealRepository;
import com.yeongjae.damoim.domain.interest.dto.InterestGetDto;
import com.yeongjae.damoim.domain.interest.entity.Interest;
import com.yeongjae.damoim.domain.interest.repository.InterestRepository;
import com.yeongjae.damoim.domain.member.entity.Member;
import com.yeongjae.damoim.global.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class InterestCreateService {

    private final InterestRepository interestRepository;
    private final DealRepository dealRepository;
    private final JwtService jwtService;

    public InterestGetDto createInterest(String token, Long deal_id) {
        Member member = jwtService.findMemberByToken(token);
        Deal deal = dealRepository.findById(deal_id).orElseThrow(DealNotFoundException::new);

        Interest savedInterest =
                interestRepository.save(Interest.builder()
                                        .member(member)
                                        .deal(deal)
                                        .build());

        return InterestGetDto.toDto(savedInterest.getInterest_id(), member, deal);
    }
}
