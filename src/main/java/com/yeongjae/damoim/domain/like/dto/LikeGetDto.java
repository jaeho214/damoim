package com.yeongjae.damoim.domain.like.dto;

import com.yeongjae.damoim.domain.like.entity.BoardLike;
import lombok.*;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LikeGetDto {
    private Long id;
    private Long board_id;
    private String nickName;

    @Builder
    public LikeGetDto(Long id, Long board_id, String nickName) {
        this.id = id;
        this.board_id = board_id;
        this.nickName = nickName;
    }

    public static LikeGetDto toDto(BoardLike like){
        return LikeGetDto.builder()
                .id(like.getId())
                .board_id(like.getBoard().getId())
                .nickName(like.getMember().getNickName())
                .build();
    }
}
