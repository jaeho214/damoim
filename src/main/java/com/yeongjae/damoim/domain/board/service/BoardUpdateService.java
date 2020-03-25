package com.yeongjae.damoim.domain.board.service;

import com.yeongjae.damoim.domain.board.dto.BoardGetDto;
import com.yeongjae.damoim.domain.board.dto.BoardUpdateDto;
import com.yeongjae.damoim.domain.board.entity.Board;
import com.yeongjae.damoim.domain.board.exception.BoardNotFoundException;
import com.yeongjae.damoim.domain.board.repository.BoardRepository;
import com.yeongjae.damoim.domain.member.entity.Member;
import com.yeongjae.damoim.domain.reply.dto.ReplyGetDto;
import com.yeongjae.damoim.global.error.ErrorCodeType;
import com.yeongjae.damoim.global.error.exception.BusinessLogicException;
import com.yeongjae.damoim.global.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardUpdateService {

    private final BoardRepository boardRepository;
    private final BoardImageUpdateService boardImageUpdateService;
    private final JwtService jwtService;

    //@CachePut(value = CacheKey.BOARD, key = "#boardUpdateDto.board_id")
    public BoardGetDto updateBoard(String token, BoardUpdateDto boardUpdateDto) {
        Member member = jwtService.findMemberByToken(token);
        Board board = boardRepository.fetchBoardById(boardUpdateDto.getBoard_id()).orElseThrow(BoardNotFoundException::new);

        checkMember(member, board.getMember());

        board.updateBoard(boardUpdateDto);

        if(boardUpdateDto.getImagePaths() != null) {
            boardImageUpdateService.saveBoardImage(boardUpdateDto, board);
        }

        return BoardGetDto.toDto(board, ReplyGetDto.toDtoSet(board.getReplyList()));
    }

    private void checkMember(Member member, Member writer) {
        if(member.getEmail().equals(writer.getEmail()))
            return;
        throw new BusinessLogicException(ErrorCodeType.USER_UNAUTHORIZED);
    }
}
