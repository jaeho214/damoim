package com.yeongjae.damoim.domain.deal.service;

import com.yeongjae.damoim.domain.deal.dto.DealGetByLocationDto;
import com.yeongjae.damoim.domain.deal.dto.DealGetByMemberDto;
import com.yeongjae.damoim.domain.deal.dto.DealGetDto;
import com.yeongjae.damoim.domain.deal.dto.DealGetPagingDto;
import com.yeongjae.damoim.domain.deal.entity.Deal;
import com.yeongjae.damoim.domain.deal.exception.DealNotFoundException;
import com.yeongjae.damoim.domain.deal.repository.DealRepository;
import com.yeongjae.damoim.domain.member.dto.MemberGetDto;
import com.yeongjae.damoim.domain.member.entity.Member;
import com.yeongjae.damoim.domain.member.exception.MemberNotFoundException;
import com.yeongjae.damoim.domain.member.repository.MemberRepository;
import com.yeongjae.damoim.global.error.ErrorCodeType;
import com.yeongjae.damoim.global.error.exception.BusinessLogicException;
import com.yeongjae.damoim.global.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DealGetService {

    private final int LIMIT = 10;

    private final DealRepository dealRepository;
    private final JwtService jwtService;

    @Transactional(readOnly = true)
    public DealGetPagingDto getDeals(String location, int pageNo) {
        Pageable pageable = PageRequest.of(--pageNo * LIMIT, 10, Sort.Direction.DESC, "createdAt");
        Page<DealGetByLocationDto> dealPage = dealRepository.findByLocation(location, pageable);

        if(dealPage == null)
            throw new DealNotFoundException();

        return DealGetPagingDto.locationOf(dealPage);
    }

    @Transactional
    public DealGetDto getDeal(String token, Long deal_id) {
        Member member = jwtService.findMemberByToken(token);

        Deal deal = dealRepository.fetchById(deal_id).orElseThrow(DealNotFoundException::new);

        if(!member.getIsVerified() || !member.getLocation().equals(deal.getLocation()))
            throw new BusinessLogicException(ErrorCodeType.USER_UNAUTHORIZED);

        deal.updateHits();
        return DealGetDto.toDto(deal, MemberGetDto.toDto(member));
    }

    @Transactional(readOnly = true)
    public DealGetPagingDto getDealByMember(String token, int pageNo) {
        Member member = jwtService.findMemberByToken(token);

        Pageable pageable = PageRequest.of(--pageNo * LIMIT, 10, Sort.Direction.DESC, "createdAt");
        Page<DealGetByMemberDto> dealPage = dealRepository.findByMember(member, pageable);

        return DealGetPagingDto.memberOf(dealPage);
    }
}
