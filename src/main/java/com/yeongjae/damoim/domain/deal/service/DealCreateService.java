package com.yeongjae.damoim.domain.deal.service;

import com.yeongjae.damoim.domain.deal.dto.DealCreateDto;
import com.yeongjae.damoim.domain.deal.entity.Deal;
import com.yeongjae.damoim.domain.deal.entity.DealImage;
import com.yeongjae.damoim.domain.deal.repository.DealImageRepository;
import com.yeongjae.damoim.domain.deal.repository.DealRepository;
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
    private final DealImageRepository dealImageRepository;
    private final MemberRepository memberRepository;
    private final JwtService jwtService;

    public Deal createDeal(String token, DealCreateDto dealCreateDto) {
        String email = jwtService.findEmailByJwt(token);

        Member member = memberRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new);

        Deal deal = dealCreateDto.of(member);

        if(dealCreateDto.getImagePaths() != null) {
            List<DealImage> dealImageList = new ArrayList<>();
            dealCreateDto.getImagePaths().forEach(imagePath ->
                    dealImageList.add(
                            DealImage.builder()
                                    .imagePath(imagePath)
                                    .deal(deal)
                                    .build()
                    ));

            dealImageList.forEach(dealImage -> deal.addImage(dealImage));

            dealImageRepository.saveAll(dealImageList);
        }

        return dealRepository.save(deal);
    }
}
