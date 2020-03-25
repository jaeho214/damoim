package com.yeongjae.damoim.domain.deal.dto;

import com.yeongjae.damoim.domain.deal.entity.DealStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DealGetByMemberDto {
    private Long deal_id;
    private String title;
    private Long hits;
    private LocalDateTime createdAt;
    private DealStatus dealStatus;
    private Integer interestCount;

    @Builder
    public DealGetByMemberDto(Long deal_id,
                              String title,
                              Long hits,
                              LocalDateTime createdAt,
                              DealStatus dealStatus,
                              Integer interestCount) {
        this.deal_id = deal_id;
        this.title = title;
        this.hits = hits;
        this.createdAt = createdAt;
        this.dealStatus = dealStatus;
        this.interestCount = interestCount;
    }
}
