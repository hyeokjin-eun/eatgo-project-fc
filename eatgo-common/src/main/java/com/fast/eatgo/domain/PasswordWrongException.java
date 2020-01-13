package com.fast.eatgo.domain;

public class PasswordWrongException extends RuntimeException {

    public PasswordWrongException () {
        super("Password is Wrong");
    }
}
