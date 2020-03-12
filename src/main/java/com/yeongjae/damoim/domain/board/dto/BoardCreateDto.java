package com.yeongjae.damoim.domain.board.dto;

import com.yeongjae.damoim.domain.board.entity.Board;
import com.yeongjae.damoim.domain.board.entity.BoardImage;
import com.yeongjae.damoim.domain.member.entity.Member;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardCreateDto {
    @NotBlank(message = "제목을 입력하세요.")
    private String title;
    @NotBlank(message = "내용을 입력하세요.")
    private String content;
    @NotBlank(message = "지역이 입력되어야 합니다.")
    private String location;
    private List<String> imagePaths = new ArrayList<>();

    @Builder
    public BoardCreateDto(String title, String content, String location, List<String> imagePaths) {
        this.title = title;
        this.content = content;
        this.location = location;
        this.imagePaths = imagePaths;
    }

    public Board of(Member member){
        return Board.builder()
                .title(this.title)
                .content(this.content)
                .location(this.location)
                .member(member)
                .imagePaths(new HashSet<>())
                .hits(0L)
                .build();
    }
}
