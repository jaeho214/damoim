package com.yeongjae.damoim.domain.enjoy.dto;

import com.yeongjae.damoim.domain.enjoy.entity.EnjoyCategory;
import com.yeongjae.damoim.domain.member.dto.MemberGetDto;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EnjoyGetByLocationDto {
    private Long id;
    private LocalDateTime createdAt;
    private String title;
    private Long hits = 0L;
    private EnjoyCategory category;
    private Integer recruit;
    private String writer;

    @Builder
    public EnjoyGetByLocationDto(Long id, LocalDateTime createdAt, String title, Long hits,
                                 EnjoyCategory category, Integer recruit, String writer) {
        this.id = id;
        this.createdAt = createdAt;
        this.title = title;
        this.hits = hits;
        this.category = category;
        this.recruit = recruit;
        this.writer = writer;
    }
}
