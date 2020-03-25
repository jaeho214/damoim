package com.yeongjae.damoim.domain.interest.controller;

import com.yeongjae.damoim.domain.interest.dto.InterestGetDto;
import com.yeongjae.damoim.domain.interest.entity.Interest;
import com.yeongjae.damoim.domain.interest.service.InterestCreateService;
import com.yeongjae.damoim.domain.interest.service.InterestDeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/damoim/interest")
@RequiredArgsConstructor
public class InterestController {
    private final InterestCreateService interestCreateService;
    private final InterestDeleteService interestDeleteService;

    @PostMapping("/{deal_id}")
    public ResponseEntity createInterest(@RequestHeader("token") String token,
                                         @PathVariable Long deal_id){
        InterestGetDto savedInterest = interestCreateService.createInterest(token, deal_id);
        return ResponseEntity.created(URI.create("/damoim/interest/" + savedInterest.getId())).body(savedInterest);
    }

    @DeleteMapping("/{deal_id}")
    public ResponseEntity deleteInterest(@RequestHeader("token") String token,
                                         @PathVariable Long deal_id){
        return interestDeleteService.deleteInterest(token, deal_id);
    }
}
