package com.yeongjae.damoim.global.error;

import lombok.Getter;

@Getter
public enum ErrorCodeType {

    //Common
    UNKNOWN(400, "COMMON_001", "UNKNOWN"),
    USER_UNAUTHORIZED(400, "COMMON_002", "이 기능을 사용할 수 없는 사용자입니다."),

    //Member
    MEMBER_NOT_FOUND(400, "MEMBER_001", "해당 회원을 찾을 수 없습니다."),
    WRONG_PASSWORD(400, "MEMBER_002", "비밀번호가 틀렸습니다."),
    DUPLICATED_EMAIL(400, "MEMBER_003", "중복된 이메일입니다."),

    //board
    BOARD_NOT_FOUND(400, "BOARD_001", "해당 게시물을 찾을 수 없습니다."),

    //reply
    REPLY_NOT_FOUND(400,"REPLY_001", "해당 댓글을 찾을 수 없습니다."),

    //enjoy
    ENJOY_NOT_FOUND(400, "ENJOY_001", "해당 번개 모임을 찾을 수 없습니다."),

    //deal
    DEAL_NOT_FOUND(400, "DEAL_001", "해당 거래 목록을 찾을 수 없습니다."),

    //interest
    INTEREST_NOT_FOUND(400, "INTEREST_001","해당 즐겨찾기 기록을 찾을 수 없습니다."),

    //keyword
    KEYWORD_NOT_FOUND(400, "KEYWORD_001", "해당 키워드를 찾을 수 없습니다."),

    //like
    LIKE_NOT_FOUND(400, "LIKE_001", "해당 좋아요를 찾을 수 없습니다.");


    private int status;
    private String code;
    private String message;

    ErrorCodeType(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
