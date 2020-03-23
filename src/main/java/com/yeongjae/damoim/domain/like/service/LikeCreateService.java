package com.yeongjae.damoim.domain.like.service;


import com.yeongjae.damoim.domain.board.entity.Board;
import com.yeongjae.damoim.domain.board.exception.BoardNotFoundException;
import com.yeongjae.damoim.domain.board.repository.BoardRepository;
import com.yeongjae.damoim.domain.like.dto.LikeGetDto;
import com.yeongjae.damoim.domain.like.entity.BoardLike;
import com.yeongjae.damoim.domain.like.repository.LikeRepository;
import com.yeongjae.damoim.domain.member.entity.Member;
import com.yeongjae.damoim.global.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeCreateService {

    private final JwtService jwtService;
    private final BoardRepository boardRepository;
    private final LikeRepository likeRepository;

    public LikeGetDto createLike(String token, Long board_id) {
        Member member = jwtService.findMemberByToken(token);

        Board board = boardRepository.findById(board_id).orElseThrow(BoardNotFoundException::new);

        BoardLike like = BoardLike.builder()
                .board(board)
                .member(member)
                .build();

        BoardLike savedLike = likeRepository.save(like);

        return LikeGetDto.toDto(savedLike);
    }
}
