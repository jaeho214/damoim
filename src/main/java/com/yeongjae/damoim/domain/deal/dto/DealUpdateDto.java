package com.yeongjae.damoim.domain.deal.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DealUpdateDto {
    private Long deal_id;
    private String title;
    private String content;
    private String category;
    private String price;
    private String status;
    private List<String> imagePaths;
}
