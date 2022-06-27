package com.codedifferently.addressbook.exceptions;

public class UserExistException extends Exception{
    public UserExistException(String message) {
        super(message);
    }
}
