package com.yeongjae.damoim.domain.deal.entity;

import lombok.Getter;

@Getter
public enum DealCategory {
    DIGITAL("디지털/가전"), FURNITURE("가구/인테리어"), KIDS("유아용품"), DAILY("생활용품"), WOMAN_CLOTHES("여성의류"),
    ACCESSORY("잡화"), BEAUTY("미용/뷰티"), MAN_CLOTHES("남성의류"), SPORTS("스포츠/레저"), GAME("게임"), BOOK("도서/티켓/음반"), PET("반려동물"), ETC("기타");

    private String category;
    DealCategory(String category){
        this.category = category;
    }

    public static DealCategory fromString(String requestString){
        for(DealCategory category : DealCategory.values()){
            if(category.getCategory().equals(requestString))
                return category;
        }
        return null;
    }
}
