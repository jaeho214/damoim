package com.yeongjae.damoim.domain.deal.dto;

import com.yeongjae.damoim.domain.deal.entity.DealStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DealGetByLocationDto {
    private Long deal_id;
    private String title;
    private Long hits;
    private String writer;
    private LocalDateTime createdAt;
    private DealStatus dealStatus;

    @Builder
    public DealGetByLocationDto(Long deal_id, String title, Long hits, String writer, LocalDateTime createdAt, DealStatus dealStatus) {
        this.deal_id = deal_id;
        this.title = title;
        this.hits = hits;
        this.writer = writer;
        this.createdAt = createdAt;
        this.dealStatus = dealStatus;
    }
}
