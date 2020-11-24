package com.group0179.exceptions;

//i am unsure whether i should throw or not
public class RequestNotFoundException extends Exception {
    public RequestNotFoundException(String message){
        super(message);
    }
}
