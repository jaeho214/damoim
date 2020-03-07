package com.yeongjae.damoim.domain.member.dto;

import com.yeongjae.damoim.domain.member.entity.Member;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    private String location;
    @NotBlank(message = "전화번호를 입력하십시오.")
    private String phone;
    @NotBlank(message = "생년월일을 입력하시오.")
    private String birth;
    @NotBlank(message = "성별을 입력하시오")
    private String sex;
    @NotNull(message = "인증 여부를 입력하시오")
    private Boolean isVerified;
    private String imagePath;

    public Member of(){
        return Member.builder()
                .email(this.email)
                .nickName(this.nickName)
                .password(this.password)
                .location(this.location)
                .phone(this.phone)
                .sex(this.sex)
                .isVerified(this.isVerified)
                .birth(this.birth)
                .imagePath(this.imagePath)
                .role("USER")
                .build();
    }
    @Builder
    public MemberCreateDto(String email,
                           String nickName,
                           String password,
                           String location,
                           String phone,
                           String sex,
                           Boolean isVerified,
                           String birth,
                           String imagePath) {
        this.email = email;
        this.nickName = nickName;
        this.password = password;
        this.location = location;
        this.phone = phone;
        this.sex = sex;
        this.isVerified = isVerified;
        this.birth = birth;
        this.imagePath = imagePath;
    }
}
