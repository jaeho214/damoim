package com.yeongjae.damoim.domain.enjoy.repository;

import com.yeongjae.damoim.domain.enjoy.dto.EnjoyGetByLocationDto;
import com.yeongjae.damoim.domain.enjoy.dto.EnjoyGetByMemberDto;
import com.yeongjae.damoim.domain.enjoy.entity.Enjoy;
import com.yeongjae.damoim.domain.enjoy.entity.EnjoyCategory;
import com.yeongjae.damoim.domain.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface EnjoyRepositoryCustom {
    Optional<Enjoy> fetchEnjoyById(Long enjoy_id);
    Page<EnjoyGetByLocationDto> findByLocation(String location, Pageable pageable);
    Page<EnjoyGetByLocationDto> findByCategory(EnjoyCategory category, String location, Pageable pageable);
    Page<EnjoyGetByLocationDto> searchByKeyword(String keyword, String location, Pageable pageable);
    Page<EnjoyGetByMemberDto> findByMember(Member member, Pageable pageable);
}
