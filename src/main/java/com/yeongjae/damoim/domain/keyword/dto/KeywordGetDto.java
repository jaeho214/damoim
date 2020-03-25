package com.yeongjae.damoim.domain.keyword.dto;

import com.yeongjae.damoim.domain.keyword.entity.Keyword;
import lombok.*;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class KeywordGetDto {
    private Long id;
    private String keyword;

    @Builder
    public KeywordGetDto(Long id, String keyword) {
        this.id = id;
        this.keyword = keyword;
    }

    public static KeywordGetDto toDto(Keyword keyword){
        return KeywordGetDto.builder()
                .id(keyword.getId())
                .keyword(keyword.getKeyword())
                .build();
    }
}
