package com.mitrasoft.userservice.exceptions;

import org.aspectj.weaver.ast.Not;

public class NotFoundByIdException extends Exception{
    public NotFoundByIdException(String message) {
        super(message);
    }
}
