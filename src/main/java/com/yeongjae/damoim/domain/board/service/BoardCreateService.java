package com.yeongjae.damoim.domain.board.service;

import com.yeongjae.damoim.domain.board.dto.BoardCreateDto;
import com.yeongjae.damoim.domain.board.dto.BoardImageCreateDto;
import com.yeongjae.damoim.domain.board.entity.Board;
import com.yeongjae.damoim.domain.board.entity.BoardImage;
import com.yeongjae.damoim.domain.board.repository.BoardImageRepository;
import com.yeongjae.damoim.domain.board.repository.BoardRepository;
import com.yeongjae.damoim.domain.member.entity.Member;
import com.yeongjae.damoim.domain.member.repository.MemberRepository;
import com.yeongjae.damoim.global.jwt.JwtService;
import lombok.RequiredArgsConstructor;
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
    private final BoardImageRepository boardImageRepository;
    private final MemberRepository memberRepository;
    private final JwtService jwtService;

    public Board createBoard(String token, BoardCreateDto boardCreateDto) {
        String email = jwtService.findEmailByJwt(token);
        Member member = memberRepository.findByEmail(email).orElseThrow(MethodNotFoundException::new);

        Board board = boardCreateDto.of(member);

        List<BoardImage> boardImageList = new ArrayList<>();
        boardCreateDto.getImagePaths().forEach(imagePath ->
                boardImageList.add(
                        BoardImage.builder()
                        .imagePath(imagePath)
                        .board(board)
                        .build()
                ));

        boardImageList.stream()
                .forEach(boardImage -> board.addImage(boardImage));

        boardImageRepository.saveAll(boardImageList);

        return boardRepository.save(board);
    }
}
