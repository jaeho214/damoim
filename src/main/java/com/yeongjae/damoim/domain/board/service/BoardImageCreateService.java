package com.yeongjae.damoim.domain.board.service;

import com.yeongjae.damoim.domain.board.dto.BoardCreateDto;
import com.yeongjae.damoim.domain.board.entity.Board;
import com.yeongjae.damoim.domain.board.entity.BoardImage;
import com.yeongjae.damoim.domain.board.repository.BoardImageRepository;
import com.yeongjae.damoim.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardImageCreateService {

    private final BoardRepository boardRepository;
    private final BoardImageRepository boardImageRepository;

    public Board saveBoardImage(BoardCreateDto boardCreateDto, Board board){
        List<BoardImage> boardImageList = setBoardImage(boardCreateDto, board);
        Board savedBoard = boardRepository.save(board);
        boardImageRepository.saveAll(boardImageList);

        return savedBoard;
    }

    private List<BoardImage> setBoardImage(BoardCreateDto boardCreateDto, Board board){
        List<BoardImage> boardImageList = new ArrayList<>();
        boardCreateDto.getImagePaths().forEach(imagePath ->
                boardImageList.add(
                        BoardImage.builder()
                                .imagePath(imagePath)
                                .board(board)
                                .build()
                ));

        boardImageList.stream()
                .forEach(boardImage -> board.addImage(boardImage));

        return boardImageList;
    }
}
