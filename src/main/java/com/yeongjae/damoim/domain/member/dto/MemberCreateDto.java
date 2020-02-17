package com.yeongjae.damoim.domain.member.dto;

import com.yeongjae.damoim.domain.member.entity.Member;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberCreateDto {
    @Email(message = "이메일 형식에 맞추어 입력하십시오.")
    private String email;
    @NotBlank(message = "별명을 입력하십시오.")
    private String nickName;
    @NotBlank(message = "비밀번호를 입력하십시오.")
    private String password;
    @NotBlank(message = "주소를 입력하십시오.")
    private String address;
    @NotBlank(message = "전화번호를 입력하십시오.")
    private String phone;
    private String sex;
    private Boolean isVerified;
    private String imagePath;

    public Member of(){
        return Member.builder()
                .email(this.email)
                .nickName(this.nickName)
                .password(this.password)
                .address(this.address)
                .phone(this.phone)
                .sex(this.sex)
                .isVerified(this.isVerified)
                .imagePath(this.imagePath)
                .build();
    }
    @Builder
    public MemberCreateDto(String email,
                           String nickName,
                           String password,
                           String address,
                           String phone,
                           String sex,
                           Boolean isVerified,
                           String imagePath) {
        this.email = email;
        this.nickName = nickName;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.sex = sex;
        this.isVerified = isVerified;
        this.imagePath = imagePath;
    }
}
