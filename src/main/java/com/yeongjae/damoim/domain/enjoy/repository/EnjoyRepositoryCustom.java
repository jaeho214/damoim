package com.yeongjae.damoim.domain.enjoy.repository;

import com.yeongjae.damoim.domain.enjoy.entity.Enjoy;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface EnjoyRepositoryCustom {
    Optional<Enjoy> fetchEnjoyById(Long enjoy_id);
}
