package com.yeongjae.damoim.domain.board.repository;


import com.yeongjae.damoim.domain.board.entity.Board;
import com.yeongjae.damoim.domain.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom {
    Page<Board> findByLocation(String location, Pageable pageable);
    Page<Board> findByMember(Member member, Pageable pageable);
}
