package com.yeongjae.damoim.domain.member.dto;

import com.yeongjae.damoim.domain.location.entity.Location;
import com.yeongjae.damoim.domain.member.entity.Member;
import com.yeongjae.damoim.global.oauth.domain.KAKAOProfile;
import com.yeongjae.damoim.global.oauth.domain.NaverProfile;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberSocialCreateDto {
    @NotBlank(message = "주소를 입력하십시오.")
    private Location location;
    @NotBlank(message = "전화번호를 입력하십시오.")
    private String phone;
    @NotNull(message = "인증 여부를 입력하시오")
    private Boolean isVerified;
    private String imagePath;

    @Builder
    public MemberSocialCreateDto(Location location,
                                 String phone,
                                 Boolean isVerified,
                                 String imagePath) {
        this.location = location;
        this.phone = phone;
        this.isVerified = isVerified;
        this.imagePath = imagePath;
    }

    public Member of(KAKAOProfile profile){
        return Member.builder()
                .email(profile.getKakao_account().getEmail())
                .password(null)
                .nickName(profile.getProperties().getNickname())
                .location(this.location)
                .phone(this.phone)
                .sex(profile.getKakao_account().getGender())
                .birth(profile.getKakao_account().getBirthday())
                .imagePath(profile.getProperties().getProfile_image())
                .role("USER")
                .isVerified(this.isVerified)
                .provider("KAKAO")
                .build();
    }
    public Member of(NaverProfile profile){
        return Member.builder()
                .email(profile.getResponse().getEmail())
                .password(null)
                .nickName(profile.getResponse().getNickname())
                .location(this.location)
                .phone(this.phone)
                .sex(profile.getResponse().getGender())
                .birth(profile.getResponse().getBirthday())
                .imagePath(profile.getResponse().getProfile_image())
                .role("USER")
                .isVerified(this.isVerified)
                .provider("NAVER")
                .build();
    }
}
