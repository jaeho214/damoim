package com.yeongjae.damoim.domain.member.service;

import com.yeongjae.damoim.domain.member.dto.MemberUpdateDto;
import com.yeongjae.damoim.domain.member.entity.Member;
import com.yeongjae.damoim.domain.member.exception.MemberNotFoundException;
import com.yeongjae.damoim.domain.member.exception.WrongPasswordException;
import com.yeongjae.damoim.domain.member.repository.MemberRepository;
import com.yeongjae.damoim.global.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberUpdateService {

    private final MemberRepository memberRepository;
    private final JwtService jwtService;

    public Member updateMember(String token, MemberUpdateDto memberUpdateDto) {
        String email = jwtService.findEmailByJwt(token);

        Member member = memberRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new);

        checkPw(member, memberUpdateDto);

        member.updateMember(memberUpdateDto);

        return member;
    }

    private void checkPw(Member member, MemberUpdateDto memberUpdateDto) {
        if(member.getPassword().equals(memberUpdateDto.getPassword()))
            return;
        throw new WrongPasswordException();
    }
}
