package com.yeongjae.damoim.domain.board.service;

import com.yeongjae.damoim.domain.board.dto.BoardUpdateDto;
import com.yeongjae.damoim.domain.board.entity.Board;
import com.yeongjae.damoim.domain.board.repository.BoardImageRepository;
import com.yeongjae.damoim.domain.board.repository.BoardRepository;
import com.yeongjae.damoim.domain.member.entity.Member;
import com.yeongjae.damoim.domain.member.repository.MemberRepository;
import com.yeongjae.damoim.global.jwt.JwtService;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class BoardUpdateServiceTest {

    @Mock
    private BoardRepository boardRepository;
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private JwtService jwtService;
    @Mock
    private BoardImageRepository boardImageRepository;

    @InjectMocks
    private BoardUpdateService boardUpdateService;

    private BoardUpdateDto boardUpdateDto = new EasyRandom().nextObject(BoardUpdateDto.class);
    private Member member = Member.builder()
            .email("aaa@email.com")
            .password("1234")
            .build();
    Board board = Board.builder()
            .content("내용")
            .title("제목")
            .imagePaths(new ArrayList<>())
            .member(member)
            .build();

    private String token = "token";
    private String email = "email";

    @Test
    void updateBoard() {
        //given
        given(boardRepository.fetchBoardById(anyLong())).willReturn(Optional.of(board));
        given(jwtService.findEmailByJwt(anyString())).willReturn(email);
        given(memberRepository.findByEmail(anyString())).willReturn(Optional.of(member));

        //when
        Board updatedBoard = boardUpdateService.updateBoard(token, boardUpdateDto);

        //then
        assertThat(updatedBoard.getContent()).isEqualTo(boardUpdateDto.getContent());
        assertThat(updatedBoard.getTitle()).isEqualTo(boardUpdateDto.getTitle());
    }
}