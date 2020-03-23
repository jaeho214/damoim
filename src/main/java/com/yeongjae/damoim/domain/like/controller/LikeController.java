package com.yeongjae.damoim.domain.like.controller;

import com.yeongjae.damoim.domain.like.dto.LikeGetDto;
import com.yeongjae.damoim.domain.like.service.LikeCreateService;
import com.yeongjae.damoim.domain.like.service.LikeDeleteService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/damoim/like")
public class LikeController {
    private final LikeCreateService likeCreateService;
    private final LikeDeleteService likeDeleteService;


    @ApiOperation("게시물 좋아요")
    @PostMapping("/{board_id}")
    public ResponseEntity createLike(@RequestHeader("token") String token,
                                     @PathVariable Long board_id){
        LikeGetDto savedLike = likeCreateService.createLike(token, board_id);
        return ResponseEntity.created(URI.create("/damoim/like" + savedLike.getId())).body(savedLike);
    }

    @ApiOperation("게시물 좋아요 취소")
    @DeleteMapping("/{board_id}")
    public ResponseEntity deleteLike(@RequestHeader("token") String token,
                                     @PathVariable Long board_id){
        return likeDeleteService.deleteLike(token, board_id);
    }
}
