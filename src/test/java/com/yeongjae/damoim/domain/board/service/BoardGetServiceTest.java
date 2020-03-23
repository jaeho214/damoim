package com.yeongjae.damoim.domain.board.service;

import com.yeongjae.damoim.domain.board.dto.BoardGetByLocationDto;
import com.yeongjae.damoim.domain.board.dto.BoardGetByMemberDto;
import com.yeongjae.damoim.domain.board.dto.BoardGetDto;
import com.yeongjae.damoim.domain.board.dto.BoardGetPagingDto;
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
import java.util.HashSet;
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
            .imagePaths(new HashSet<>())
            .boardLikeList(new ArrayList<>())
            .location("강원도_강릉시")
            .replyList(new HashSet<>())
            .hits(0L)
            .build();
    private String email = "email@eamil.com";
    private String token = "token";
    private Page<Board> boardPage = Mockito.mock(Page.class);
    private Page<BoardGetByLocationDto> boardGetByLocationPage = Mockito.mock(Page.class);
    private Page<BoardGetByMemberDto> boardGetByMemberDtoPage = Mockito.mock(Page.class);

    @Test
    void getBoards() {
        //given
        given(jwtService.findMemberByToken(anyString())).willReturn(member);
        given(boardRepository.findByLocation(anyString(), any())).willReturn(boardGetByLocationPage);

        //when
        BoardGetPagingDto boardList = boardGetService.getBoards(token,1, "강원도_강릉시");

        //then
        assertThat(boardList.getBoardGetByLocationDtoList().size()).isEqualTo(boardGetByLocationPage.getSize());
    }

    @Test
    void getBoard() {
        //given
        given(jwtService.findMemberByToken(anyString())).willReturn(member);
        given(boardRepository.fetchBoardById(anyLong())).willReturn(Optional.ofNullable(board));

        //when
        BoardGetDto getBoard = boardGetService.getBoard("token", 1L);

        //then
        assertThat(getBoard.getTitle()).isEqualTo(board.getTitle());
        assertThat(getBoard.getContent()).isEqualTo(board.getContent());
        assertThat(getBoard.getReplyList().size()).isEqualTo(board.getReplyList().size());
    }

    @Test
    void getBoardByMember(){
        //given
        given(jwtService.findMemberByToken(anyString())).willReturn(member);
        given(boardRepository.findByMember(any(Member.class), any())).willReturn(boardGetByMemberDtoPage);

        //when
        BoardGetPagingDto boardList = boardGetService.getBoardByMember("token", 1);

        //then
        assertThat(boardList.getBoardGetByMemberDtoList().size()).isEqualTo(boardGetByMemberDtoPage.getSize());
    }

    @Test
    void searchByKeyword(){
        //given
        given(jwtService.findMemberByToken(anyString())).willReturn(member);
        given(boardRepository.searchByKeyword(anyString(),anyString(),any())).willReturn(boardGetByLocationPage);

        //when
        BoardGetPagingDto boardList = boardGetService.searchByKeyword("token", "강원도_강릉시", "키워드", 1);

        //then
        assertThat(boardList.getBoardGetByLocationDtoList().size()).isEqualTo(boardGetByLocationPage.getSize());
    }
}