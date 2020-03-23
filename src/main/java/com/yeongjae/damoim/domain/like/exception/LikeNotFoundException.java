package com.yeongjae.damoim.domain.like.exception;

import com.yeongjae.damoim.global.error.ErrorCodeType;
import com.yeongjae.damoim.global.error.exception.BusinessLogicException;

public class LikeNotFoundException extends BusinessLogicException {
    public LikeNotFoundException() {
        super(ErrorCodeType.LIKE_NOT_FOUND);
    }
}
