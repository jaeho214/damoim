package com.yeongjae.damoim.domain.board.service;

import com.yeongjae.damoim.domain.board.entity.Board;
import com.yeongjae.damoim.domain.board.exception.BoardNotFoundException;
import com.yeongjae.damoim.domain.board.repository.BoardRepository;
import com.yeongjae.damoim.domain.member.entity.Member;
import com.yeongjae.damoim.domain.member.exception.MemberNotFoundException;
import com.yeongjae.damoim.domain.member.repository.MemberRepository;
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

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardGetService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final JwtService jwtService;

    @Transactional(readOnly = true)
    public List<Board> getBoards(String token, int pageNo, String location) {
        String email = jwtService.findEmailByJwt(token);
        Member member = memberRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new);

        if(!member.getIsVerified() || member.getLocation().equals(location))
            throw new BusinessLogicException(ErrorCodeType.USER_UNAUTHORIZED);

        Pageable pageable = PageRequest.of(pageNo, 15, Sort.Direction.DESC, "createdAt");
        Page<Board> boards = boardRepository.findByLocation(location, pageable);

        if(boards == null)
            throw new BoardNotFoundException();

        return boards.stream()
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Board getBoard(String token, Long id){
        String email = jwtService.findEmailByJwt(token);
        Member member = memberRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new);

        Board board = boardRepository.fetchBoardById(id).orElseThrow(BoardNotFoundException::new);

        if(!member.getIsVerified() || !member.getLocation().equals(board.getLocation()))
            throw new BusinessLogicException(ErrorCodeType.USER_UNAUTHORIZED);

        board.updateHits();

        return board;
    }
}
