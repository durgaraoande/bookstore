package com.abdr.bookstore;

public class UserFoundException extends Exception {
    public UserFoundException() {
        super("Username already exists");
    }
}
