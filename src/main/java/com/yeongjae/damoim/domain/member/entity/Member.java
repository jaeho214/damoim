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
    @Column(name = "password" , length = 30, nullable = false)
    private String password;
    @Column(name = "address", length = 50, nullable = false)
    private String address;
    @Column(name = "phone", length = 20, nullable = false)
    private String phone;
    @Column(name = "sex", length = 5, nullable = false)
    private String sex;
    @Column(name = "imagePath")
    private String imagePath;
    @Column(name = "isVerified", nullable = false, columnDefinition = "BIT default 0")
    private Boolean isVerified = false;

    @Builder
    public Member(final String email,
                  final String nickName,
                  final String password,
                  final String address,
                  final String phone,
                  final String sex,
                  final String imagePath,
                  final Boolean isVerified) {
        this.email = email;
        this.nickName = nickName;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.sex = sex;
        this.imagePath = imagePath;
        this.isVerified = isVerified;
    }

    public void updateMember(MemberUpdateDto memberUpdateDto){
        this.nickName = memberUpdateDto.getNickName();
        this.address = memberUpdateDto.getAddress();
        this.isVerified = memberUpdateDto.getIsVerified();
        this.phone = memberUpdateDto.getPhone();
        this.imagePath = memberUpdateDto.getImagePath();
    }
}
