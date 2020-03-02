package com.yeongjae.damoim.domain.deal.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.yeongjae.damoim.domain.board.entity.Board;
import com.yeongjae.damoim.domain.deal.entity.Deal;
import com.yeongjae.damoim.domain.deal.entity.QDeal;
import com.yeongjae.damoim.domain.deal.entity.QDealImage;
import com.yeongjae.damoim.domain.member.entity.QMember;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Component
public class DealRepositoryImpl extends QuerydslRepositorySupport implements DealRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    private QDeal deal = QDeal.deal;
    private QDealImage dealImage = QDealImage.dealImage;
    private QMember member = QMember.member;

    public DealRepositoryImpl(){
        super(Deal.class);
    }

    @Override
    public Optional<Deal> fetchById(Long deal_id) {
        JPAQuery<Deal> jpaQuery = new JPAQuery<>(entityManager);
        jpaQuery = jpaQuery.select(deal)
                .leftJoin(deal.imagePaths, dealImage)
                .leftJoin(deal.member, member)
                .fetchJoin()
                .where(deal.id.eq(deal_id));

        Optional<Deal> deal = Optional.ofNullable((Deal) jpaQuery.fetch());
        return deal;

    }
}
