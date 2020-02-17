package com.yeongjae.damoim.domain.member.service;

import com.yeongjae.damoim.domain.member.dto.MemberCreateDto;
import com.yeongjae.damoim.domain.member.entity.Member;
import com.yeongjae.damoim.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class MemberCreateServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberCreateService memberCreateService;

    private MemberCreateDto memberCreateDto = MemberCreateDto.builder()
            .email("email@gmail.com")
            .password("1")
            .address("서울특별시 성북구 정릉동")
            .isVerified(false)
            .nickName("닉넴")
            .sex("male")
            .phone("010-1111-2222")
            .build();

    @Test
    void createMember() {
        //given
        Member member = memberCreateDto.of();
        given(memberRepository.save(member)).willReturn(member);

        //when
        Member savedMember = memberCreateService.createMember(memberCreateDto);

        //then
        assertThat(savedMember.getEmail()).isEqualTo(member.getEmail());
        assertThat(savedMember.getNickName()).isEqualTo(member.getNickName());
        assertThat(savedMember.getAddress()).isEqualTo(member.getAddress());
    }
}