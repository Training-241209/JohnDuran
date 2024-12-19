package com.revature.p1.exception.custom;

public class UserAlreadyExistsException extends Exception{
    public UserAlreadyExistsException(String userName){
        super("Username already exists:"+ userName);
    }
}
