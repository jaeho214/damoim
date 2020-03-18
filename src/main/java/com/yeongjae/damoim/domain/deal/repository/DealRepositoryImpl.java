package com.yeongjae.damoim.domain.deal.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.yeongjae.damoim.domain.board.entity.Board;
import com.yeongjae.damoim.domain.deal.dto.DealGetByLocationDto;
import com.yeongjae.damoim.domain.deal.dto.DealGetByMemberDto;
import com.yeongjae.damoim.domain.deal.entity.Deal;
import com.yeongjae.damoim.domain.deal.entity.QDeal;
import com.yeongjae.damoim.domain.deal.entity.QDealImage;
import com.yeongjae.damoim.domain.member.entity.Member;
import com.yeongjae.damoim.domain.member.entity.QMember;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
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
        Deal deal = jpaQuery.select(this.deal)
                .from(this.deal)
                .innerJoin(this.deal.member, member).fetchJoin()
                .leftJoin(this.deal.imagePaths, dealImage).fetchJoin()
                .where(this.deal.id.eq(deal_id))
                .fetchOne();

        return Optional.ofNullable(deal);

    }

    @Override
    public Page<DealGetByLocationDto> findByLocation(String location, Pageable pageable) {
        JPAQuery<Deal> jpaQuery = new JPAQuery<>(entityManager);

        jpaQuery = jpaQuery.select(deal)
                .from(deal)
                .innerJoin(deal.member, member).fetchJoin()
                .where(deal.location.eq(location));

        List<Deal> dealList = getQuerydsl()
                .applyPagination(pageable, jpaQuery)
                .fetch();

        List<DealGetByLocationDto> dealGetByLocationDtoList = new ArrayList<>();
        dealList.forEach(deal ->
                dealGetByLocationDtoList.add(
                DealGetByLocationDto.builder()
                .createdAt(deal.getCreatedAt())
                .deal_id(deal.getId())
                .dealStatus(deal.getStatus())
                .title(deal.getTitle())
                .hits(deal.getHits())
                .writer(deal.getMember().getNickName())
                .build()
                ));

        return new PageImpl<>(dealGetByLocationDtoList, pageable, jpaQuery.fetchCount());
    }

    @Override
    public Page<DealGetByMemberDto> findByMember(Member member, Pageable pageable) {
        JPAQuery<Deal> jpaQuery = new JPAQuery<>(entityManager);

        jpaQuery = jpaQuery.select(deal)
                .from(deal)
                .innerJoin(deal.member, this.member)
                .where(deal.member.eq(member));

        List<Deal> dealList = getQuerydsl()
                .applyPagination(pageable, jpaQuery)
                .fetch();

        List<DealGetByMemberDto> dealGetByMemberDtoList = new ArrayList<>();
        dealList.forEach(deal ->
                dealGetByMemberDtoList.add(
                        DealGetByMemberDto.builder()
                                .createdAt(deal.getCreatedAt())
                                .deal_id(deal.getId())
                                .dealStatus(deal.getStatus())
                                .title(deal.getTitle())
                                .hits(deal.getHits())
                                .build()
                ));

        return new PageImpl<>(dealGetByMemberDtoList, pageable, jpaQuery.fetchCount());
    }
}
