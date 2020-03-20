package com.yeongjae.damoim.domain.enjoy.dto;

import com.yeongjae.damoim.domain.enjoy.entity.EnjoyCategory;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EnjoyGetByMemberDto {
    private Long id;
    private LocalDateTime createdAt;
    private Long hits;
    private String title;
    private String location;
    private EnjoyCategory category;

    @Builder
    public EnjoyGetByMemberDto(Long id, LocalDateTime createdAt, Long hits,
                               String title, String location, EnjoyCategory category) {
        this.id = id;
        this.createdAt = createdAt;
        this.hits = hits;
        this.title = title;
        this.location = location;
        this.category = category;
    }
}
