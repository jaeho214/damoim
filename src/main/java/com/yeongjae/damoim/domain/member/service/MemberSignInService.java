package com.yeongjae.damoim.domain.member.service;

import com.yeongjae.damoim.domain.member.dto.MemberSignInDto;
import com.yeongjae.damoim.domain.member.entity.Member;
import com.yeongjae.damoim.domain.member.exception.MemberNotFoundException;
import com.yeongjae.damoim.domain.member.exception.WrongPasswordException;
import com.yeongjae.damoim.domain.member.repository.MemberRepository;
import com.yeongjae.damoim.global.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberSignInService {

    private final MemberRepository memberRepository;
    private final JwtService jwtService;

    @Transactional(readOnly = true)
    public String signIn(MemberSignInDto memberSignInDto) {
        Member member = memberRepository.findByEmail(memberSignInDto.getEmail()).orElseThrow(MemberNotFoundException::new);

        checkPw(member, memberSignInDto);

        return jwtService.createJwt(member.getEmail());
    }

    private void checkPw(Member member, MemberSignInDto memberSignInDto) {
        if(member.getPassword().equals(memberSignInDto.getPassword()))
            return;
        throw new WrongPasswordException();
    }
}
