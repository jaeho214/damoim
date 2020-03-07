package com.yeongjae.damoim.domain.enjoy.service;

import com.yeongjae.damoim.domain.enjoy.dto.EnjoyUpdateDto;
import com.yeongjae.damoim.domain.enjoy.entity.Enjoy;
import com.yeongjae.damoim.domain.enjoy.entity.EnjoyCategory;
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

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class EnjoyUpdateServiceTest {
    @InjectMocks
    private EnjoyUpdateService enjoyUpdateService;
    @Mock
    private EnjoyRepository enjoyRepository;
    @Mock
    private JwtService jwtService;
    @Mock
    private MemberRepository memberRepository;

    private EnjoyUpdateDto enjoyUpdateDto = new EasyRandom().nextObject(EnjoyUpdateDto.class);
    private Member member = Member.builder()
            .email("aaa@email.com")
            .password("1234")
            .build();
    private Enjoy enjoy = Enjoy.builder()
            .content("content")
            .title("title")
            .hits(0L)
            .category(EnjoyCategory.DRINK)
            .member(member)
            .build();

    private String token = "token";
    private String email = "email";

    @Test
    void updateEnjoy() {
        //given
        given(enjoyRepository.findById(anyLong())).willReturn(Optional.ofNullable(enjoy));
        given(jwtService.findEmailByJwt(anyString())).willReturn(email);
        given(memberRepository.findByEmail(anyString())).willReturn(Optional.of(member));

        //when
        Enjoy updateEnjoy = enjoyUpdateService.updateEnjoy(token, enjoyUpdateDto);

        //then
        assertThat(updateEnjoy.getContent()).isEqualTo(enjoyUpdateDto.getContent());
        assertThat(updateEnjoy.getTitle()).isEqualTo(enjoyUpdateDto.getTitle());

    }
}