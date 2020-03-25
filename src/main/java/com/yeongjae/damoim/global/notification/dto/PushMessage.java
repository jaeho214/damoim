package com.yeongjae.damoim.global.notification.dto;

import lombok.Getter;

@Getter
public enum PushMessage {
    CHAT("님이 메세지를 보냈습니다."),
    REPLY("님이 회원님의 게시물에 댓글을 달았습니다."),
    KEYWORD("에 대한 게시물이 작성되었습니다.! 확인해보세요!");

    private String message;
    PushMessage(String message){
        this.message = message;
    }
}
