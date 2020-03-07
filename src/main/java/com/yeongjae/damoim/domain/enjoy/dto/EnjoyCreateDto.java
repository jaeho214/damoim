package com.yeongjae.damoim.domain.enjoy.dto;

import com.yeongjae.damoim.domain.enjoy.entity.Enjoy;
import com.yeongjae.damoim.domain.enjoy.entity.EnjoyCategory;
import com.yeongjae.damoim.domain.member.entity.Member;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EnjoyCreateDto {
    @NotBlank(message = "제목을 입력하시오")
    private String title;
    @NotBlank(message = "내용을 입력하시오")
    private String content;
    @NotBlank(message = "지역을 입력하시오")
    private String location;
    @NotBlank(message = "카테고리를 정하시오")
    private String category;
    @NotBlank(message = "번개 장소의 위치를 지정하시오")
    private String latitude;
    @NotBlank(message = "번개 장소의 위치를 지정하시오")
    private String longitude;
    @NotNull(message = "모집 인원을 입력하시오")
    private Integer recruit;

    @Builder
    public EnjoyCreateDto(String title, String content, String location, String category, String latitude, String longitude, Integer recruit) {
        this.title = title;
        this.content = content;
        this.location = location;
        this.category = category;
        this.latitude = latitude;
        this.longitude = longitude;
        this.recruit = recruit;
    }

    public Enjoy of(Member member){
        return Enjoy.builder()
                .title(this.title)
                .content(this.content)
                .location(this.location)
                .category(EnjoyCategory.fromString(this.category))
                .latitude(this.latitude)
                .longitude(this.longitude)
                .recruit(this.recruit)
                .member(member)
                .hits(0L)
                .build();
    }
}
