package com.yeongjae.damoim.domain.enjoy.service;

import com.yeongjae.damoim.domain.enjoy.entity.Enjoy;
import com.yeongjae.damoim.domain.enjoy.exception.EnjoyNotFoundException;
import com.yeongjae.damoim.domain.enjoy.repository.EnjoyRepository;
import com.yeongjae.damoim.domain.member.entity.Member;
import com.yeongjae.damoim.domain.member.exception.MemberNotFoundException;
import com.yeongjae.damoim.domain.member.repository.MemberRepository;
import com.yeongjae.damoim.global.error.ErrorCodeType;
import com.yeongjae.damoim.global.error.exception.BusinessLogicException;
import com.yeongjae.damoim.global.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class EnjoyDeleteService {
    private final EnjoyRepository enjoyRepository;
    private final MemberRepository memberRepository;
    private final JwtService jwtService;

    public ResponseEntity deleteEnjoy(String token, Long enjoy_id){
        String email = jwtService.findEmailByJwt(token);

        Member member = memberRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new);

        Enjoy enjoy = enjoyRepository.findById(enjoy_id).orElseThrow(EnjoyNotFoundException::new);

        checkMember(member, enjoy.getMember());

        enjoy.delete();

        return ResponseEntity.noContent().build();
    }

    private void checkMember(Member deleter, Member writer) {
        if(deleter.getEmail().equals(writer.getEmail()))
            return;
        throw new BusinessLogicException(ErrorCodeType.USER_UNAUTHORIZED);
    }
}
