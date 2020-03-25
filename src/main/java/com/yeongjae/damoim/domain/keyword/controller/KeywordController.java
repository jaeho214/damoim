package com.yeongjae.damoim.domain.keyword.controller;

import com.yeongjae.damoim.domain.keyword.dto.KeywordGetDto;
import com.yeongjae.damoim.domain.keyword.service.KeywordCreateService;
import com.yeongjae.damoim.domain.keyword.service.KeywordDeleteService;
import com.yeongjae.damoim.domain.keyword.service.KeywordGetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/damoim/keyword")
public class KeywordController {

    private final KeywordCreateService keywordCreateService;
    private final KeywordGetService keywordGetService;
    private final KeywordDeleteService keywordDeleteService;

    @ApiOperation("키워드 알람을 위한 키워드 추가")
    @PostMapping
    public ResponseEntity createKeyword(@RequestHeader("token") String token,
                                        @RequestBody String keyword){
        KeywordGetDto savedLike = keywordCreateService.createKeyword(token, keyword);
        return ResponseEntity.created(URI.create("/damoim/keyword" + savedLike.getId())).body(savedLike);
    }

    @ApiOperation("키워드 알람 목록 조회, 최대 10개로 제한해줘")
    @GetMapping
    public ResponseEntity getKeywords(@RequestHeader("token") String token){
        return ResponseEntity.ok(keywordGetService.getKeywords(token));
    }

    @ApiOperation("키워드 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteKeyword(@RequestHeader("token") String token,
                                        @PathVariable Long id){
        return keywordDeleteService.deleteKeyword(token, id);
    }
}
