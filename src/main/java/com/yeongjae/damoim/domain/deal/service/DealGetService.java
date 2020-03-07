package com.yeongjae.damoim.domain.deal.service;

import com.yeongjae.damoim.domain.deal.entity.Deal;
import com.yeongjae.damoim.domain.deal.exception.DealNotFoundException;
import com.yeongjae.damoim.domain.deal.repository.DealRepository;
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

    private final DealRepository dealRepository;
    private final MemberRepository memberRepository;
    private final JwtService jwtService;

    @Transactional(readOnly = true)
    public List<Deal> getDeals(String location, int pageNo) {
        Pageable pageable = PageRequest.of(pageNo, 10, Sort.Direction.ASC, "createdAt");

        Page<Deal> dealPage = dealRepository.findByLocation(location, pageable);

        if(dealPage == null)
            throw new DealNotFoundException();

        return dealPage.stream()
                .collect(Collectors.toList());
    }

    @Transactional
    public Deal getDeal(String token, Long deal_id) {
        String email = jwtService.findEmailByJwt(token);
        Member member = memberRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new);

        Deal deal = dealRepository.fetchById(deal_id).orElseThrow(DealNotFoundException::new);

        if(!member.getIsVerified() || !member.getLocation().equals(deal.getLocation()))
            throw new BusinessLogicException(ErrorCodeType.USER_UNAUTHORIZED);

        deal.updateHits();
        return deal;
    }
}
