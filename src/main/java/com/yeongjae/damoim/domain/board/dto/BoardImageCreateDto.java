package com.yeongjae.damoim.domain.board.dto;

import com.yeongjae.damoim.domain.board.entity.Board;
import com.yeongjae.damoim.domain.board.entity.BoardImage;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardImageCreateDto {
    @NotBlank(message = "이미지 url이 필요합니다")
    private String imagePath;
    private Board board;

}
