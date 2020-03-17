package com.yeongjae.damoim.domain.board.dto;

import com.yeongjae.damoim.domain.board.entity.Board;
import com.yeongjae.damoim.domain.board.entity.BoardImage;
import com.yeongjae.damoim.domain.member.dto.MemberGetDto;
import com.yeongjae.damoim.domain.reply.entity.Reply;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardGetDto {
    private Long id;
    private LocalDateTime createdAt;
    private String title;
    private String content;
    private Long hits;
    private String location;
    private MemberGetDto member;
    private Set<BoardImage> imagePaths = new HashSet<>();
    private Set<Reply> replyList = new HashSet<>();

    @Builder
    public BoardGetDto(Long id, LocalDateTime createdAt, String title, String content, Long hits, String location, MemberGetDto member, Set<BoardImage> imagePaths, Set<Reply> replyList) {
        this.id = id;
        this.createdAt = createdAt;
        this.title = title;
        this.content = content;
        this.hits = hits;
        this.location = location;
        this.member = member;
        this.imagePaths = imagePaths;
        this.replyList = replyList;
    }

    public static BoardGetDto toDto(Board board, MemberGetDto memberGetDto){
        return BoardGetDto.builder()
                .id(board.getId())
                .content(board.getContent())
                .title(board.getTitle())
                .createdAt(board.getCreatedAt())
                .hits(board.getHits())
                .location(board.getLocation())
                .member(memberGetDto)
                .imagePaths(board.getImagePaths())
                .replyList(board.getReplyList())
                .build();
    }
}
