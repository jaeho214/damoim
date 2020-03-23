package com.yeongjae.damoim.domain.like.repository;

import com.yeongjae.damoim.domain.like.entity.BoardLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<BoardLike, Long> {
    Optional<BoardLike> findByMember_IdAndBoard_Id(Long member_id, Long board_id);
}
