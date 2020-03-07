package com.yeongjae.damoim.domain.enjoy.controller;

import com.yeongjae.damoim.domain.enjoy.dto.EnjoyCreateDto;
import com.yeongjae.damoim.domain.enjoy.dto.EnjoyUpdateDto;
import com.yeongjae.damoim.domain.enjoy.entity.Enjoy;
import com.yeongjae.damoim.domain.enjoy.service.EnjoyCreateService;
import com.yeongjae.damoim.domain.enjoy.service.EnjoyDeleteService;
import com.yeongjae.damoim.domain.enjoy.service.EnjoyGetService;
import com.yeongjae.damoim.domain.enjoy.service.EnjoyUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @PostMapping
    public ResponseEntity createEnjoy(@RequestHeader("token") String token,
                                      @RequestBody EnjoyCreateDto enjoyCreateDto){
        Enjoy savedEnjoy = enjoyCreateService.createEnjoy(token, enjoyCreateDto);
        return ResponseEntity.created(URI.create("/damoim/enjoy" + savedEnjoy.getId())).body(savedEnjoy);
    }

    @GetMapping("/list/{location}")
    public ResponseEntity getEnjoys(@PathVariable String location,
                                    @RequestParam("pageNo") int pageNo){
        return ResponseEntity.ok(enjoyGetService.getEnjoys(location, pageNo));
    }

    @GetMapping("/{enjoy_id}")
    public ResponseEntity getEnjoy(@RequestHeader("token") String token,
                                   @PathVariable Long enjoy_id){
        return ResponseEntity.ok().body(enjoyGetService.getEnjoy(token, enjoy_id));
    }

    @PutMapping
    public ResponseEntity updateEnjoy(@RequestHeader("token") String token,
                                      @RequestBody EnjoyUpdateDto enjoyUpdateDto){
        return ResponseEntity.ok(enjoyUpdateService.updateEnjoy(token, enjoyUpdateDto));
    }

    @DeleteMapping("/{enjoy_id}")
    public ResponseEntity deleteEnjoy(@RequestHeader("token") String token,
                                      @PathVariable Long enjoy_id){
        return enjoyDeleteService.deleteEnjoy(token, enjoy_id);
    }

}
