package com.yeongjae.damoim.domain.deal.service;

import com.yeongjae.damoim.domain.deal.dto.DealGetDto;
import com.yeongjae.damoim.domain.deal.dto.DealUpdateDto;
import com.yeongjae.damoim.domain.deal.entity.Deal;
import com.yeongjae.damoim.domain.deal.entity.DealImage;
import com.yeongjae.damoim.domain.deal.exception.DealNotFoundException;
import com.yeongjae.damoim.domain.deal.repository.DealImageRepository;
import com.yeongjae.damoim.domain.deal.repository.DealRepository;
import com.yeongjae.damoim.domain.member.dto.MemberGetDto;
import com.yeongjae.damoim.domain.member.entity.Member;
import com.yeongjae.damoim.domain.member.exception.MemberNotFoundException;
import com.yeongjae.damoim.domain.member.repository.MemberRepository;
import com.yeongjae.damoim.global.error.ErrorCodeType;
import com.yeongjae.damoim.global.error.exception.BusinessLogicException;
import com.yeongjae.damoim.global.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DealUpdateService {

    private final DealRepository dealRepository;
    private final DealImageUpdateService dealImageUpdateService;
    private final JwtService jwtService;

    public DealGetDto updateDeal(String token, DealUpdateDto dealUpdateDto) {
        Member member = jwtService.findMemberByToken(token);

        Deal deal = dealRepository.fetchById(dealUpdateDto.getDeal_id()).orElseThrow(DealNotFoundException::new);

        checkMember(member, deal.getMember());

        deal.updateDeal(dealUpdateDto);

        if(dealUpdateDto.getImagePaths() != null) {
            dealImageUpdateService.saveDealImage(dealUpdateDto, deal);
        }
        return DealGetDto.toDto(deal);
    }

    private void checkMember(Member updater, Member writer) {
        if(updater.getEmail().equals(writer.getEmail()))
            return ;
        throw new BusinessLogicException(ErrorCodeType.USER_UNAUTHORIZED);
    }
}
