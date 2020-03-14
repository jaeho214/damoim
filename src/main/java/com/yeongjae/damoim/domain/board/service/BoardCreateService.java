package com.yeongjae.damoim.domain.board.service;

import com.yeongjae.damoim.domain.board.dto.BoardCreateDto;
import com.yeongjae.damoim.domain.board.dto.BoardImageCreateDto;
import com.yeongjae.damoim.domain.board.entity.Board;
import com.yeongjae.damoim.domain.board.entity.BoardImage;
import com.yeongjae.damoim.domain.board.repository.BoardImageRepository;
import com.yeongjae.damoim.domain.board.repository.BoardRepository;
import com.yeongjae.damoim.domain.member.entity.Member;
import com.yeongjae.damoim.domain.member.exception.MemberNotFoundException;
import com.yeongjae.damoim.domain.member.repository.MemberRepository;
import com.yeongjae.damoim.global.config.CacheKey;
import com.yeongjae.damoim.global.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.el.MethodNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardCreateService {

    private final BoardRepository boardRepository;
    private final JwtService jwtService;
    private final BoardImageCreateService boardImageCreateService;

    public Board createBoard(String token, BoardCreateDto boardCreateDto) {
        Member member = jwtService.findMemberByToken(token);
        Board board = boardCreateDto.of(member);

        if(boardCreateDto.getImagePaths() != null) {
            return boardImageCreateService.saveBoardImage(boardCreateDto, board);
        }
        return boardRepository.save(board);
    }
}
