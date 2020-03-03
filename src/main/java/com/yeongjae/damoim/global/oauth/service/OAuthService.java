package com.yeongjae.damoim.global.oauth.service;

import com.google.gson.Gson;
import com.yeongjae.damoim.global.error.exception.UserDefineException;
import com.yeongjae.damoim.global.oauth.domain.KAKAOProfile;
import com.yeongjae.damoim.global.oauth.domain.NaverProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class OAuthService {

    private final RestTemplate restTemplate;
    private final Gson gson;

    public KAKAOProfile getKakaoProfile(String access_token){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "Bearer " + access_token);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(null, headers);
        try{
            ResponseEntity<String> response = restTemplate.postForEntity("https://kapi.kakao.com/v2/user/me", request, String.class);
            if (response.getStatusCode() == HttpStatus.OK)
                return gson.fromJson(response.getBody(), KAKAOProfile.class);
        }catch (Exception e){
            throw new UserDefineException("communication fail with kakao");
        }
        throw new UserDefineException("communication fail with kakao");
    }

    public NaverProfile getNaverProfile(String access_token){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "Bearer " + access_token);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(null, headers);
        try{
            ResponseEntity<String> response = restTemplate.postForEntity("https://openapi.naver.com/v1/nid/me", request, String.class);
            if (response.getStatusCode() == HttpStatus.OK)
                return gson.fromJson(response.getBody(), NaverProfile.class);
        }catch (Exception e){
            throw new UserDefineException("communication fail with naver");
        }
        throw new UserDefineException("communication fail with naver");
    }

}
