package com.yeongjae.damoim.domain.member.dto;

import com.yeongjae.damoim.domain.member.entity.Member;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberUpdateDto {

    private String nickName;
    private String password;
    private String address;
    private String phone;
    private Boolean isVerified;
    private String imagePath;

    public Member of(){
        return Member.builder()
                .nickName(this.nickName)
                .password(this.password)
                .address(this.address)
                .phone(this.phone)
                .isVerified(this.isVerified)
                .imagePath(this.imagePath)
                .build();
    }

    @Builder
    public MemberUpdateDto(String nickName, String password, String address, String phone, Boolean isVerified, String imagePath) {
        this.nickName = nickName;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.isVerified = isVerified;
        this.imagePath = imagePath;
    }
}
