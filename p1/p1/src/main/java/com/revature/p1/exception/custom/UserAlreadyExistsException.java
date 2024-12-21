package com.revature.p1.exception.custom;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(String userName){
        super("Username already exists:"+ userName);
    }
}
