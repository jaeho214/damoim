package com.yeongjae.damoim.domain.enjoy.service;

import com.yeongjae.damoim.domain.enjoy.dto.EnjoyCreateDto;
import com.yeongjae.damoim.domain.enjoy.dto.EnjoyGetDto;
import com.yeongjae.damoim.domain.enjoy.entity.Enjoy;
import com.yeongjae.damoim.domain.enjoy.repository.EnjoyRepository;
import com.yeongjae.damoim.domain.member.dto.MemberGetDto;
import com.yeongjae.damoim.domain.member.entity.Member;
import com.yeongjae.damoim.domain.member.exception.MemberNotFoundException;
import com.yeongjae.damoim.domain.member.repository.MemberRepository;
import com.yeongjae.damoim.global.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class EnjoyCreateService {

    private final EnjoyRepository enjoyRepository;
    private final JwtService jwtService;

    public EnjoyGetDto createEnjoy(String token, EnjoyCreateDto enjoyCreateDto) {
        Member member = jwtService.findMemberByToken(token);
        Enjoy savedEnjoy = enjoyRepository.save(enjoyCreateDto.of(member));

        return EnjoyGetDto.toDto(savedEnjoy, MemberGetDto.toDto(member));
    }
}
