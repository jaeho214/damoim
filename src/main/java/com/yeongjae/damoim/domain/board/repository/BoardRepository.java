package com.yeongjae.damoim.domain.board.repository;


import com.yeongjae.damoim.domain.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom {
    Page<Board> findByLocation(String location, Pageable pageable);
}
