package com.yeongjae.damoim.domain.enjoy.dto;

import lombok.*;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EnjoyGetPagingDto {
    private boolean isLast;
    private boolean isEmpty;
    private List<EnjoyGetByLocationDto> enjoyGetByLocationDtoList = new ArrayList<>();
    private List<EnjoyGetByMemberDto> enjoyGetByMemberDtoList = new ArrayList<>();

    @Builder
    public EnjoyGetPagingDto(boolean isLast, boolean isEmpty,
                             List<EnjoyGetByLocationDto> enjoyGetByLocationDtoList,
                             List<EnjoyGetByMemberDto> enjoyGetByMemberDtoList) {
        this.isLast = isLast;
        this.isEmpty = isEmpty;
        this.enjoyGetByLocationDtoList = enjoyGetByLocationDtoList;
        this.enjoyGetByMemberDtoList = enjoyGetByMemberDtoList;
    }

    public static EnjoyGetPagingDto locationOf(Page<EnjoyGetByLocationDto> enjoyPage){
        return EnjoyGetPagingDto.builder()
                .isEmpty(enjoyPage.isEmpty())
                .isLast(enjoyPage.isLast())
                .enjoyGetByLocationDtoList(enjoyPage.getContent())
                .enjoyGetByMemberDtoList(null)
                .build();
    }

    public static EnjoyGetPagingDto memberOf(Page<EnjoyGetByMemberDto> enjoyPage){
        return EnjoyGetPagingDto.builder()
                .isEmpty(enjoyPage.isEmpty())
                .isLast(enjoyPage.isLast())
                .enjoyGetByLocationDtoList(null)
                .enjoyGetByMemberDtoList(enjoyPage.getContent())
                .build();
    }


}
