package com.yeongjae.damoim.domain.board.service;

import com.yeongjae.damoim.domain.board.dto.BoardUpdateDto;
import com.yeongjae.damoim.domain.board.entity.Board;
import com.yeongjae.damoim.domain.board.entity.BoardImage;
import com.yeongjae.damoim.domain.board.exception.BoardNotFoundException;
import com.yeongjae.damoim.domain.board.repository.BoardImageRepository;
import com.yeongjae.damoim.domain.board.repository.BoardRepository;
import com.yeongjae.damoim.domain.member.entity.Member;
import com.yeongjae.damoim.domain.member.exception.MemberNotFoundException;
import com.yeongjae.damoim.domain.member.repository.MemberRepository;
import com.yeongjae.damoim.global.error.ErrorCodeType;
import com.yeongjae.damoim.global.error.exception.BusinessLogicException;
import com.yeongjae.damoim.global.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardUpdateService {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final BoardImageRepository boardImageRepository;
    private final JwtService jwtService;

    public Board updateBoard(String token, BoardUpdateDto boardUpdateDto) {
        String email = jwtService.findEmailByJwt(token);

        Member member = memberRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new);
        Board board = boardRepository.fetchBoardById(boardUpdateDto.getBoard_id()).orElseThrow(BoardNotFoundException::new);

        checkMember(member, board.getMember());

        board.updateBoard(boardUpdateDto);

        if(boardUpdateDto.getImagePaths() != null) {

            if (board.getImagePaths() != null) {
                board.getImagePaths().forEach(image -> image.delete());
                board.getImagePaths().clear();
            }
            List<BoardImage> boardImageList = new ArrayList<>();
            boardUpdateDto.getImagePaths().forEach(imagePath ->
                    boardImageList.add(
                            BoardImage.builder()
                                    .imagePath(imagePath)
                                    .board(board)
                                    .build()
                    ));

            boardImageRepository.saveAll(boardImageList);

            boardImageList.forEach(image -> {
                board.addImage(image);
            });

        }


        return board;
    }

    private void checkMember(Member member, Member writer) {
        if(member.getEmail().equals(writer.getEmail()))
            return;
        throw new BusinessLogicException(ErrorCodeType.USER_UNAUTHORIZED);
    }
}
