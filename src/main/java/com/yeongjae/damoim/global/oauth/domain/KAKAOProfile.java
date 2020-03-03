package com.yeongjae.damoim.global.oauth.domain;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class KAKAOProfile {
    private Long kakaoProfile_id;
    private Properties properties;
    private KakaoAccount kakao_account;

    @Getter @Setter
    public static class Properties{
        private String nickname;
        private String thumbnail_image;
        private String profile_image;
    }

    @Getter @Setter
    public static class KakaoAccount{
        private Boolean has_email;
        private Boolean is_email_valid;
        private Boolean is_email_verified;
        private String email;
        private Boolean has_birthday;
        private String birthday;
        private Boolean has_gender;
        private String gender;
    }
}
