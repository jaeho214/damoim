package com.yeongjae.damoim.global.crawler;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CrawlerDto {
    List<String> contents = new ArrayList<>();
    List<String> date = new ArrayList<>();
    List<String> link = new ArrayList<>();
    List<String> source = new ArrayList<>();
    List<String> title = new ArrayList<>();
}
