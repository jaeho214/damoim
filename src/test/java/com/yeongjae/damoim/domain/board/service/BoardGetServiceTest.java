package com.yeongjae.damoim.domain.board.service;

import com.yeongjae.damoim.domain.board.entity.Board;
import com.yeongjae.damoim.domain.board.repository.BoardRepository;
import com.yeongjae.damoim.domain.member.entity.Member;
import com.yeongjae.damoim.domain.member.repository.MemberRepository;
import com.yeongjae.damoim.global.jwt.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class BoardGetServiceTest {

    @Mock
    private BoardRepository boardRepository;
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private JwtService jwtService;
    @InjectMocks
    private BoardGetService boardGetService;

    private Member member = Member.builder()
            .email("email")
            .birth("0214")
            .password("1234")
            .isVerified(true)
            .location("강원도_강릉시")
            .build();
    private Board board = Board.builder()
            .member(member)
            .title("제목")
            .content("content")
            .imagePaths(new ArrayList<>())
            .location("강원도_강릉시")
            .hits(0L)
            .build();
    private String email = "email@eamil.com";
    private String token = "token";
    private Page<Board> boardPage = Mockito.mock(Page.class);

    @Test
    void getBoards() {
        //given
        given(jwtService.findEmailByJwt(anyString())).willReturn(email);
        given(memberRepository.findByEmail(anyString())).willReturn(Optional.of(member));
        given(boardRepository.findByLocation(anyString(), any())).willReturn(boardPage);

        //when
        List<Board> boardList = boardGetService.getBoards(token,1, "강원도_강릉시");

        //then
        assertThat(boardList.size()).isEqualTo(boardPage.getSize());
    }

    @Test
    void getBoard() {
        //given
        given(jwtService.findEmailByJwt(anyString())).willReturn(email);
        given(memberRepository.findByEmail(anyString())).willReturn(Optional.of(member));
        given(boardRepository.fetchBoardById(anyLong())).willReturn(Optional.ofNullable(board));

        //when
        Board getBoard = boardGetService.getBoard("token", 1L);

        //then
        assertThat(getBoard.getTitle()).isEqualTo(board.getTitle());
        assertThat(getBoard.getContent()).isEqualTo(board.getContent());
    }
}