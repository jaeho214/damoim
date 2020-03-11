package com.yeongjae.damoim.domain.interest.repository;

import com.yeongjae.damoim.domain.interest.entity.Interest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InterestRepository extends JpaRepository<Interest, Long> {
    Optional<Interest> findByMember_IdAndDeal_id(Long member_id, Long deal_id);
}
