package com.codedifferently.addressbook.exceptions;

public class UserCredentialsInvalidException extends Exception{
    public UserCredentialsInvalidException(String msg){
        super(msg);
    }
}
