package com.yeongjae.damoim.domain.deal.repository;

import com.yeongjae.damoim.domain.deal.entity.DealImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DealImageRepository extends JpaRepository<DealImage, Long> {
    List<DealImage> findAllByDeal_Id(Long deal_id);
}
