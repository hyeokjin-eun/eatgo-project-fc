package com.fast.eatgo.domain;

public class EmailNotExistedException extends RuntimeException {


    public EmailNotExistedException(String email) {
        super("Email is already registered : " + email);
    }
}
