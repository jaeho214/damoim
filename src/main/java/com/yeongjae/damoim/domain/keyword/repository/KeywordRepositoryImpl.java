package com.yeongjae.damoim.domain.keyword.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.yeongjae.damoim.domain.keyword.entity.Keyword;
import com.yeongjae.damoim.domain.keyword.entity.QKeyword;
import com.yeongjae.damoim.domain.member.entity.Member;
import com.yeongjae.damoim.domain.member.entity.QMember;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Component
public class KeywordRepositoryImpl extends QuerydslRepositorySupport implements KeywordRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    private QKeyword keyword = QKeyword.keyword1;
    private QMember member = QMember.member;

    public KeywordRepositoryImpl(){
        super(Keyword.class);
    }

    @Override
    public List<String> findDistinctKeywordAll() {
        JPAQuery<String> jpaQuery = new JPAQuery<>(entityManager);

        List<String> keywords = jpaQuery.select(keyword.keyword)
                .distinct()
                .from(keyword)
                .fetch();

        return keywords;
    }

    @Override
    public List<Keyword> findAllByMember(Member member) {
        JPAQuery<Keyword> jpaQuery = new JPAQuery<>(entityManager);

        List<Keyword> keywords = jpaQuery.select(keyword)
                .distinct()
                .from(keyword)
                .where(keyword.member.eq(member))
                .fetch();

        return keywords;
    }

    @Override
    public List<String> findAllMemberByKeywordAndLocation(String keyword, String location) {
        JPAQuery<Keyword> jpaQuery = new JPAQuery<>(entityManager);

        List<Keyword> keywords = jpaQuery.select(this.keyword)
                .distinct()
                .from(this.keyword)
                .innerJoin(this.keyword.member, member).fetchJoin()
                .where(this.keyword.keyword.eq(keyword))
                .where(this.keyword.member.location.eq(location))
                .fetch();

        List<String> tokens = new ArrayList<>();
        keywords.forEach(key ->
                tokens.add(key.getMember().getFcmToken())
        );

        return tokens;
    }
}
