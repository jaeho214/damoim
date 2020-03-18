package com.yeongjae.damoim.domain.deal.dto;

import com.yeongjae.damoim.domain.deal.entity.Deal;
import com.yeongjae.damoim.domain.deal.entity.DealCategory;
import com.yeongjae.damoim.domain.deal.entity.DealImage;
import com.yeongjae.damoim.domain.deal.entity.DealStatus;
import com.yeongjae.damoim.domain.member.dto.MemberGetDto;
import com.yeongjae.damoim.domain.member.entity.Member;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DealGetDto {
    private Long id;
    private LocalDateTime createdAt;
    private String title;
    private String content;
    private Long hits = 0L;
    private DealCategory category;
    private String price;
    private DealStatus status;
    private MemberGetDto member;
    private List<DealImage> imagePaths = new ArrayList<>();

    @Builder
    public DealGetDto(Long id, LocalDateTime createdAt, String title, String content, Long hits, DealCategory category, String price, DealStatus status, MemberGetDto member, List<DealImage> imagePaths) {
        this.id = id;
        this.createdAt = createdAt;
        this.title = title;
        this.content = content;
        this.hits = hits;
        this.category = category;
        this.price = price;
        this.status = status;
        this.member = member;
        this.imagePaths = imagePaths;
    }

    public static DealGetDto toDto(Deal deal, MemberGetDto memberGetDto){
        return DealGetDto.builder()
                .id(deal.getId())
                .createdAt(deal.getCreatedAt())
                .title(deal.getTitle())
                .content(deal.getContent())
                .hits(deal.getHits())
                .category(deal.getCategory())
                .price(deal.getPrice())
                .status(deal.getStatus())
                .member(memberGetDto)
                .imagePaths(deal.getImagePaths())
                .build();
    }
}
