package com.yeongjae.damoim.domain.board.service;

import com.yeongjae.damoim.domain.board.entity.Board;
import com.yeongjae.damoim.domain.board.exception.BoardNotFoundException;
import com.yeongjae.damoim.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardGetService {

    private final BoardRepository boardRepository;

    @Transactional(readOnly = true)
    public List<Board> getBoards(int pageNo, String location) {
        Pageable pageable = PageRequest.of(pageNo, 15, Sort.Direction.ASC);
        Page<Board> boards = boardRepository.findByLocation(location, pageable);
        return boards.stream()
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Board getBoard(Long id){
        return boardRepository.fetchBoardById(id).orElseThrow(BoardNotFoundException::new);
    }
}
