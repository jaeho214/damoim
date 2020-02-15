package com.yeongjae.damoim.domain.interest.entity;


import com.yeongjae.damoim.domain.deal.entity.Deal;
import com.yeongjae.damoim.domain.member.entity.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Table(name = "interest")
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
}
