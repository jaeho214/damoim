package com.yeongjae.damoim.domain.deal.entity;

import lombok.Getter;

@Getter
public enum  DealStatus {
    SELLING("판매중"), TRADING("거래중"), SELL_COMPLETE("판매완료");

    private String status;
    DealStatus(String status){
        this.status = status;
    }
}
