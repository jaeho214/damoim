package com.yeongjae.damoim.domain.member.service;

import com.yeongjae.damoim.domain.member.entity.Member;
import com.yeongjae.damoim.domain.member.exception.MemberNotFoundException;
import com.yeongjae.damoim.domain.member.repository.MemberRepository;
import com.yeongjae.damoim.global.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberGetService {

    private final MemberRepository memberRepository;
    private final JwtService jwtService;

    @Transactional(readOnly = true)
    public Member getMember(String token){
        String email = jwtService.findEmailByJwt(token);
        return memberRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new);
    }

    public boolean checkEmail(String email) {
        if(!memberRepository.existsByEmail(email))
            return true;
        return false;
    }
}
