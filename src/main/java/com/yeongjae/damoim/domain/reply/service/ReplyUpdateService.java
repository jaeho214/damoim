package com.yeongjae.damoim.domain.reply.service;

import com.yeongjae.damoim.domain.member.entity.Member;
import com.yeongjae.damoim.domain.member.exception.MemberNotFoundException;
import com.yeongjae.damoim.domain.member.repository.MemberRepository;
import com.yeongjae.damoim.domain.reply.dto.ReplyUpdateDto;
import com.yeongjae.damoim.domain.reply.entity.Reply;
import com.yeongjae.damoim.domain.reply.exception.ReplyNotFoundException;
import com.yeongjae.damoim.domain.reply.repository.ReplyRepository;
import com.yeongjae.damoim.global.error.ErrorCodeType;
import com.yeongjae.damoim.global.error.exception.BusinessLogicException;
import com.yeongjae.damoim.global.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReplyUpdateService {

    private final ReplyRepository replyRepository;
    private final MemberRepository memberRepository;
    private final JwtService jwtService;


    public Reply updateReply(String token, ReplyUpdateDto replyUpdateDto) {
        String email = jwtService.findEmailByJwt(token);
        Member member = memberRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new);
        Reply reply = replyRepository.findById(replyUpdateDto.getReply_id()).orElseThrow(ReplyNotFoundException::new);

        checkMember(member, reply.getMember());

        reply.updateReply(replyUpdateDto.getContent());

        return reply;
    }

    private void checkMember(Member member, Member writer) {
        if(member.getEmail().equals(writer.getEmail()))
            return;
        throw new BusinessLogicException(ErrorCodeType.USER_UNAUTHORIZED);
    }
}
