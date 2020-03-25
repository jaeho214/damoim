package com.yeongjae.damoim.domain.keyword.service;

import com.yeongjae.damoim.domain.keyword.entity.Keyword;
import com.yeongjae.damoim.domain.keyword.exception.KeywordNotFoundException;
import com.yeongjae.damoim.domain.keyword.repository.KeywordRepository;
import com.yeongjae.damoim.domain.member.entity.Member;
import com.yeongjae.damoim.global.error.ErrorCodeType;
import com.yeongjae.damoim.global.error.exception.BusinessLogicException;
import com.yeongjae.damoim.global.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KeywordDeleteService {

    private final JwtService jwtService;
    private final KeywordRepository keywordRepository;

    public ResponseEntity deleteKeyword(String token, Long id) {
        Member member = jwtService.findMemberByToken(token);

        Keyword keyword = keywordRepository.findById(id).orElseThrow(KeywordNotFoundException::new);

        if(!keyword.getKeyword().equals(member))
            throw new BusinessLogicException(ErrorCodeType.USER_UNAUTHORIZED);

        keywordRepository.delete(keyword);

        return ResponseEntity.noContent().build();
    }

}
