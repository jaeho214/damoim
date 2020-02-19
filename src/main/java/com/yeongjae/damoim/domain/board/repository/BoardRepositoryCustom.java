package com.yeongjae.damoim.domain.board.repository;

import com.yeongjae.damoim.domain.board.entity.Board;

import java.util.Optional;

public interface BoardRepositoryCustom {

    Optional<Board> fetchBoardById(Long board_id);
}
