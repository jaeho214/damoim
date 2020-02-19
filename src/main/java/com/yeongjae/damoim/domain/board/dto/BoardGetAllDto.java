package com.yeongjae.damoim.domain.board.dto;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardGetAllDto {
    private String title;
    private Long hits;
    private String writer;
    private LocalDateTime createdDate;

}
