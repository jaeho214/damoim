package com.yeongjae.damoim.domain.member.service;

import com.yeongjae.damoim.domain.member.dto.MemberCreateDto;
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

    public Member createMember(MemberCreateDto memberCreateDto) {
        if(memberRepository.existsByEmail(memberCreateDto.getEmail()))
            throw new DuplicatedEmailException();

        memberCreateDto.setPassword(passwordEncoder.encode(memberCreateDto.getPassword()));
        return memberRepository.save(memberCreateDto.of());
    }

    public Member createMemberByKAKAO(String access_token, MemberSocialCreateDto memberSocialCreateDto) {
        KAKAOProfile profile = oAuthService.getKakaoProfile(access_token);

        if(memberRepository.existsByEmail(profile.getKakao_account().getEmail()))
            throw new DuplicatedEmailException();

        return memberRepository.save(memberSocialCreateDto.of(profile));

    }

    public Member createMemberByNAVER(String access_token, MemberSocialCreateDto memberSocialCreateDto) {
        NaverProfile profile = oAuthService.getNaverProfile(access_token);

        if(memberRepository.existsByEmail(profile.getResponse().getEmail()))
            throw new DuplicatedEmailException();

        return memberRepository.save(memberSocialCreateDto.of(profile));
    }
}
