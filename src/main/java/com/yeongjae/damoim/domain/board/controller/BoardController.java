package com.yeongjae.damoim.domain.board.controller;

import com.yeongjae.damoim.domain.board.dto.BoardCreateDto;
import com.yeongjae.damoim.domain.board.dto.BoardUpdateDto;
import com.yeongjae.damoim.domain.board.entity.Board;
import com.yeongjae.damoim.domain.board.service.BoardCreateService;
import com.yeongjae.damoim.domain.board.service.BoardDeleteService;
import com.yeongjae.damoim.domain.board.service.BoardGetService;
import com.yeongjae.damoim.domain.board.service.BoardUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/damoim/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardCreateService boardCreateService;
    private final BoardGetService boardGetService;
    private final BoardUpdateService boardUpdateService;
    private final BoardDeleteService boardDeleteService;

    @GetMapping("/list/{location}")
    public ResponseEntity getBoards(@RequestParam("pageNo") int pageNo,
                                    @PathVariable String location){
        return ResponseEntity.ok(boardGetService.getBoards(pageNo, location));
    }

    @GetMapping("/{id}")
    public ResponseEntity getBoard(@PathVariable Long id){
        return ResponseEntity.ok().body(boardGetService.getBoard(id));
    }

    @PostMapping
    public ResponseEntity createBoard(@RequestHeader("token") String token,
                                      @RequestBody BoardCreateDto boardCreateDto){
        Board savedBoard = boardCreateService.createBoard(token, boardCreateDto);
        return ResponseEntity.created(URI.create("/damoim/board/" + savedBoard.getId())).body(savedBoard);
    }

    @PutMapping
    public ResponseEntity updateBoard(@RequestHeader("token") String token,
                                      @RequestBody BoardUpdateDto boardUpdateDto){
        Board updatedBoard = boardUpdateService.updateBoard(token, boardUpdateDto);
        return ResponseEntity.ok(updatedBoard);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBoard(@RequestHeader("token") String token,
                                      @PathVariable Long id){
        return boardDeleteService.deleteBoard(token, id);
    }


}
