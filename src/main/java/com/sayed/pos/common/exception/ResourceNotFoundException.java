package com.sayed.pos.common.exception;


public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resource) {
        super(resource);
    }
}
