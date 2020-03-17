package com.yeongjae.damoim.domain.board.dto;

import lombok.*;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardGetPagingDto {

    private boolean isLast;
    private boolean isEmpty;
    private List<BoardGetByLocationDto> boardGetByLocationDtoList = new ArrayList<>();
    private List<BoardGetByMemberDto> boardGetByMemberDtoList = new ArrayList<>();

    @Builder
    public BoardGetPagingDto(boolean isLast, boolean isEmpty, List<BoardGetByLocationDto> boardGetByLocationDtoList, List<BoardGetByMemberDto> boardGetByMemberDtoList) {
        this.isLast = isLast;
        this.isEmpty = isEmpty;
        this.boardGetByLocationDtoList = boardGetByLocationDtoList;
        this.boardGetByMemberDtoList = boardGetByMemberDtoList;
    }


    public static BoardGetPagingDto locationOf(Page<BoardGetByLocationDto> boardPage){
        return BoardGetPagingDto.builder()
                .isLast(boardPage.isLast())
                .isEmpty(boardPage.isEmpty())
                .boardGetByLocationDtoList(boardPage.getContent())
                .boardGetByMemberDtoList(null)
                .build();
    }

    public static BoardGetPagingDto memberOf(Page<BoardGetByMemberDto> boardPage){
        return BoardGetPagingDto.builder()
                .isLast(boardPage.isLast())
                .isEmpty(boardPage.isEmpty())
                .boardGetByLocationDtoList(null)
                .boardGetByMemberDtoList(boardPage.getContent())
                .build();
    }
}
