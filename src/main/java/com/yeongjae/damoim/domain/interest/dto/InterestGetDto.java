package com.yeongjae.damoim.domain.interest.dto;

import com.yeongjae.damoim.domain.deal.dto.DealGetDto;
import com.yeongjae.damoim.domain.deal.entity.Deal;
import com.yeongjae.damoim.domain.member.dto.MemberGetDto;
import com.yeongjae.damoim.domain.member.entity.Member;
import lombok.*;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InterestGetDto {
    private Long id;
    private String member;
    private Long deal_id;

    @Builder
    public InterestGetDto(Long id, String member, Long deal_id) {
        this.id = id;
        this.member = member;
        this.deal_id = deal_id;
    }

    public static InterestGetDto toDto(Long id, Member member, Deal deal){
        return InterestGetDto.builder()
                .id(id)
                .member(member.getNickName())
                .deal_id(deal.getId())
                .build();
    }
}
