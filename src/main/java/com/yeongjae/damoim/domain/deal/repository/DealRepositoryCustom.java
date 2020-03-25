package com.yeongjae.damoim.domain.deal.repository;

import com.yeongjae.damoim.domain.deal.dto.DealGetByLocationDto;
import com.yeongjae.damoim.domain.deal.dto.DealGetByMemberDto;
import com.yeongjae.damoim.domain.deal.entity.Deal;
import com.yeongjae.damoim.domain.deal.entity.DealCategory;
import com.yeongjae.damoim.domain.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface DealRepositoryCustom {
    Optional<Deal> fetchById(Long deal_id);
    Page<DealGetByLocationDto> findByLocation(String location, Pageable pageable);
    Page<DealGetByMemberDto> findByMember(Member member, Pageable pageable);
    Page<DealGetByLocationDto> findByCategory(DealCategory category, String location, Pageable pageable);
    Page<DealGetByLocationDto> searchByKeyword(String keyword, String location, Pageable pageable);
}
