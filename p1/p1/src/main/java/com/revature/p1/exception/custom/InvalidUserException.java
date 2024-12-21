package com.revature.p1.exception.custom;

public class InvalidUserException extends RuntimeException{
    public InvalidUserException(String msg){
        super(msg);
    }
}
