package com.yeongjae.damoim.domain.deal.controller;

import com.yeongjae.damoim.domain.deal.dto.DealCreateDto;
import com.yeongjae.damoim.domain.deal.dto.DealUpdateDto;
import com.yeongjae.damoim.domain.deal.entity.Deal;
import com.yeongjae.damoim.domain.deal.service.DealCreateService;
import com.yeongjae.damoim.domain.deal.service.DealDeleteService;
import com.yeongjae.damoim.domain.deal.service.DealGetService;
import com.yeongjae.damoim.domain.deal.service.DealUpdateService;
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

    @PostMapping
    public ResponseEntity createDeal(@RequestHeader("token") String token,
                                     @RequestBody DealCreateDto dealCreateDto){
        Deal savedDeal = dealCreateService.createDeal(token, dealCreateDto);
        return ResponseEntity.created(URI.create("damoim/deal/" + savedDeal.getId())).body(savedDeal);
    }

    @GetMapping("list/{location}")
    public ResponseEntity getDeals(@PathVariable String location,
                                   @RequestParam("pageNo") int pageNo){
        return ResponseEntity.ok(dealGetService.getDeals(location, pageNo));
    }

    @GetMapping("/{deal_id}")
    public ResponseEntity getDeal(@RequestHeader("token") String token,
                                  @PathVariable Long deal_id){
        return ResponseEntity.ok().body(dealGetService.getDeal(token, deal_id));
    }

    @PutMapping
    public ResponseEntity updateDeal(@RequestHeader("token") String token,
                                     @RequestBody DealUpdateDto dealUpdateDto){
        return ResponseEntity.ok(dealUpdateService.updateDeal(token, dealUpdateDto));
    }

    @DeleteMapping("/{deal_id}")
    public ResponseEntity deleteDeal(@RequestHeader("token") String token,
                                     @PathVariable Long deal_id){
        return dealDeleteService.deleteDeal(token, deal_id);
    }
}
