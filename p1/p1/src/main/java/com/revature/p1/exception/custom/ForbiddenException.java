package com.revature.p1.exception.custom;

public  class ForbiddenException extends RuntimeException {
    public ForbiddenException(String message) {
        super(message);
    }
    
}