package com.yeongjae.damoim.domain.member.exception;

import com.yeongjae.damoim.global.error.ErrorCodeType;
import com.yeongjae.damoim.global.error.exception.BusinessLogicException;

public class DuplicatedEmailException extends BusinessLogicException {
    public DuplicatedEmailException() {
        super(ErrorCodeType.DUPLICATED_EMAIL);
    }
}
