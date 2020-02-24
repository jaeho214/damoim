package com.yeongjae.damoim.domain.enjoy.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.yeongjae.damoim.domain.enjoy.entity.Enjoy;
import com.yeongjae.damoim.domain.enjoy.entity.QEnjoy;
import com.yeongjae.damoim.domain.member.entity.QMember;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
        jpaQuery = jpaQuery.select(enjoy)
                .leftJoin(enjoy.member, member)
                .fetchJoin()
                .where(enjoy.id.eq(enjoy_id));

        Optional<Enjoy> board = Optional.ofNullable((Enjoy) jpaQuery.fetch());
        return board;

    }
}
