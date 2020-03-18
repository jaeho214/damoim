package com.yeongjae.damoim.domain.deal.service;

import com.yeongjae.damoim.domain.deal.dto.DealUpdateDto;
import com.yeongjae.damoim.domain.deal.entity.Deal;
import com.yeongjae.damoim.domain.deal.entity.DealImage;
import com.yeongjae.damoim.domain.deal.repository.DealImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class DealImageUpdateService {

    private final DealImageRepository dealImageRepository;

    public void saveDealImage(DealUpdateDto dealUpdateDto, Deal deal){

        clearDealImage(deal);
        dealImageRepository.saveAll(setDealImage(dealUpdateDto, deal));
    }

    public void clearDealImage(Deal deal){
        if (deal.getImagePaths() != null) {
            deal.getImagePaths().forEach(dealImage -> dealImage.delete());
            deal.getImagePaths().clear();
        }
    }

    private List<DealImage> setDealImage(DealUpdateDto dealUpdateDto, Deal deal){
        List<DealImage> dealImageList = new ArrayList<>();
        dealUpdateDto.getImagePaths().forEach(imagePath ->
                dealImageList.add(
                        DealImage.builder()
                                .imagePath(imagePath)
                                .deal(deal)
                                .build()
                ));
        dealImageList.forEach(dealImage -> deal.addImage(dealImage));

        return dealImageList;
    }
}
