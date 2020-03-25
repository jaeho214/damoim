package com.yeongjae.damoim.global.notification.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NotificationDto {

    private final String title = "다모임";
    private String body;

    public NotificationDto(String body){this.body = body;}
}
