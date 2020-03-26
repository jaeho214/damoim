package com.yeongjae.damoim.domain.enjoy.controller;

import com.yeongjae.damoim.domain.enjoy.dto.EnjoyCreateDto;
import com.yeongjae.damoim.domain.enjoy.dto.EnjoyGetDto;
import com.yeongjae.damoim.domain.enjoy.dto.EnjoyUpdateDto;
import com.yeongjae.damoim.domain.enjoy.entity.Enjoy;
import com.yeongjae.damoim.domain.enjoy.service.EnjoyCreateService;
import com.yeongjae.damoim.domain.enjoy.service.EnjoyDeleteService;
import com.yeongjae.damoim.domain.enjoy.service.EnjoyGetService;
import com.yeongjae.damoim.domain.enjoy.service.EnjoyUpdateService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/damoim/enjoy")
@RequiredArgsConstructor
public class EnjoyController {
    private final EnjoyCreateService enjoyCreateService;
    private final EnjoyGetService enjoyGetService;
    private final EnjoyUpdateService enjoyUpdateService;
    private final EnjoyDeleteService enjoyDeleteService;

    @ApiOperation("번개 모임 생성")
    @PostMapping
    public ResponseEntity createEnjoy(@RequestHeader("token") String token,
                                      @RequestBody EnjoyCreateDto enjoyCreateDto){
        EnjoyGetDto savedEnjoy = enjoyCreateService.createEnjoy(token, enjoyCreateDto);
        return ResponseEntity.created(URI.create("/damoim/enjoy" + savedEnjoy.getId())).body(savedEnjoy);
    }

    @ApiOperation("해당 지역의 번개 모임들 10개씩 페이지별 출력")
    @GetMapping("/list/{location}")
    public ResponseEntity getEnjoys(@PathVariable String location,
                                    @RequestParam("pageNo") int pageNo){
        return ResponseEntity.ok(enjoyGetService.getEnjoys(location, pageNo));
    }

    @ApiOperation("유저가 작성한 번개 목록 10개씩 페이지별 출력")
    @GetMapping("/user")
    public ResponseEntity getEnjoyByMember(@RequestHeader("token") String token,
                                           @RequestParam("pageNo") int pageNo){
        return ResponseEntity.ok(enjoyGetService.getEnjoyByMember(token, pageNo));
    }

    @ApiOperation("번개 모임 정보 상세보기")
    @GetMapping("/{enjoy_id}")
    public ResponseEntity getEnjoy(@RequestHeader("token") String token,
                                   @PathVariable Long enjoy_id){
        return ResponseEntity.ok().body(enjoyGetService.getEnjoy(token, enjoy_id));
    }

    @ApiOperation("키워드로 번개 모임 검색")
    @GetMapping("/search/{location}/{keyword}")
    public ResponseEntity searchEnjoyByKeyword(@PathVariable String location,
                                               @PathVariable String keyword,
                                               @RequestParam("pageNo") int pageNo){
        return ResponseEntity.ok().body(enjoyGetService.searchByKeyword(location, keyword, pageNo));
    }

    @ApiOperation("카테고리별 번개 모임 검색")
    @GetMapping("/category/{location}/{category}")
    public ResponseEntity getEnjoyByCategory(@PathVariable String location,
                                             @PathVariable String category,
                                             @RequestParam("pageNo") int pageNo){
        return ResponseEntity.ok().body(enjoyGetService.getEnjoyByCategory(location, category, pageNo));
    }

    @ApiOperation("번개 모임 내용 수정")
    @PutMapping
    public ResponseEntity updateEnjoy(@RequestHeader("token") String token,
                                      @RequestBody EnjoyUpdateDto enjoyUpdateDto){
        return ResponseEntity.ok(enjoyUpdateService.updateEnjoy(token, enjoyUpdateDto));
    }

    @ApiOperation("번개 모임 삭제")
    @DeleteMapping("/{enjoy_id}")
    public ResponseEntity deleteEnjoy(@RequestHeader("token") String token,
                                      @PathVariable Long enjoy_id){
        return enjoyDeleteService.deleteEnjoy(token, enjoy_id);
    }

}
