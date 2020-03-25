package com.yeongjae.damoim.domain.reply.service;

import com.yeongjae.damoim.domain.board.entity.Board;
import com.yeongjae.damoim.domain.board.exception.BoardNotFoundException;
import com.yeongjae.damoim.domain.board.repository.BoardRepository;
import com.yeongjae.damoim.domain.member.entity.Member;
import com.yeongjae.damoim.domain.member.exception.MemberNotFoundException;
import com.yeongjae.damoim.domain.member.repository.MemberRepository;
import com.yeongjae.damoim.domain.reply.dto.ReplyCreateDto;
import com.yeongjae.damoim.domain.reply.dto.ReplyGetDto;
import com.yeongjae.damoim.domain.reply.entity.Reply;
import com.yeongjae.damoim.domain.reply.repository.ReplyRepository;
import com.yeongjae.damoim.global.jwt.JwtService;
import com.yeongjae.damoim.global.notification.dto.NotificationType;
import com.yeongjae.damoim.global.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
@Transactional
@RequiredArgsConstructor
public class ReplyCreateService {

    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;
    private final JwtService jwtService;
    private final NotificationService notificationService;

    public ReplyGetDto createReply(String token, ReplyCreateDto replyCreateDto) {
        Member member = jwtService.findMemberByToken(token);
        Board board = boardRepository.findById(replyCreateDto.getBoard_id()).orElseThrow(BoardNotFoundException::new);

        //board.addReply(replyCreateDto.of(member, board));
        Reply savedReply = replyRepository.save(replyCreateDto.of(member, board));

        notificationService.sendNotification(Arrays.asList(savedReply.getBoard().getMember().getFcmToken()), member.getNickName(), NotificationType.REPLY);

        return ReplyGetDto.toDto(savedReply);
    }
}
