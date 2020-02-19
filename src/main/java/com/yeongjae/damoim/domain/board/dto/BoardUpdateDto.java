package com.yeongjae.damoim.domain.board.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardUpdateDto {
    @NotNull(message = "반드시 id가 있어야 합니다.")
    private Long board_id;
    @NotBlank(message = "제목을 입력하세요.")
    private String title;
    @NotBlank(message = "내용을 입력하세요.")
    private String content;
    private List<String> imagePaths;


    public BoardUpdateDto(String title,
                          String content,
                          List<String> imagePaths) {
        this.title = title;
        this.content = content;
        this.imagePaths = imagePaths;
    }
}
