package com.yeongjae.damoim.domain.enjoy.service;

import com.yeongjae.damoim.domain.enjoy.dto.EnjoyGetDto;
import com.yeongjae.damoim.domain.enjoy.dto.EnjoyUpdateDto;
import com.yeongjae.damoim.domain.enjoy.entity.Enjoy;
import com.yeongjae.damoim.domain.enjoy.exception.EnjoyNotFoundException;
import com.yeongjae.damoim.domain.enjoy.repository.EnjoyRepository;
import com.yeongjae.damoim.domain.member.dto.MemberGetDto;
import com.yeongjae.damoim.domain.member.entity.Member;
import com.yeongjae.damoim.domain.member.exception.MemberNotFoundException;
import com.yeongjae.damoim.domain.member.repository.MemberRepository;
import com.yeongjae.damoim.global.error.ErrorCodeType;
import com.yeongjae.damoim.global.error.exception.BusinessLogicException;
import com.yeongjae.damoim.global.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EnjoyUpdateService {

    private final EnjoyRepository enjoyRepository;
    private final JwtService jwtService;


    public EnjoyGetDto updateEnjoy(String token, EnjoyUpdateDto enjoyUpdateDto){
        Member member = jwtService.findMemberByToken(token);

        Enjoy enjoy = enjoyRepository.findById(enjoyUpdateDto.getEnjoy_id()).orElseThrow(EnjoyNotFoundException::new);

        checkMember(member, enjoy.getMember());

        enjoy.updateEnjoy(enjoyUpdateDto);

        return EnjoyGetDto.toDto(enjoy, MemberGetDto.toDto(member));
    }

    private void checkMember(Member updater, Member writer) {
        if(updater.getEmail().equals(writer.getEmail()))
            return;
        throw new BusinessLogicException(ErrorCodeType.USER_UNAUTHORIZED);
    }

}
