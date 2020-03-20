package com.yeongjae.damoim.domain.enjoy.service;

import com.yeongjae.damoim.domain.enjoy.dto.EnjoyGetByLocationDto;
import com.yeongjae.damoim.domain.enjoy.dto.EnjoyGetByMemberDto;
import com.yeongjae.damoim.domain.enjoy.dto.EnjoyGetDto;
import com.yeongjae.damoim.domain.enjoy.dto.EnjoyGetPagingDto;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnjoyGetService {

    private final int LIMIT = 10;

    private final EnjoyRepository enjoyRepository;
    private final JwtService jwtService;

    @Transactional(readOnly = true)
    public EnjoyGetPagingDto getEnjoys(String location, int pageNo){
        Pageable pageable = PageRequest.of(--pageNo * LIMIT, 10, Sort.Direction.DESC, "createdAt");
        Page<EnjoyGetByLocationDto> enjoyPages = enjoyRepository.findByLocation(location, pageable);

        return EnjoyGetPagingDto.locationOf(enjoyPages);
    }

    @Transactional(readOnly = true)
    public EnjoyGetDto getEnjoy(String token, Long enjoy_id){
        Member member = jwtService.findMemberByToken(token);

        Enjoy enjoy = enjoyRepository.fetchEnjoyById(enjoy_id).orElseThrow(EnjoyNotFoundException::new);

        if(!member.getIsVerified() || !member.getLocation().equals(enjoy.getLocation()))
            throw new BusinessLogicException(ErrorCodeType.USER_UNAUTHORIZED);

        enjoy.updateHits();
        return EnjoyGetDto.toDto(enjoy, MemberGetDto.toDto(member));
    }

    public EnjoyGetPagingDto getEnjoyByMember(String token, int pageNo){
        Member member = jwtService.findMemberByToken(token);

        Pageable pageable = PageRequest.of(--pageNo * LIMIT, 10, Sort.Direction.DESC, "createdAt");
        Page<EnjoyGetByMemberDto> enjoyPages = enjoyRepository.findByMember(member, pageable);

        return EnjoyGetPagingDto.memberOf(enjoyPages);
    }
}
