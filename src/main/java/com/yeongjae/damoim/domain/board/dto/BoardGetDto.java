package com.yeongjae.damoim.domain.board.dto;


import com.yeongjae.damoim.domain.board.entity.BoardImage;
import com.yeongjae.damoim.domain.reply.entity.Reply;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardGetDto {
    private String title;
    private String content;
    private Long hits;
    private String writer;
    private LocalDateTime createdTime;
    private List<BoardImage> imagePaths = new ArrayList<>();
    private List<Reply> replyList = new ArrayList<>();
}
