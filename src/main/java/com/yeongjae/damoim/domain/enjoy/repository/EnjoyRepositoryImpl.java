package com.yeongjae.damoim.domain.enjoy.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.yeongjae.damoim.domain.enjoy.dto.EnjoyGetByLocationDto;
import com.yeongjae.damoim.domain.enjoy.dto.EnjoyGetByMemberDto;
import com.yeongjae.damoim.domain.enjoy.entity.Enjoy;
import com.yeongjae.damoim.domain.enjoy.entity.QEnjoy;
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
public class EnjoyRepositoryImpl extends QuerydslRepositorySupport implements EnjoyRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    private QEnjoy enjoy = QEnjoy.enjoy;
    private QMember member = QMember.member;

    public EnjoyRepositoryImpl(){
        super(Enjoy.class);
    }


    @Override
    public Optional<Enjoy> fetchEnjoyById(Long enjoy_id) {
        JPAQuery<Enjoy> jpaQuery = new JPAQuery<>(entityManager);
        Enjoy enjoy = jpaQuery.select(this.enjoy)
                .from(this.enjoy)
                .innerJoin(this.enjoy.member, member).fetchJoin()
                .where(this.enjoy.id.eq(enjoy_id))
                .fetchOne();

        return Optional.ofNullable(enjoy);
    }

    @Override
    public Page<EnjoyGetByLocationDto> findByLocation(String location, Pageable pageable) {
        JPAQuery<Enjoy> jpaQuery = new JPAQuery<>(entityManager);
        jpaQuery = jpaQuery.select(enjoy)
                .from(enjoy)
                .innerJoin(enjoy.member, member).fetchJoin()
                .where(enjoy.location.eq(location));

        List<Enjoy> enjoyList = getQuerydsl()
                .applyPagination(pageable, jpaQuery)
                .fetch();

        List<EnjoyGetByLocationDto> enjoyGetByLocationDtoList = new ArrayList<>();
        enjoyList.forEach(enjoy ->
                enjoyGetByLocationDtoList.add(
                        EnjoyGetByLocationDto.builder()
                        .id(enjoy.getId())
                        .title(enjoy.getTitle())
                        .category(enjoy.getCategory())
                        .createdAt(enjoy.getCreatedAt())
                        .hits(enjoy.getHits())
                        .writer(enjoy.getMember().getNickName())
                        .recruit(enjoy.getRecruit())
                        .build()
                )
        );
        return new PageImpl<>(enjoyGetByLocationDtoList, pageable, jpaQuery.fetchCount());
    }

    @Override
    public Page<EnjoyGetByMemberDto> findByMember(Member member, Pageable pageable) {
        JPAQuery<Enjoy> jpaQuery = new JPAQuery<>(entityManager);
        jpaQuery = jpaQuery.select(enjoy)
                .from(enjoy)
                .where(enjoy.member.eq(member));

        List<Enjoy> enjoyList = getQuerydsl()
                .applyPagination(pageable, jpaQuery)
                .fetch();

        List<EnjoyGetByMemberDto> enjoyGetByMemberDtoList = new ArrayList<>();
        enjoyList.forEach(enjoy ->
                enjoyGetByMemberDtoList.add(
                        EnjoyGetByMemberDto.builder()
                                .id(enjoy.getId())
                                .title(enjoy.getTitle())
                                .category(enjoy.getCategory())
                                .createdAt(enjoy.getCreatedAt())
                                .hits(enjoy.getHits())
                                .location(enjoy.getLocation())
                                .build()
                )
        );
        return new PageImpl<>(enjoyGetByMemberDtoList, pageable, jpaQuery.fetchCount());
    }
}
