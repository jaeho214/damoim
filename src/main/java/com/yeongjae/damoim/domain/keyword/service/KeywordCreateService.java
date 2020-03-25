package com.yeongjae.damoim.domain.keyword.service;

import com.yeongjae.damoim.domain.keyword.dto.KeywordGetDto;
import com.yeongjae.damoim.domain.keyword.entity.Keyword;
import com.yeongjae.damoim.domain.keyword.repository.KeywordRepository;
import com.yeongjae.damoim.domain.member.entity.Member;
import com.yeongjae.damoim.global.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class KeywordCreateService {

    private final JwtService jwtService;
    private final KeywordRepository keywordRepository;

    public KeywordGetDto createKeyword(String token, String keyword) {
        Member member = jwtService.findMemberByToken(token);

        Keyword savedKeyword = Keyword.builder()
                .keyword(keyword)
                .member(member)
                .build();
        keywordRepository.save(savedKeyword);
        member.addKeyword(savedKeyword);

        return KeywordGetDto.toDto(savedKeyword);
    }
}
