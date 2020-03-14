package com.yeongjae.damoim.domain.board.service;

import com.yeongjae.damoim.domain.board.dto.BoardUpdateDto;
import com.yeongjae.damoim.domain.board.entity.Board;
import com.yeongjae.damoim.domain.board.repository.BoardRepository;
import com.yeongjae.damoim.domain.member.entity.Member;
import com.yeongjae.damoim.domain.member.repository.MemberRepository;
import com.yeongjae.damoim.global.jwt.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class BoardDeleteServiceTest {

    @Mock
    private BoardRepository boardRepository;
    @Mock
    private BoardImageUpdateService boardImageUpdateService;
    @Mock
    private JwtService jwtService;
    @InjectMocks
    private BoardDeleteService boardDeleteService;

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
            .location("강원도_강릉시")
            .hits(0L)
            .build();
    private String email = "email@eamil.com";
    private String token = "token";

    @Test
    void deleteBoard() {
        //given
        given(jwtService.findMemberByToken(anyString())).willReturn(member);
        given(boardRepository.findById(anyLong())).willReturn(Optional.ofNullable(board));

        //when
        ResponseEntity responseEntity = boardDeleteService.deleteBoard(token, 1L);

        //then
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(204);
    }
}