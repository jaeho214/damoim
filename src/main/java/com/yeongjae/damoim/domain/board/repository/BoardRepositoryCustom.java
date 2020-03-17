package com.yeongjae.damoim.domain.board.repository;

import com.yeongjae.damoim.domain.board.dto.BoardGetByLocationDto;
import com.yeongjae.damoim.domain.board.dto.BoardGetByMemberDto;
import com.yeongjae.damoim.domain.board.entity.Board;
import com.yeongjae.damoim.domain.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BoardRepositoryCustom {

    Optional<Board> fetchBoardById(Long board_id);
    Page<BoardGetByLocationDto> findByLocation(String location, Pageable pageable);
    Page<BoardGetByMemberDto> findByMember(Member member, Pageable pageable);
}
