package com.yeongjae.damoim.domain.reply.exception;

import com.yeongjae.damoim.global.error.ErrorCodeType;
import com.yeongjae.damoim.global.error.exception.BusinessLogicException;

public class ReplyNotFoundException extends BusinessLogicException {
    public ReplyNotFoundException() {
        super(ErrorCodeType.REPLY_NOT_FOUND);
    }
}
