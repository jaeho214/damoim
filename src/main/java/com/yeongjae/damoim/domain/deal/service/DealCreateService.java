package com.yeongjae.damoim.domain.deal.service;

import com.yeongjae.damoim.domain.deal.dto.DealCreateDto;
import com.yeongjae.damoim.domain.deal.dto.DealGetDto;
import com.yeongjae.damoim.domain.deal.entity.Deal;
import com.yeongjae.damoim.domain.deal.entity.DealImage;
import com.yeongjae.damoim.domain.deal.repository.DealImageRepository;
import com.yeongjae.damoim.domain.deal.repository.DealRepository;
import com.yeongjae.damoim.domain.member.dto.MemberGetDto;
import com.yeongjae.damoim.domain.member.entity.Member;
import com.yeongjae.damoim.domain.member.exception.MemberNotFoundException;
import com.yeongjae.damoim.domain.member.repository.MemberRepository;
import com.yeongjae.damoim.global.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DealCreateService {

    private final DealRepository dealRepository;
    private final DealImageCreateService dealImageCreateService;
    private final JwtService jwtService;

    public DealGetDto createDeal(String token, DealCreateDto dealCreateDto) {
        Member member = jwtService.findMemberByToken(token);

        Deal deal = dealCreateDto.of(member);

        if(dealCreateDto.getImagePaths() != null) {
            Deal savedDeal = dealImageCreateService.saveDealImage(dealCreateDto, deal);
            return DealGetDto.toDto(savedDeal, MemberGetDto.toDto(member));
        }

        Deal savedDeal = dealRepository.save(deal);
        return DealGetDto.toDto(savedDeal, MemberGetDto.toDto(member));
    }
}
