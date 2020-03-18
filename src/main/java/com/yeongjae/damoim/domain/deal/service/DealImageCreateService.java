package com.yeongjae.damoim.domain.deal.service;

import com.yeongjae.damoim.domain.board.entity.Board;
import com.yeongjae.damoim.domain.deal.dto.DealCreateDto;
import com.yeongjae.damoim.domain.deal.entity.Deal;
import com.yeongjae.damoim.domain.deal.entity.DealImage;
import com.yeongjae.damoim.domain.deal.repository.DealImageRepository;
import com.yeongjae.damoim.domain.deal.repository.DealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class DealImageCreateService {

    private final DealRepository dealRepository;
    private final DealImageRepository dealImageRepository;

    public Deal saveDealImage(DealCreateDto dealCreateDto, Deal deal){
        List<DealImage> dealImageList = setDealImage(dealCreateDto, deal);

        Deal savedDeal = dealRepository.save(deal);
        dealImageRepository.saveAll(dealImageList);

        return savedDeal;
    }

    private List<DealImage> setDealImage(DealCreateDto dealCreateDto, Deal deal){
        List<DealImage> dealImageList = new ArrayList<>();
        dealCreateDto.getImagePaths().forEach(imagePath ->
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
