package com.yeongjae.damoim.domain.member.service;

import com.yeongjae.damoim.domain.member.dto.MemberCreateDto;
import com.yeongjae.damoim.domain.member.dto.MemberGetDto;
import com.yeongjae.damoim.domain.member.dto.MemberSocialCreateDto;
import com.yeongjae.damoim.domain.member.entity.Member;
import com.yeongjae.damoim.domain.member.exception.DuplicatedEmailException;
import com.yeongjae.damoim.domain.member.repository.MemberRepository;
import com.yeongjae.damoim.global.oauth.domain.KAKAOProfile;
import com.yeongjae.damoim.global.oauth.domain.NaverProfile;
import com.yeongjae.damoim.global.oauth.service.OAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberCreateService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final OAuthService oAuthService;

    public MemberGetDto createMember(MemberCreateDto memberCreateDto) {
        if(memberRepository.existsByEmail(memberCreateDto.getEmail()))
            throw new DuplicatedEmailException();

        memberCreateDto.setPassword(passwordEncoder.encode(memberCreateDto.getPassword()));

        Member savedMember = memberRepository.save(memberCreateDto.of());

        return MemberGetDto.toDto(savedMember);
    }

    public MemberGetDto createMemberByKAKAO(String access_token, MemberSocialCreateDto memberSocialCreateDto) {
        KAKAOProfile profile = oAuthService.getKakaoProfile(access_token);

        if(memberRepository.existsByEmail(profile.getKakao_account().getEmail()))
            throw new DuplicatedEmailException();

        Member savedMember = memberRepository.save(memberSocialCreateDto.of(profile));
        return MemberGetDto.toDto(savedMember);
    }

    public MemberGetDto createMemberByNAVER(String access_token, MemberSocialCreateDto memberSocialCreateDto) {
        NaverProfile profile = oAuthService.getNaverProfile(access_token);

        if(memberRepository.existsByEmail(profile.getResponse().getEmail()))
            throw new DuplicatedEmailException();

        Member savedMember = memberRepository.save(memberSocialCreateDto.of(profile));
        return MemberGetDto.toDto(savedMember);
    }
}
