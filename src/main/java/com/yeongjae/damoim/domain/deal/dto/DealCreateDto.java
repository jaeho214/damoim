package com.yeongjae.damoim.domain.deal.dto;

import com.yeongjae.damoim.domain.deal.entity.Deal;
import com.yeongjae.damoim.domain.deal.entity.DealCategory;
import com.yeongjae.damoim.domain.deal.entity.DealStatus;
import com.yeongjae.damoim.domain.member.entity.Member;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DealCreateDto {
    @NotBlank(message = "제목을 입력하세요")
    private String title;
    @NotBlank(message = "내용을 입력하세요")
    private String content;
    @NotBlank(message = "지역을 입력하세요")
    private String location;
    @NotBlank(message = "카테고리를 입력하세요")
    private String category;
    @NotBlank(message = "가격을 입력하세요")
    private String price;
    @NotBlank(message = "사진이 반드시 필요합니다")
    private List<String> imagePaths = new ArrayList<>();

    @Builder
    public DealCreateDto(String title, String content, String location, String category, String price, List<String> imagePaths) {
        this.title = title;
        this.content = content;
        this.location = location;
        this.category = category;
        this.price = price;
        this.imagePaths = imagePaths;
    }

    public Deal of(Member member){
        return Deal.builder()
                .title(this.title)
                .content(this.content)
                .location(this.location)
                .category(DealCategory.fromString(category))
                .price(this.price)
                .hits(0L)
                .member(member)
                .imagePaths(new ArrayList<>())
                .status(DealStatus.SELLING)
                .interests(new ArrayList<>())
                .build();
    }


}
