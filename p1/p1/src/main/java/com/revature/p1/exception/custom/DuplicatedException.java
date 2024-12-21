package com.revature.p1.exception.custom;

public class DuplicatedException extends RuntimeException{
    public DuplicatedException(String msg){
        super(msg);
    }
    
}
