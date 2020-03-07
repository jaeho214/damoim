package com.yeongjae.damoim.domain.enjoy.entity;

import lombok.Getter;

@Getter
public enum EnjoyCategory {

    SPORTS("운동"), TALK("수다"), PROJECT("프로젝트"), STUDY("스터디"), DRINK("술자리"), ETC("기타");

    private String category;
    EnjoyCategory(String category){
        this.category = category;
    }

    public static EnjoyCategory fromString(String requestString){
        for(EnjoyCategory category : EnjoyCategory.values()){
            if(category.getCategory().equals(requestString))
                return category;
        }
        return null;
    }
}
