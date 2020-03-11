package com.yeongjae.damoim.domain.reply.dto;

import com.yeongjae.damoim.domain.board.entity.Board;
import com.yeongjae.damoim.domain.member.entity.Member;
import com.yeongjae.damoim.domain.reply.entity.Reply;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReplyUpdateDto {
    @NotNull(message = "댓글의 번호를 입력하세요")
    private Long reply_id;
    @NotBlank(message = "댓글 내용을 입력하세요")
    private String content;

    public ReplyUpdateDto(String content) {
        this.content = content;
    }

    public Reply of(Member member, Board board){
        return Reply.builder()
                .content(content)
                .member(member)
                .board(board)
                .build();
    }
}