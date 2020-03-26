package com.yeongjae.damoim.domain.keyword.repository;

import com.yeongjae.damoim.domain.keyword.entity.Keyword;
import com.yeongjae.damoim.domain.member.entity.Member;

import java.util.List;

public interface KeywordRepositoryCustom {
    List<String> findDistinctKeywordAll();
    List<Keyword> findAllByMember(Member member);
    List<String> findAllMemberByKeywordAndLocation(String keyword, String location);
}
