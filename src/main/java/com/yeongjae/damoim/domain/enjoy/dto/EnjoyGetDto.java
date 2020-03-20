package com.yeongjae.damoim.domain.enjoy.dto;

import com.yeongjae.damoim.domain.enjoy.entity.Enjoy;
import com.yeongjae.damoim.domain.enjoy.entity.EnjoyCategory;
import com.yeongjae.damoim.domain.member.dto.MemberGetDto;
import com.yeongjae.damoim.domain.member.entity.Member;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EnjoyGetDto {
    private Long id;
    private LocalDateTime createdAt;
    private String title;
    private String content;
    private Long hits = 0L;
    private EnjoyCategory category = EnjoyCategory.ETC;
    private String latitude;
    private String longitude;
    private Integer recruit;
    private MemberGetDto member;

    @Builder
    public EnjoyGetDto(Long id, LocalDateTime createdAt, String title, String content, Long hits,
                       EnjoyCategory category, String latitude, String longitude, Integer recruit, MemberGetDto member) {
        this.id = id;
        this.createdAt = createdAt;
        this.title = title;
        this.content = content;
        this.hits = hits;
        this.category = category;
        this.latitude = latitude;
        this.longitude = longitude;
        this.recruit = recruit;
        this.member = member;
    }

    public static EnjoyGetDto toDto(Enjoy enjoy, MemberGetDto memberGetDto){
        return EnjoyGetDto.builder()
                .id(enjoy.getId())
                .createdAt(enjoy.getCreatedAt())
                .title(enjoy.getTitle())
                .content(enjoy.getContent())
                .hits(enjoy.getHits())
                .category(enjoy.getCategory())
                .latitude(enjoy.getLatitude())
                .longitude(enjoy.getLongitude())
                .recruit(enjoy.getRecruit())
                .member(memberGetDto)
                .build();
    }
}
