package com.yeongjae.damoim.domain.board.dto;


import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardGetByMemberDto implements Serializable {
    private Long board_id;
    private LocalDateTime createdAt;
    private Long hits;
    private String title;
    private Integer replyCount;
    private String location;
    private Integer likeCount;

    @Builder
    public BoardGetByMemberDto(Long board_id, LocalDateTime createdAt, Long hits,
                               String title, Integer replyCount, String location, Integer likeCount) {
        this.board_id = board_id;
        this.createdAt = createdAt;
        this.hits = hits;
        this.title = title;
        this.replyCount = replyCount;
        this.location = location;
        this.likeCount = likeCount;
    }
}
