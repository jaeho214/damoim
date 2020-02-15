package com.yeongjae.damoim.domain.deal.entity;

import com.yeongjae.damoim.domain.member.entity.Member;
import com.yeongjae.damoim.global.jpa.JpaBasePersistable;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Table(name = "deal")
@Entity
@AttributeOverride(name = "id", column = @Column(name = "deal_id"))
@Where(clause = "deleted=0")
@DynamicUpdate
public class Deal extends JpaBasePersistable {
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
    private DealCategory category = DealCategory.ETC;

    @Column(name = "price", length = 25, nullable = false)
    private String price;

    @Column(name = "status", length = 10, nullable = false)
    @Enumerated(value = EnumType.STRING)
    private DealStatus status;

    @ManyToOne(targetEntity = Member.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Builder
    public Deal(final String title,
                final String content,
                final Long hits,
                final String location,
                final DealCategory category,
                final String price,
                final DealStatus status,
                final Member member) {
        this.title = title;
        this.content = content;
        this.hits = hits;
        this.location = location;
        this.category = category;
        this.price = price;
        this.status = status;
        this.member = member;
    }

    public void updateStatus(DealStatus status){
        this.status = status;
    }

    public void updateDeal(String title, String content, DealCategory category, String price){
        this.title = title;
        this.content = content;
        this.category = category;
        this.price = price;
    }

    public void updateHits(){
        this.hits++;
    }
}
