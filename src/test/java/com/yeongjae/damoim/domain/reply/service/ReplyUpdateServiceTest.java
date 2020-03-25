package com.yeongjae.damoim.domain.reply.service;

import com.yeongjae.damoim.domain.board.entity.Board;
import com.yeongjae.damoim.domain.member.entity.Member;
import com.yeongjae.damoim.domain.member.repository.MemberRepository;
import com.yeongjae.damoim.domain.reply.dto.ReplyGetDto;
import com.yeongjae.damoim.domain.reply.dto.ReplyUpdateDto;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ReplyUpdateServiceTest {

    @InjectMocks
    private ReplyUpdateService replyUpdateService;
    @Mock
    private JwtService jwtService;
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private ReplyRepository replyRepository;

    private ReplyUpdateDto replyUpdateDtoFixture = new EasyRandom().nextObject(ReplyUpdateDto.class);
    private Member memberFixture = new EasyRandom().nextObject(Member.class);
    private Board boardFixture = new EasyRandom().nextObject(Board.class);
    private Reply replyFixture = Reply.builder()
            .board(boardFixture)
            .member(memberFixture)
            .content("content")
            .build();
    private String email = "email@email.com";

    @Test
    void updateReply() {
        //given
        given(jwtService.findMemberByToken(anyString())).willReturn(memberFixture);
        given(replyRepository.findById(anyLong())).willReturn(Optional.ofNullable(replyFixture));

        //when
        ReplyGetDto updateReply = replyUpdateService.updateReply("token", replyUpdateDtoFixture);

        //then
        assertThat(updateReply.getContent()).isEqualTo(replyFixture.getContent());
    }
}