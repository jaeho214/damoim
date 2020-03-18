package com.yeongjae.damoim.domain.deal.repository;

import com.yeongjae.damoim.domain.deal.entity.Deal;
import com.yeongjae.damoim.domain.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealRepository extends JpaRepository<Deal, Long>, DealRepositoryCustom {

}
