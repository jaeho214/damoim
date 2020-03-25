package com.yeongjae.damoim.global.notification.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PushDto {
    @JsonProperty(value = "notification")
    private NotificationDto notificationDto;

    @JsonProperty(value = "registration_ids")
    private List<String> registrationIds = new ArrayList<>();

    @Builder
    public PushDto(NotificationDto notificationDto, List<String> registrationIds) {
        this.notificationDto = notificationDto;
        this.registrationIds = registrationIds;
    }
}
