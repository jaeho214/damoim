package com.yeongjae.damoim.domain.keyword.service;

import com.yeongjae.damoim.domain.keyword.dto.KeywordGetDto;
import com.yeongjae.damoim.domain.keyword.entity.Keyword;
import com.yeongjae.damoim.domain.keyword.repository.KeywordRepository;
import com.yeongjae.damoim.domain.member.entity.Member;
import com.yeongjae.damoim.global.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KeywordGetService {

    private final JwtService jwtService;
    private final KeywordRepository keywordRepository;

    @Transactional(readOnly = true)
    public List<KeywordGetDto> getKeywords(String token) {
        Member member = jwtService.findMemberByToken(token);

        List<Keyword> keywords = keywordRepository.findAllByMember(member);

        List<KeywordGetDto> keywordStringList = new ArrayList<>();
        keywords.forEach(keyword ->
                keywordStringList.add(KeywordGetDto.toDto(keyword)));
        return keywordStringList;
    }
}
