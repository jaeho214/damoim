package com.yeongjae.damoim.domain.board.repository;


import com.yeongjae.damoim.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom {

}
