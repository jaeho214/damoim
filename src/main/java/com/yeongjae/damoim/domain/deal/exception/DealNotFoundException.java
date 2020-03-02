package com.yeongjae.damoim.domain.deal.exception;

import com.yeongjae.damoim.global.error.ErrorCodeType;
import com.yeongjae.damoim.global.error.exception.BusinessLogicException;

public class DealNotFoundException extends BusinessLogicException {
    public DealNotFoundException() {
        super(ErrorCodeType.DEAL_NOT_FOUND);
    }
}
