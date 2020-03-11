package com.yeongjae.damoim.domain.interest.exception;

import com.yeongjae.damoim.global.error.ErrorCodeType;
import com.yeongjae.damoim.global.error.exception.BusinessLogicException;

public class InterestNotFoundException extends BusinessLogicException {
    public InterestNotFoundException() {
        super(ErrorCodeType.INTEREST_NOT_FOUND);
    }
}
