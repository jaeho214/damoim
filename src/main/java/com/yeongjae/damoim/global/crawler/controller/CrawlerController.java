package com.yeongjae.damoim.global.crawler.controller;

import com.yeongjae.damoim.global.crawler.service.CrawlerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/damoim/news")
@RequiredArgsConstructor
public class CrawlerController {

    private final CrawlerService crawlerService;

    @GetMapping("/{location}/{sort}/{startDate}/{endDate:.+}")
    public ResponseEntity getCrawler(@PathVariable String location,
                                     @PathVariable int sort,
                                     @PathVariable String startDate,
                                     @PathVariable String endDate) throws Exception {
        return ResponseEntity.ok(crawlerService.getCrawler(location, sort, startDate, endDate));
    }
}
