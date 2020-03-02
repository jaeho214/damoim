package com.yeongjae.damoim.domain.deal.repository;

import com.yeongjae.damoim.domain.deal.entity.Deal;
import com.yeongjae.damoim.domain.location.entity.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealRepository extends JpaRepository<Deal, Long>, DealRepositoryCustom {
    Page<Deal> findByLocation(Location location, Pageable pageable);
}
