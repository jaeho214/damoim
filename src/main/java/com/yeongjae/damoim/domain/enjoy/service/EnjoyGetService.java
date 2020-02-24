package com.yeongjae.damoim.domain.enjoy.service;

import com.yeongjae.damoim.domain.enjoy.entity.Enjoy;
import com.yeongjae.damoim.domain.enjoy.exception.EnjoyNotFoundException;
import com.yeongjae.damoim.domain.enjoy.repository.EnjoyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnjoyGetService {
    private final EnjoyRepository enjoyRepository;

    @Transactional(readOnly = true)
    public List<Enjoy> getEnjoys(String location, int pageNo){
        Pageable pageable = PageRequest.of(pageNo, 10, Sort.Direction.ASC);
        Page<Enjoy> locationPages = enjoyRepository.findByLocation(location, pageable);

        return locationPages.stream()
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Enjoy getEnjoy(Long enjoy_id){
        Enjoy enjoy = enjoyRepository.fetchEnjoyById(enjoy_id).orElseThrow(EnjoyNotFoundException::new);
        enjoy.updateHits();
        return enjoy;
    }
}
