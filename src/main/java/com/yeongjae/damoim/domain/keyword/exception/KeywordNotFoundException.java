package com.yeongjae.damoim.domain.keyword.exception;

import com.yeongjae.damoim.global.error.ErrorCodeType;
import com.yeongjae.damoim.global.error.exception.BusinessLogicException;

public class KeywordNotFoundException extends BusinessLogicException {
    public KeywordNotFoundException() {
        super(ErrorCodeType.KEYWORD_NOT_FOUND);
    }
}
