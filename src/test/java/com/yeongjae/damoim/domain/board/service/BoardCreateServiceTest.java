package com.yeongjae.damoim.domain.board.service;

import com.yeongjae.damoim.domain.board.dto.BoardCreateDto;
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

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class BoardCreateServiceTest {

    @Mock
    private BoardRepository boardRepository;

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private BoardCreateService boardCreateService;

    @Mock
    private JwtService jwtService;

    private BoardCreateDto boardCreateDto = BoardCreateDto.builder()
            .title("제목")
            .content("내용")
            .location("지역")
            .build();

    private Member member = Member.builder()
            .email("email@gmail.com")
            .password("1")
            .location("강원도_강릉시")
            .isVerified(false)
            .nickName("닉넴")
            .sex("male")
            .phone("010-1111-2222")
            .build();

    private String token = "token";
    private String email = "email";
    @Test
    void createBoard() {
        //given
        Board board = boardCreateDto.of(member);
        given(boardRepository.save(board)).willReturn(board);
        given(jwtService.findMemberByToken(any(String.class))).willReturn(member);

        //when
        Board savedBoard = boardCreateService.createBoard(token, boardCreateDto);

        //then
        assertThat(savedBoard.getContent()).isEqualTo(board.getContent());
        assertThat(savedBoard.getTitle()).isEqualTo(board.getTitle());
    }
}