package com.yeongjae.damoim.domain.board.exception;

import com.yeongjae.damoim.global.error.ErrorCodeType;
import com.yeongjae.damoim.global.error.exception.BusinessLogicException;

public class BoardNotFoundException extends BusinessLogicException {
    public BoardNotFoundException() {
        super(ErrorCodeType.BOARD_NOT_FOUND);
    }
}
