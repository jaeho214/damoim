package com.yeongjae.damoim.domain.deal.service;

import com.yeongjae.damoim.domain.deal.dto.DealCreateDto;
import com.yeongjae.damoim.domain.deal.dto.DealGetDto;
import com.yeongjae.damoim.domain.deal.entity.Deal;
import com.yeongjae.damoim.domain.deal.entity.DealImage;
import com.yeongjae.damoim.domain.deal.repository.DealImageRepository;
import com.yeongjae.damoim.domain.deal.repository.DealRepository;
import com.yeongjae.damoim.domain.keyword.entity.Keyword;
import com.yeongjae.damoim.domain.keyword.repository.KeywordRepository;
import com.yeongjae.damoim.domain.member.dto.MemberGetDto;
import com.yeongjae.damoim.domain.member.entity.Member;
import com.yeongjae.damoim.domain.member.exception.MemberNotFoundException;
import com.yeongjae.damoim.domain.member.repository.MemberRepository;
import com.yeongjae.damoim.global.jwt.JwtService;
import com.yeongjae.damoim.global.notification.dto.NotificationType;
import com.yeongjae.damoim.global.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class DealCreateService {

    private final DealRepository dealRepository;
    private final KeywordRepository keywordRepository;
    private final DealImageCreateService dealImageCreateService;
    private final NotificationService notificationService;
    private final JwtService jwtService;

    public DealGetDto createDeal(String token, DealCreateDto dealCreateDto) {
        Member member = jwtService.findMemberByToken(token);

        Deal deal = dealCreateDto.of(member);

        if(dealCreateDto.getImagePaths() != null) {
            Deal savedDeal = dealImageCreateService.saveDealImage(dealCreateDto, deal);
            sendNotification(savedDeal, deal.getLocation());
            return DealGetDto.toDto(savedDeal);
        }

        Deal savedDeal = dealRepository.save(deal);
        sendNotification(savedDeal, deal.getLocation());


        return DealGetDto.toDto(savedDeal);
    }

    private void sendNotification(Deal deal, String location){
        List<String> keywordList = keywordRepository.findDistinctKeywordAll().stream()
                .filter(keyword -> deal.getTitle().contains(keyword))
                .collect(Collectors.toList());

        for(String keyword : keywordList){
            List<String> tokenList = keywordRepository.findAllMemberByKeywordAndLocation(keyword, location);
            notificationService.sendNotification(tokenList, keyword, NotificationType.KEYWORD);
        }
    }
}
