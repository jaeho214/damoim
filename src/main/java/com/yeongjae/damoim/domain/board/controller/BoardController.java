package com.yeongjae.damoim.domain.board.controller;

import com.yeongjae.damoim.domain.board.dto.BoardCreateDto;
import com.yeongjae.damoim.domain.board.dto.BoardGetDto;
import com.yeongjae.damoim.domain.board.dto.BoardUpdateDto;
import com.yeongjae.damoim.domain.board.entity.Board;
import com.yeongjae.damoim.domain.board.service.BoardCreateService;
import com.yeongjae.damoim.domain.board.service.BoardDeleteService;
import com.yeongjae.damoim.domain.board.service.BoardGetService;
import com.yeongjae.damoim.domain.board.service.BoardUpdateService;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.objects.annotations.Getter;
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

    @ApiOperation("지역을 통해서 게시판 목록 불러오기 10개씩")
    @GetMapping("/list/{location}")
    public ResponseEntity getBoards(@RequestHeader("token") String token,
                                    @RequestParam("pageNo") int pageNo,
                                    @PathVariable String location){
        return ResponseEntity.ok().body(boardGetService.getBoards(token, pageNo, location));
    }

    @ApiOperation("게시물 상세보기")
    @GetMapping("/{id}")
    public ResponseEntity getBoard(@RequestHeader("token") String token,
                                   @PathVariable Long id){
        return ResponseEntity.ok().body(boardGetService.getBoard(token, id));
    }

    @ApiOperation("내가(회원) 쓴 게시물 목록 보기 10개씩")
    @GetMapping("user")
    public ResponseEntity getBoardByMember(@RequestHeader("token") String token,
                                           @RequestParam("pageNo") int pageNo){
        return ResponseEntity.ok().body(boardGetService.getBoardByMember(token, pageNo));
    }

    @ApiOperation("게시물 검색")
    @GetMapping("search/{location}/{keyword}")
    public ResponseEntity searchBoardByKeyword(@RequestHeader("token") String token,
                                               @PathVariable String location,
                                               @PathVariable String keyword,
                                               @RequestParam("pageNo") int pageNo){
        return ResponseEntity.ok().body(boardGetService.searchByKeyword(token, location, keyword, pageNo));
    }

    @ApiOperation("게시물 작성")
    @PostMapping
    public ResponseEntity createBoard(@RequestHeader("token") String token,
                                      @RequestBody BoardCreateDto boardCreateDto){
        BoardGetDto savedBoard = boardCreateService.createBoard(token, boardCreateDto);
        return ResponseEntity.created(URI.create("/damoim/board/" + savedBoard.getId())).body(savedBoard);
    }

    @ApiOperation("게시물 수정")
    @PutMapping
    public ResponseEntity updateBoard(@RequestHeader("token") String token,
                                      @RequestBody BoardUpdateDto boardUpdateDto){
        BoardGetDto updatedBoard = boardUpdateService.updateBoard(token, boardUpdateDto);
        return ResponseEntity.ok(updatedBoard);
    }

    @ApiOperation("게시물 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteBoard(@RequestHeader("token") String token,
                                      @PathVariable Long id){
        return boardDeleteService.deleteBoard(token, id);
    }


}
