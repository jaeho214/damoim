package com.yeongjae.damoim.global.notification.service;

import com.yeongjae.damoim.global.notification.dto.*;
import com.yeongjae.damoim.global.notification.interceptor.HeaderRequestInterceptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {

    @Value("${fcm.url}")
    private String api_url;

    @Value("${fcm.server}")
    private String serverKey;

    public FirebaseDto sendNotification(List<String> fcmTokens, String sender, NotificationType type) {

        HttpEntity<PushDto> request = new HttpEntity<>(getPushDto(fcmTokens, sender, type));

        CompletableFuture<FirebaseDto> pushNotification = this.send(request);
        CompletableFuture.allOf(pushNotification).join();

        try {
            return pushNotification.get();
        } catch (InterruptedException | ExecutionException e) {
            log.error(e.getMessage());
        }
        return null;
    }


    @Async
    public CompletableFuture<FirebaseDto> send(HttpEntity<PushDto> entity) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(setInterceptors());

        FirebaseDto firebaseDto = restTemplate.postForObject(api_url, entity, FirebaseDto.class);

        return CompletableFuture.completedFuture(firebaseDto);

    }

    private List<ClientHttpRequestInterceptor> setInterceptors() {
        ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + serverKey));
        interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));
        return interceptors;
    }

    private PushDto getPushDto(List<String> fcmTokens, String sender, NotificationType type) {
        String body = "";

        switch (type){
            case CHAT:
                body = sender + PushMessage.CHAT;
                break;
            case REPLY:
                body = sender + PushMessage.REPLY;
                break;
            case KEYWORD:
                body = sender + PushMessage.KEYWORD;
                break;
        }

        NotificationDto notificationDto = new NotificationDto(body);

        return PushDto.builder()
                .notificationDto(notificationDto)
                .registrationIds(fcmTokens)
                .build();
    }

}
