package com.yeongjae.damoim.domain.deal.controller;

import com.yeongjae.damoim.domain.deal.dto.DealCreateDto;
import com.yeongjae.damoim.domain.deal.dto.DealGetDto;
import com.yeongjae.damoim.domain.deal.dto.DealUpdateDto;
import com.yeongjae.damoim.domain.deal.entity.Deal;
import com.yeongjae.damoim.domain.deal.service.DealCreateService;
import com.yeongjae.damoim.domain.deal.service.DealDeleteService;
import com.yeongjae.damoim.domain.deal.service.DealGetService;
import com.yeongjae.damoim.domain.deal.service.DealUpdateService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/damoim/deal")
public class DealController {
    private final DealCreateService dealCreateService;
    private final DealGetService dealGetService;
    private final DealUpdateService dealUpdateService;
    private final DealDeleteService dealDeleteService;

    @ApiOperation("중고 거래 등록")
    @PostMapping
    public ResponseEntity createDeal(@RequestHeader("token") String token,
                                     @RequestBody DealCreateDto dealCreateDto){
        DealGetDto savedDeal = dealCreateService.createDeal(token, dealCreateDto);
        return ResponseEntity.created(URI.create("damoim/deal/" + savedDeal.getId())).body(savedDeal);
    }

    @ApiOperation("우리 동네 중고 물품 조회")
    @GetMapping("list/{location}")
    public ResponseEntity getDeals(@PathVariable String location,
                                   @RequestParam("pageNo") int pageNo){
        return ResponseEntity.ok().body(dealGetService.getDeals(location, pageNo));
    }

    @ApiOperation("중고 거래 조회")
    @GetMapping("/{deal_id}")
    public ResponseEntity getDeal(@RequestHeader("token") String token,
                                  @PathVariable Long deal_id){
        return ResponseEntity.ok().body(dealGetService.getDeal(token, deal_id));
    }

    @ApiOperation("회원의 중고 거래 목록 조회")
    @GetMapping("/user")
    public ResponseEntity getDealByMember(@RequestHeader("token") String token,
                                          @RequestParam("pageNo") int pageNo){
        return ResponseEntity.ok(dealGetService.getDealByMember(token, pageNo));
    }

    @ApiOperation("카테고리별 중고 거래 목록 조회")
    @GetMapping("/category/{location}/{category}")
    public ResponseEntity getDealByCategory(@PathVariable String location,
                                            @PathVariable String category,
                                            @RequestParam("pageNo") int pageNo){
        return ResponseEntity.ok(dealGetService.getDealByCategory(location, category, pageNo));
    }

    @ApiOperation("키워드 검색")
    @GetMapping("/search/{location}/{keyword}")
    public ResponseEntity searchDealByKeyword(@PathVariable String location,
                                              @PathVariable String keyword,
                                              @RequestParam("pageNo") int pageNo){
        return ResponseEntity.ok(dealGetService.searchByKeyword(location, keyword, pageNo));
    }

    @ApiOperation("중고 물품 수정")
    @PutMapping
    public ResponseEntity updateDeal(@RequestHeader("token") String token,
                                     @RequestBody DealUpdateDto dealUpdateDto){
        return ResponseEntity.ok(dealUpdateService.updateDeal(token, dealUpdateDto));
    }

    @ApiOperation("중고 물품 삭제")
    @DeleteMapping("/{deal_id}")
    public ResponseEntity deleteDeal(@RequestHeader("token") String token,
                                     @PathVariable Long deal_id){
        return dealDeleteService.deleteDeal(token, deal_id);
    }
}
