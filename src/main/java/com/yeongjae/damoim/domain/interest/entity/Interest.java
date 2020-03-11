package com.yeongjae.damoim.domain.interest.entity;


import com.yeongjae.damoim.domain.deal.entity.Deal;
import com.yeongjae.damoim.domain.member.entity.Member;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Table(
        name = "interest",
        uniqueConstraints = {
            @UniqueConstraint(
                columnNames = {"member_id", "deal_id"}
            )
        }
        )
@Entity
public class Interest {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long interest_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deal_id")
    private Deal deal;

    @Builder
    public Interest(Member member, Deal deal) {
        this.member = member;
        this.deal = deal;
    }
}
