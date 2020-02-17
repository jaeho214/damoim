package com.yeongjae.damoim.domain.member.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberSignInDto {
    @Email(message = "이메일 형식에 맞추어 입력하십시오.")
    private String email;
    @NotBlank(message = "비밀번호를 입력하십시오.")
    private String password;

    @Builder
    public MemberSignInDto(String email,
                           String password) {
        this.email = email;
        this.password = password;
    }
}
