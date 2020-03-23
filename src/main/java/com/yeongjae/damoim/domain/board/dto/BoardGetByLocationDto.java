package com.yeongjae.damoim.domain.board.dto;


import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardGetByLocationDto {
    private Long board_id;
    private String title;
    private Long hits;
    private String writer;
    private LocalDateTime createdAt;
    private Integer replyCount;
    private Integer likeCount;

    @Builder
    public BoardGetByLocationDto(Long board_id, String title, Long hits, String writer,
                                 LocalDateTime createdAt, Integer replyCount, Integer likeCount) {
        this.board_id = board_id;
        this.title = title;
        this.hits = hits;
        this.writer = writer;
        this.createdAt = createdAt;
        this.replyCount = replyCount;
        this.likeCount = likeCount;
    }
}
