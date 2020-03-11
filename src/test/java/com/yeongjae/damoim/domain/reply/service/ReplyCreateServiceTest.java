package com.yeongjae.damoim.domain.reply.service;

import com.yeongjae.damoim.domain.board.entity.Board;
import com.yeongjae.damoim.domain.board.repository.BoardRepository;
import com.yeongjae.damoim.domain.member.entity.Member;
import com.yeongjae.damoim.domain.member.repository.MemberRepository;
import com.yeongjae.damoim.domain.reply.dto.ReplyCreateDto;
import com.yeongjae.damoim.domain.reply.entity.Reply;
import com.yeongjae.damoim.domain.reply.repository.ReplyRepository;
import com.yeongjae.damoim.global.jwt.JwtService;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ReplyCreateServiceTest {

    @InjectMocks
    private ReplyCreateService replyCreateService;
    @Mock
    private JwtService jwtService;
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private BoardRepository boardRepository;
    @Mock
    private ReplyRepository replyRepository;


    private String email = "eamil@email.com";
    private Member memberFixture = new EasyRandom().nextObject(Member.class);
    private Board boardFixture = new EasyRandom().nextObject(Board.class);
    private Reply replyFixture = new EasyRandom().nextObject(Reply.class);
    private ReplyCreateDto replyCreateDtoFixture = new EasyRandom().nextObject(ReplyCreateDto.class);

    @Test
    void createReply() {
        //given
        given(jwtService.findEmailByJwt(anyString())).willReturn(email);
        given(memberRepository.findByEmail(anyString())).willReturn(Optional.ofNullable(memberFixture));
        given(boardRepository.findById(anyLong())).willReturn(Optional.ofNullable(boardFixture));
        given(replyRepository.save(any(Reply.class))).willReturn(replyFixture);

        //when
        Reply savedReply = replyCreateService.createReply("token", replyCreateDtoFixture);

        //then
        assertThat(savedReply.getMember().getEmail()).isEqualTo(replyFixture.getMember().getEmail());
        assertThat(savedReply.getBoard().getId()).isEqualTo(replyFixture.getBoard().getId());

    }
}