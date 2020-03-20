package com.yeongjae.damoim.domain.enjoy.repository;

import com.yeongjae.damoim.domain.enjoy.entity.Enjoy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnjoyRepository extends JpaRepository<Enjoy, Long>, EnjoyRepositoryCustom {
}
