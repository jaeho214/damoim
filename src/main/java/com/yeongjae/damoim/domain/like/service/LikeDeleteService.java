package com.yeongjae.damoim.domain.like.service;

import com.yeongjae.damoim.domain.like.entity.BoardLike;
import com.yeongjae.damoim.domain.like.exception.LikeNotFoundException;
import com.yeongjae.damoim.domain.like.repository.LikeRepository;
import com.yeongjae.damoim.domain.member.entity.Member;
import com.yeongjae.damoim.global.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeDeleteService {

    private final JwtService jwtService;
    private final LikeRepository likeRepository;

    public ResponseEntity deleteLike(String token, Long board_id) {
        Member member = jwtService.findMemberByToken(token);

        BoardLike like = likeRepository.findByMember_IdAndBoard_Id(member.getId(), board_id)
                .orElseThrow(LikeNotFoundException::new);

        likeRepository.delete(like);

        return ResponseEntity.noContent().build();
    }
}
