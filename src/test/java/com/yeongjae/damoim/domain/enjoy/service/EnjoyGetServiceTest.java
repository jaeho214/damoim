package com.yeongjae.damoim.domain.enjoy.service;

import com.yeongjae.damoim.domain.enjoy.dto.EnjoyGetByLocationDto;
import com.yeongjae.damoim.domain.enjoy.dto.EnjoyGetByMemberDto;
import com.yeongjae.damoim.domain.enjoy.dto.EnjoyGetDto;
import com.yeongjae.damoim.domain.enjoy.dto.EnjoyGetPagingDto;
import com.yeongjae.damoim.domain.enjoy.entity.Enjoy;
import com.yeongjae.damoim.domain.enjoy.entity.EnjoyCategory;
import com.yeongjae.damoim.domain.enjoy.repository.EnjoyRepository;
import com.yeongjae.damoim.domain.member.entity.Member;
import com.yeongjae.damoim.domain.member.repository.MemberRepository;
import com.yeongjae.damoim.global.jwt.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class EnjoyGetServiceTest {

    @Mock
    private EnjoyRepository enjoyRepository;
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private JwtService jwtService;
    @InjectMocks
    private EnjoyGetService enjoyGetService;

    private Member member = Member.builder()
            .email("email")
            .birth("0214")
            .password("1234")
            .isVerified(true)
            .location("강원도_강릉시")
            .build();
    private Enjoy enjoy = Enjoy.builder()
            .member(member)
            .category(EnjoyCategory.DRINK)
            .hits(0L)
            .content("content")
            .location("강원도_강릉시")
            .title("title")
            .build();

    private String email = "email@eamil.com";
    private String token = "token";
    private Page<EnjoyGetByLocationDto> enjoyGetByLocationDtoPage = Mockito.mock(Page.class);
    private Page<EnjoyGetByMemberDto> enjoyGetByMemberDtoPage = Mockito.mock(Page.class);

    @Test
    void getEnjoys() {
        //given
        given(enjoyRepository.findByLocation(anyString(), any())).willReturn(enjoyGetByLocationDtoPage);

        //when
        EnjoyGetPagingDto enjoyList = enjoyGetService.getEnjoys("강원도_강릉시", 1);

        //then
        assertThat(enjoyList.getEnjoyGetByLocationDtoList().size()).isEqualTo(enjoyGetByLocationDtoPage.getSize());
    }

    @Test
    void getEnjoy() {
        //given
        given(jwtService.findMemberByToken(anyString())).willReturn(member);
        given(enjoyRepository.fetchEnjoyById(anyLong())).willReturn(Optional.of(enjoy));

        //when
        EnjoyGetDto savedEnjoy = enjoyGetService.getEnjoy(token, 1L);

        //then
        assertThat(savedEnjoy.getTitle()).isEqualTo(enjoy.getTitle());
        assertThat(savedEnjoy.getContent()).isEqualTo(enjoy.getContent());
    }

    @Test
    void getEnjoyByMember(){
        //given
        given(jwtService.findMemberByToken(anyString())).willReturn(member);
        given(enjoyRepository.findByMember(any(Member.class), any())).willReturn(enjoyGetByMemberDtoPage);

        //when
        EnjoyGetPagingDto enjoyList = enjoyGetService.getEnjoyByMember("token", 1);

        //then
        assertThat(enjoyList.getEnjoyGetByMemberDtoList().size()).isEqualTo(enjoyGetByMemberDtoPage.getSize());
    }

    @Test
    void searchByKeyword(){
        //given
        given(enjoyRepository.searchByKeyword(anyString(),anyString(),any())).willReturn(enjoyGetByLocationDtoPage);

        //when
        EnjoyGetPagingDto enjoyList = enjoyGetService.searchByKeyword("location", "keyword", 1);

        //then
        assertThat(enjoyList.getEnjoyGetByLocationDtoList().size()).isEqualTo(enjoyGetByLocationDtoPage.getSize());

    }

    @Test
    void getEnjoyByCategory(){
        //given
        given(enjoyRepository.findByCategory(any(), anyString(), any())).willReturn(enjoyGetByLocationDtoPage);

        //when
        EnjoyGetPagingDto enjoyList = enjoyGetService.getEnjoyByCategory("location", "category", 1);

        //then
        assertThat(enjoyList.getEnjoyGetByLocationDtoList().size()).isEqualTo(enjoyGetByLocationDtoPage.getSize());

    }
}