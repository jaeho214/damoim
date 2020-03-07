package com.yeongjae.damoim.domain.member.entity;

import com.yeongjae.damoim.domain.member.dto.MemberUpdateDto;
import com.yeongjae.damoim.global.jpa.JpaBasePersistable;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@Table(name = "member")
@Entity
@AttributeOverride(name = "id", column = @Column(name = "member_id"))
@Where(clause = "deleted=0")
@DynamicUpdate
public class Member extends JpaBasePersistable {
    @Column(unique = true, name = "email", length = 50, nullable = false)
    private String email;
    @Column(name = "nick_name", length = 30, nullable = false)
    private String nickName;
    @Column(name = "password" , length = 30)
    private String password;
    @Column(name = "location", length = 50, nullable = false)
    private String location;
    @Column(unique = true, name = "phone", length = 20, nullable = false)
    private String phone;
    @Column(name = "sex", length = 5, nullable = false)
    private String sex;
    @Column(name = "imagePath")
    private String imagePath;
    @Column(name = "isVerified", nullable = false, columnDefinition = "BIT default 0")
    private Boolean isVerified = false;

    @Column(name = "birth", nullable = false, length = 20)
    private String birth;

    @Column(name = "fcmToken", nullable = false)
    private String fcmToken;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "provider", length = 100)
    private String provider;

    @Builder
    public Member(final String email,
                  final String nickName,
                  final String password,
                  final String location,
                  final String phone,
                  final String sex,
                  final String imagePath,
                  final String birth,
                  final Boolean isVerified,
                  final String role,
                  final String provider) {
        this.email = email;
        this.nickName = nickName;
        this.password = password;
        this.location = location;
        this.phone = phone;
        this.sex = sex;
        this.imagePath = imagePath;
        this.birth = birth;
        this.isVerified = isVerified;
        this.role = role;
        this.provider = provider;
    }

    public void updateMember(MemberUpdateDto memberUpdateDto){
        this.nickName = memberUpdateDto.getNickName();
        this.location = memberUpdateDto.getLocation();
        this.isVerified = memberUpdateDto.getIsVerified();
        this.phone = memberUpdateDto.getPhone();
        this.imagePath = memberUpdateDto.getImagePath();
    }
}
