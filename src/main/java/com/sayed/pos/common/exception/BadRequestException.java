package com.sayed.pos.common.exception;

import java.io.IOException;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
