package com.yeongjae.damoim.global.crawler.service;

import com.yeongjae.damoim.global.crawler.CrawlerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;

@Service
@RequiredArgsConstructor
public class CrawlerService {

    private final RestTemplate restTemplate;

    public CrawlerDto getCrawler(String location, int sort, String startDate, String endDate) throws Exception {
        StringBuilder url = new StringBuilder("http://localhost:5000/");
        try{
            url.append(URLEncoder.encode(location, "UTF-8"));
            url.append("/" + sort);
            url.append("/" + URLEncoder.encode(startDate, "UTF-8"));
            url.append("/" + URLEncoder.encode(endDate, "UTF-8"));
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        URI restURI = new URI(url.toString());
        CrawlerDto crawlerDto = restTemplate.getForObject(restURI, CrawlerDto.class);
        return crawlerDto;
    }
}
