package com.yeongjae.damoim.domain.enjoy.dto;

import com.yeongjae.damoim.domain.enjoy.entity.EnjoyCategory;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EnjoyUpdateDto {
    @NotNull(message = "id를 입력하세요")
    private Long enjoy_id;
    @NotBlank(message = "제목을 입력하시오")
    private String title;
    @NotBlank(message = "내용을 입력하시오")
    private String content;
    @NotBlank(message = "카테고리를 정하시오")
    private String category;
    @NotBlank(message = "번개 장소의 위치를 지정하시오")
    private String latitude;
    @NotBlank(message = "번개 장소의 위치를 지정하시오")
    private String longitude;
    @NotNull(message = "모집 인원을 입력하시오")
    private Integer recruit;


    public EnjoyUpdateDto(Long enjoy_id,
                          String title,
                          String content,
                          String category,
                          String latitude,
                          String longitude,
                          Integer recruit) {
        this.enjoy_id = enjoy_id;
        this.title = title;
        this.content = content;
        this.category = category;
        this.latitude = latitude;
        this.longitude = longitude;
        this.recruit = recruit;
    }
}
