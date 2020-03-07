package com.yeongjae.damoim.domain.member.dto;

import com.yeongjae.damoim.domain.member.entity.Member;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberUpdateDto {

    private String nickName;
    private String password;
    private String location;
    private String phone;
    private String birth;
    private Boolean isVerified;
    private String imagePath;

    public Member of(){
        return Member.builder()
                .nickName(this.nickName)
                .password(this.password)
                .location(this.location)
                .phone(this.phone)
                .birth(this.birth)
                .isVerified(this.isVerified)
                .imagePath(this.imagePath)
                .build();
    }

    @Builder
    public MemberUpdateDto(String nickName, String password, String location, String phone, String birth, Boolean isVerified, String imagePath) {
        this.nickName = nickName;
        this.password = password;
        this.location = location;
        this.phone = phone;
        this.birth = birth;
        this.isVerified = isVerified;
        this.imagePath = imagePath;
    }
}
