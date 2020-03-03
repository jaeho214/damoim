package com.yeongjae.damoim.global.oauth.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NaverProfile {
    private Response response;

    @Getter @Setter
    public static class Response{
        private String id;
        private String nickname;
        private String name;
        private String email;
        private String gender;
        private String birthday;
        private String profile_image;
    }
}
