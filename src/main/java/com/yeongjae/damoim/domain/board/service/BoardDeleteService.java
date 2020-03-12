package com.yeongjae.damoim.domain.board.service;

import com.yeongjae.damoim.domain.board.entity.Board;
import com.yeongjae.damoim.domain.board.exception.BoardNotFoundException;
import com.yeongjae.damoim.domain.board.repository.BoardRepository;
import com.yeongjae.damoim.domain.member.entity.Member;
import com.yeongjae.damoim.domain.member.exception.MemberNotFoundException;
import com.yeongjae.damoim.domain.member.repository.MemberRepository;
import com.yeongjae.damoim.global.config.CacheKey;
import com.yeongjae.damoim.global.error.ErrorCodeType;
import com.yeongjae.damoim.global.error.exception.BusinessLogicException;
import com.yeongjae.damoim.global.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardDeleteService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final JwtService jwtService;

    @CacheEvict(value = CacheKey.BOARD, key = "#board_id")
    public ResponseEntity deleteBoard(String token, Long board_id) {
        String email = jwtService.findEmailByJwt(token);

        Member member = memberRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new);
        Board board = boardRepository.findById(board_id).orElseThrow(BoardNotFoundException::new);

        checkMember(member, board.getMember());

        board.delete();

        return ResponseEntity.noContent().build();
    }

    private void checkMember(Member member, Member writer) {
        if(member.getEmail().equals(writer.getEmail()))
            return;
        throw new BusinessLogicException(ErrorCodeType.USER_UNAUTHORIZED);
    }
}
