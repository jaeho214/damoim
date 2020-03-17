package com.yeongjae.damoim.domain.member.dto;

import com.yeongjae.damoim.domain.member.entity.Member;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberGetDto {
    private Long id;
    private LocalDateTime createdAt;
    private String email;
    private String nickName;
    private String location;
    private String phone;
    private String sex;
    private String imagePath;
    private Boolean isVerified = false;
    private String birth;

    @Builder
    public MemberGetDto(Long id, LocalDateTime createdAt, String email, String nickName, String location, String phone, String sex, String imagePath, Boolean isVerified, String birth) {
        this.id = id;
        this.createdAt = createdAt;
        this.email = email;
        this.nickName = nickName;
        this.location = location;
        this.phone = phone;
        this.sex = sex;
        this.imagePath = imagePath;
        this.isVerified = isVerified;
        this.birth = birth;
    }



    public static MemberGetDto toDto(Member member){
        return MemberGetDto.builder()
                .email(member.getEmail())
                .birth(member.getBirth())
                .imagePath(member.getImagePath())
                .location(member.getLocation())
                .isVerified(member.getIsVerified())
                .nickName(member.getNickName())
                .phone(member.getPhone())
                .sex(member.getSex())
                .createdAt(member.getCreatedAt())
                .id(member.getId())
                .build();
    }
}
