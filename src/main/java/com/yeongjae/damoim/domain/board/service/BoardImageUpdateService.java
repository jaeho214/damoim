package com.yeongjae.damoim.domain.board.service;

import com.yeongjae.damoim.domain.board.dto.BoardUpdateDto;
import com.yeongjae.damoim.domain.board.entity.Board;
import com.yeongjae.damoim.domain.board.entity.BoardImage;
import com.yeongjae.damoim.domain.board.repository.BoardImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardImageUpdateService {

    private final BoardImageRepository boardImageRepository;

    public void saveBoardImage(BoardUpdateDto boardUpdateDto, Board board) {

        clearBoardImage(board);
        List<BoardImage> boardImageList = setBoardImage(boardUpdateDto, board);

        boardImageRepository.saveAll(boardImageList);

    }

    public void clearBoardImage(Board board){
        if (board.getImagePaths() != null) {
            board.getImagePaths().forEach(image -> image.delete());
            board.getImagePaths().clear();
        }
    }

    private List<BoardImage> setBoardImage(BoardUpdateDto boardUpdateDto, Board board){
        List<BoardImage> boardImageList = new ArrayList<>();
        boardUpdateDto.getImagePaths().forEach(imagePath ->
                boardImageList.add(
                        BoardImage.builder()
                                .imagePath(imagePath)
                                .board(board)
                                .build()
                ));

        boardImageList.forEach(image -> {
            board.addImage(image);
        });
        return boardImageList;
    }

}
