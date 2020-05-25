package com.yeongjae.damoim.domain.member.entity;

import com.yeongjae.damoim.domain.keyword.entity.Keyword;
import com.yeongjae.damoim.domain.member.dto.MemberUpdateDto;
import com.yeongjae.damoim.global.jpa.JpaBasePersistable;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@Table(name = "tbl_member")
@Entity
@AttributeOverride(name = "id", column = @Column(name = "member_id"))
@Where(clause = "deleted=0")
@DynamicUpdate
public class Member extends JpaBasePersistable implements Serializable {
    @Column(unique = true, name = "email", length = 50, nullable = false)
    private String email;
    @Column(name = "nick_name", length = 30, nullable = false)
    private String nickName;
    @Column(name = "password" , length = 65)
    private String password;
    @Column(name = "location", length = 50)
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

    @Column(name = "fcmToken")
    private String fcmToken;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "provider", length = 100)
    private String provider;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Column(name = "keywords")
    private List<Keyword> keywords = new ArrayList<>();

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
                  final String provider,
                  final List<Keyword> keywords) {
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
        this.keywords = keywords;
    }

    public void updateMember(MemberUpdateDto memberUpdateDto){
        this.nickName = memberUpdateDto.getNickName();
        this.location = memberUpdateDto.getLocation();
        this.isVerified = memberUpdateDto.getIsVerified();
        this.phone = memberUpdateDto.getPhone();
        this.imagePath = memberUpdateDto.getImagePath();
    }

    public void addKeyword(Keyword keyword){
        this.keywords.add(keyword);
    }

    public void updatePassword(String password){
        this.password = password;
    }

    public void updateToken(String token){
        this.fcmToken = token;
    }
}
