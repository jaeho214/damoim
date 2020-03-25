package com.yeongjae.damoim.domain.board.service;

import com.yeongjae.damoim.domain.board.dto.BoardCreateDto;
import com.yeongjae.damoim.domain.board.dto.BoardGetDto;
import com.yeongjae.damoim.domain.board.entity.Board;
import com.yeongjae.damoim.domain.board.repository.BoardRepository;
import com.yeongjae.damoim.domain.member.entity.Member;
import com.yeongjae.damoim.global.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardCreateService {

    private final BoardRepository boardRepository;
    private final JwtService jwtService;
    private final BoardImageCreateService boardImageCreateService;

    public BoardGetDto createBoard(String token, BoardCreateDto boardCreateDto) {
        Member member = jwtService.findMemberByToken(token);
        Board board = boardCreateDto.of(member);

        if(boardCreateDto.getImagePaths() != null) {
            Board savedBoard = boardImageCreateService.saveBoardImage(boardCreateDto, board);
            return BoardGetDto.toDto(savedBoard, new HashSet<>());
        }
        Board savedBoard = boardRepository.save(board);
        return BoardGetDto.toDto(savedBoard, new HashSet<>());
    }
}
