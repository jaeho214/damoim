package com.yeongjae.damoim.domain.member.service;

import com.yeongjae.damoim.domain.member.dto.MemberGetDto;
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

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberUpdateService {

    private final JwtService jwtService;

    public MemberGetDto updateMember(String token, MemberUpdateDto memberUpdateDto) {
        Member member = jwtService.findMemberByToken(token);

        checkPw(member, memberUpdateDto);

        member.updateMember(memberUpdateDto);

        return MemberGetDto.toDto(member);
    }

    private void checkPw(Member member, MemberUpdateDto memberUpdateDto) {
        if(member.getPassword().equals(memberUpdateDto.getPassword()))
            return;
        throw new WrongPasswordException();
    }



}
