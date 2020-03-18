package com.yeongjae.damoim.domain.deal.dto;

import lombok.*;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DealGetPagingDto {
    private boolean isLast;
    private boolean isEmpty;
    private List<DealGetByLocationDto> dealGetByLocationDtos = new ArrayList<>();
    private List<DealGetByMemberDto> dealGetByMemberDtos = new ArrayList<>();

    @Builder
    public DealGetPagingDto(boolean isLast, boolean isEmpty, List<DealGetByLocationDto> dealGetByLocationDtos, List<DealGetByMemberDto> dealGetByMemberDtos) {
        this.isLast = isLast;
        this.isEmpty = isEmpty;
        this.dealGetByLocationDtos = dealGetByLocationDtos;
        this.dealGetByMemberDtos = dealGetByMemberDtos;
    }

    public static DealGetPagingDto locationOf(Page<DealGetByLocationDto> dealPage){
        return DealGetPagingDto.builder()
                .isLast(dealPage.isLast())
                .isEmpty(dealPage.isEmpty())
                .dealGetByLocationDtos(dealPage.getContent())
                .dealGetByMemberDtos(null)
                .build();
    }

    public static DealGetPagingDto memberOf(Page<DealGetByMemberDto> dealPage){
        return DealGetPagingDto.builder()
                .isLast(dealPage.isLast())
                .isEmpty(dealPage.isEmpty())
                .dealGetByLocationDtos(null)
                .dealGetByMemberDtos(dealPage.getContent())
                .build();
    }
}
