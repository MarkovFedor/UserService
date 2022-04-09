package com.mitrasoft.userservice.exceptions;

public class UsernameNotUniqueException extends Exception {
    public UsernameNotUniqueException(String message) {
        super(message);
    }
}
