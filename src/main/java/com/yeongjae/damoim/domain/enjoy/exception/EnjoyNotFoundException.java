package com.yeongjae.damoim.domain.enjoy.exception;

import com.yeongjae.damoim.global.error.ErrorCodeType;
import com.yeongjae.damoim.global.error.exception.BusinessLogicException;

public class EnjoyNotFoundException extends BusinessLogicException {
    public EnjoyNotFoundException() {
        super(ErrorCodeType.ENJOY_NOT_FOUND);
    }
}
