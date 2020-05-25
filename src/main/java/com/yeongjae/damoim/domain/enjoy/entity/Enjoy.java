package com.yeongjae.damoim.domain.enjoy.entity;

import com.yeongjae.damoim.domain.enjoy.dto.EnjoyUpdateDto;
import com.yeongjae.damoim.domain.member.entity.Member;
import com.yeongjae.damoim.global.jpa.JpaBasePersistable;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Table(name = "tbl_enjoy")
@Entity
@AttributeOverride(name = "id", column = @Column(name = "enjoy_id"))
@Where(clause = "deleted=0")
@DynamicUpdate
public class Enjoy extends JpaBasePersistable {
    @Column(name = "title", length = 30, nullable = false)
    private String title;

    @Column(name = "content" , nullable = false)
    private String content;

    @Column(name = "hits", nullable = false)
    private Long hits = 0L;

    @Column(name = "location", length = 50, nullable = false)
    private String location;

    @Column(name = "category", length = 30, nullable = false)
    @Enumerated(value = EnumType.STRING)
    private EnjoyCategory category = EnjoyCategory.ETC;

    @Column(name = "latitude", length = 50, nullable = false)
    private String latitude;
    @Column(name = "longitude", length = 50, nullable = false)
    private String longitude;

    @Column(name = "recruit", nullable = false)
    private Integer recruit;

    @ManyToOne(targetEntity = Member.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Builder
    public Enjoy(String title, String content, Long hits, String location, EnjoyCategory category, String latitude, String longitude, Integer recruit, Member member) {
        this.title = title;
        this.content = content;
        this.hits = hits;
        this.location = location;
        this.category = category;
        this.latitude = latitude;
        this.longitude = longitude;
        this.recruit = recruit;
        this.member = member;
    }

    public void updateEnjoy(EnjoyUpdateDto enjoyUpdateDto) {
        this.title = enjoyUpdateDto.getTitle();
        this.content = enjoyUpdateDto.getContent();
        this.category = EnjoyCategory.fromString(enjoyUpdateDto.getCategory());
        this.latitude = enjoyUpdateDto.getLatitude();
        this.longitude = enjoyUpdateDto.getLongitude();
        this.recruit = enjoyUpdateDto.getRecruit();
    }

    public void updateHits(){
        this.hits++;
    }
}
