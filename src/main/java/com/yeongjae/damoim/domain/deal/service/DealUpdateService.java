package com.yeongjae.damoim.domain.deal.service;

import com.yeongjae.damoim.domain.deal.dto.DealUpdateDto;
import com.yeongjae.damoim.domain.deal.entity.Deal;
import com.yeongjae.damoim.domain.deal.entity.DealImage;
import com.yeongjae.damoim.domain.deal.exception.DealNotFoundException;
import com.yeongjae.damoim.domain.deal.repository.DealImageRepository;
import com.yeongjae.damoim.domain.deal.repository.DealRepository;
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
    private final DealImageRepository dealImageRepository;
    private final MemberRepository memberRepository;
    private final JwtService jwtService;

    public Deal updateDeal(String token, DealUpdateDto dealUpdateDto) {
        String email = jwtService.findEmailByJwt(token);

        Member member = memberRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new);

        Deal deal = dealRepository.fetchById(dealUpdateDto.getDeal_id()).orElseThrow(DealNotFoundException::new);

        checkMember(member, deal.getMember());

        deal.updateDeal(dealUpdateDto);

        if(dealUpdateDto.getImagePaths() != null) {
            if (deal.getImagePaths() != null) {
                deal.getImagePaths().forEach(dealImage -> dealImage.delete());
                deal.getImagePaths().clear();
            }

            List<DealImage> dealImageList = new ArrayList<>();
            dealUpdateDto.getImagePaths().forEach(imagePath ->
                    dealImageList.add(
                            DealImage.builder()
                                    .imagePath(imagePath)
                                    .deal(deal)
                                    .build()
                    ));

            dealImageRepository.saveAll(dealImageList);
            dealImageList.forEach(dealImage -> deal.addImage(dealImage));
        }
        return deal;
    }

    private void checkMember(Member updater, Member writer) {
        if(updater.getEmail().equals(writer.getEmail()))
            return ;
        throw new BusinessLogicException(ErrorCodeType.USER_UNAUTHORIZED);
    }
}
