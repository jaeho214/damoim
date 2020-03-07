package com.yeongjae.damoim.domain.member.service;

import com.yeongjae.damoim.domain.member.dto.MemberSignInDto;
import com.yeongjae.damoim.domain.member.entity.Member;
import com.yeongjae.damoim.domain.member.exception.MemberNotFoundException;
import com.yeongjae.damoim.domain.member.exception.WrongPasswordException;
import com.yeongjae.damoim.domain.member.repository.MemberRepository;
import com.yeongjae.damoim.global.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberSignInService {

    private final MemberRepository memberRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public String signIn(MemberSignInDto memberSignInDto) {
        Member member = memberRepository.findByEmail(memberSignInDto.getEmail()).orElseThrow(MemberNotFoundException::new);

        checkPw(memberSignInDto, member);

        return jwtService.createJwt(member.getEmail());
    }

    private void checkPw(MemberSignInDto memberSignInDto, Member member) {
        if(passwordEncoder.matches(memberSignInDto.getPassword(), member.getPassword()))
            return;
        throw new WrongPasswordException();
    }
}
