package com.yeongjae.damoim.domain.deal.repository;

import com.yeongjae.damoim.domain.deal.entity.Deal;

import java.util.Optional;

public interface DealRepositoryCustom {
    Optional<Deal> fetchById(Long deal_id);
}
