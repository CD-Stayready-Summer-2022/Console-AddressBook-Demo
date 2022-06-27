package com.codedifferently.addressbook.exceptions;

public class UserDoesNotExistException extends Exception{
    public UserDoesNotExistException(String message) {
        super(message);
    }
}
