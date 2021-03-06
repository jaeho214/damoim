package com.yeongjae.damoim.domain.board.service;

import com.yeongjae.damoim.domain.board.dto.BoardGetByLocationDto;
import com.yeongjae.damoim.domain.board.dto.BoardGetByMemberDto;
import com.yeongjae.damoim.domain.board.dto.BoardGetDto;
import com.yeongjae.damoim.domain.board.dto.BoardGetPagingDto;
import com.yeongjae.damoim.domain.board.entity.Board;
import com.yeongjae.damoim.domain.board.exception.BoardNotFoundException;
import com.yeongjae.damoim.domain.board.repository.BoardRepository;
import com.yeongjae.damoim.domain.member.entity.Member;
import com.yeongjae.damoim.domain.reply.dto.ReplyGetDto;
import com.yeongjae.damoim.global.error.ErrorCodeType;
import com.yeongjae.damoim.global.error.exception.BusinessLogicException;
import com.yeongjae.damoim.global.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardGetService {

    private final int LIMIT = 10;

    private final BoardRepository boardRepository;
    private final JwtService jwtService;

    @Transactional(readOnly = true)
    //@Cacheable(value = CacheKey.BOARDS, key = "#location", unless = "#result==null")
    public BoardGetPagingDto getBoards(String token, int pageNo, String location) {
        Member member = jwtService.findMemberByToken(token);

        if(!member.getIsVerified() || !member.getLocation().equals(location))
            throw new BusinessLogicException(ErrorCodeType.USER_UNAUTHORIZED);

        Pageable pageable = PageRequest.of(--pageNo * LIMIT, 10, Sort.Direction.DESC, "createdAt");
        Page<BoardGetByLocationDto> boardPage = boardRepository.findByLocation(location, pageable);


        if(boardPage == null)
            throw new BoardNotFoundException();

        return BoardGetPagingDto.locationOf(boardPage);
    }

    @Transactional
    //@Cacheable(value = CacheKey.BOARD, key = "#board_id", unless = "#result==null")
    public BoardGetDto getBoard(String token, Long board_id){
        Member member = jwtService.findMemberByToken(token);

        Board board = boardRepository.fetchBoardById(board_id).orElseThrow(BoardNotFoundException::new);

        if(!member.getIsVerified() || !member.getLocation().equals(board.getLocation()))
            throw new BusinessLogicException(ErrorCodeType.USER_UNAUTHORIZED);

        board.updateHits();

        return BoardGetDto.toDto(board, ReplyGetDto.toDtoSet(board.getReplyList()));
    }

    public BoardGetPagingDto getBoardByMember(String token, int pageNo) {
        Member member = jwtService.findMemberByToken(token);

        Pageable pageable = PageRequest.of(--pageNo * LIMIT, 10, Sort.Direction.DESC, "createdAt");
        Page<BoardGetByMemberDto> boardPage = boardRepository.findByMember(member, pageable);

        return BoardGetPagingDto.memberOf(boardPage);
    }

    public BoardGetPagingDto searchByKeyword(String token, String location, String keyword, int pageNo) {
        Member member = jwtService.findMemberByToken(token);

        if(!member.getIsVerified() || !member.getLocation().equals(location))
            throw new BusinessLogicException(ErrorCodeType.USER_UNAUTHORIZED);

        Pageable pageable = PageRequest.of(--pageNo * LIMIT, 10, Sort.Direction.DESC, "createdAt");
        Page<BoardGetByLocationDto> boardPage = boardRepository.searchByKeyword(keyword, location, pageable);

        return BoardGetPagingDto.locationOf(boardPage);
    }

}
