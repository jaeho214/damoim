package com.yeongjae.damoim.domain.board.service;

import com.yeongjae.damoim.domain.board.dto.BoardCreateDto;
import com.yeongjae.damoim.domain.board.entity.Board;
import com.yeongjae.damoim.domain.board.entity.BoardImage;
import com.yeongjae.damoim.domain.board.repository.BoardImageRepository;
import com.yeongjae.damoim.domain.board.repository.BoardRepository;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class BoardImageCreateServiceTest {

    @InjectMocks
    private BoardImageCreateService boardImageCreateService;
    @Mock
    private BoardRepository boardRepository;
    @Mock
    private BoardImageRepository boardImageRepository;

    private BoardImage boardImageFixture = new EasyRandom().nextObject(BoardImage.class);
    private BoardCreateDto boardCreateDtoFixture = new EasyRandom().nextObject(BoardCreateDto.class);
    private Board boardFixture = new EasyRandom().nextObject(Board.class);
    private List<BoardImage> boardImageListFixture = new ArrayList<>(Arrays.asList(boardImageFixture));

    @Test
    void saveBoardImage() {
        //given
        given(boardRepository.save(any(Board.class))).willReturn(boardFixture);
        given(boardImageRepository.saveAll(anyList())).willReturn(boardImageListFixture);

        //when
        Board savedBoard = boardImageCreateService.saveBoardImage(boardCreateDtoFixture, boardFixture);

        //then
        assertThat(savedBoard.getId()).isEqualTo(boardFixture.getId());
    }
}