package com.yeongjae.damoim.domain.enjoy.service;

import com.yeongjae.damoim.domain.enjoy.dto.EnjoyCreateDto;
import com.yeongjae.damoim.domain.enjoy.entity.Enjoy;
import com.yeongjae.damoim.domain.enjoy.repository.EnjoyRepository;
import com.yeongjae.damoim.domain.member.entity.Member;
import com.yeongjae.damoim.domain.member.repository.MemberRepository;
import com.yeongjae.damoim.global.jwt.JwtService;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class EnjoyCreateServiceTest {

    @InjectMocks
    private EnjoyCreateService enjoyCreateService;
    @Mock
    private JwtService jwtService;
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private EnjoyRepository enjoyRepository;

    private EnjoyCreateDto enjoyCreateDtoFixture = new EasyRandom().nextObject(EnjoyCreateDto.class);
    private Member memberFixture = new EasyRandom().nextObject(Member.class);
    private String email = "email";
    private String token = "token";

    @Test
    void createEnjoy() {
        //given
        Enjoy enjoy = enjoyCreateDtoFixture.of(memberFixture);
        given(enjoyRepository.save(enjoy)).willReturn(enjoy);
        given(jwtService.findEmailByJwt(anyString())).willReturn(email);
        given(memberRepository.findByEmail(anyString())).willReturn(Optional.of(memberFixture));

        //when
        Enjoy savedEnjoy = enjoyCreateService.createEnjoy(token, enjoyCreateDtoFixture);

        //then
        assertThat(savedEnjoy.getContent()).isEqualTo(enjoy.getContent());
        assertThat(savedEnjoy.getTitle()).isEqualTo(enjoy.getTitle());

    }
}