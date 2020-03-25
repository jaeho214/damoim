package com.yeongjae.damoim.domain.reply.dto;

import com.yeongjae.damoim.domain.reply.entity.Reply;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReplyGetDto {
    private Long id;
    private LocalDateTime createdAt;
    private String content;
    private String writer;
    private Long board_id;

    @Builder
    public ReplyGetDto(Long id, LocalDateTime createdAt, String content, String writer, Long board_id) {
        this.id = id;
        this.createdAt = createdAt;
        this.content = content;
        this.writer = writer;
        this.board_id = board_id;
    }

    public static ReplyGetDto toDto(Reply reply){
        return ReplyGetDto.builder()
                .id(reply.getId())
                .createdAt(reply.getCreatedAt())
                .content(reply.getContent())
                .writer(reply.getMember().getNickName())
                .board_id(reply.getBoard().getId())
                .build();
    }

    public static Set<ReplyGetDto> toDtoSet(Set<Reply> replySet){
        Set<ReplyGetDto> replyGetDtoSet = new HashSet<>();
        replySet.forEach(reply ->
                replyGetDtoSet.add(toDto(reply)));

        return replyGetDtoSet;
    }
}
