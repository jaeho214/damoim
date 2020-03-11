package com.yeongjae.damoim.domain.interest.service;

import com.yeongjae.damoim.domain.deal.entity.Deal;
import com.yeongjae.damoim.domain.deal.exception.DealNotFoundException;
import com.yeongjae.damoim.domain.deal.repository.DealRepository;
import com.yeongjae.damoim.domain.interest.entity.Interest;
import com.yeongjae.damoim.domain.interest.exception.InterestNotFoundException;
import com.yeongjae.damoim.domain.interest.repository.InterestRepository;
import com.yeongjae.damoim.domain.member.entity.Member;
import com.yeongjae.damoim.domain.member.exception.MemberNotFoundException;
import com.yeongjae.damoim.domain.member.repository.MemberRepository;
import com.yeongjae.damoim.global.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InterestDeleteService {

    private final InterestRepository interestRepository;
    private final DealRepository dealRepository;
    private final MemberRepository memberRepository;
    private final JwtService jwtService;

    public ResponseEntity deleteInterest(String token, Long deal_id) {
        String email = jwtService.findEmailByJwt(token);

        Member member = memberRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new);

        Deal deal = dealRepository.findById(deal_id).orElseThrow(DealNotFoundException::new);

        Interest interest = interestRepository.findByMember_IdAndDeal_id(member.getId(), deal.getId()).orElseThrow(InterestNotFoundException::new);

        interestRepository.delete(interest);

        return ResponseEntity.noContent().build();
    }
}
