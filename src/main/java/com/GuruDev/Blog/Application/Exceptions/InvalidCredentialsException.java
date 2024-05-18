package com.GuruDev.Blog.Application.Exceptions;

public class InvalidCredentialsException extends RuntimeException {

    public InvalidCredentialsException(String message) {
        super(message);
    }

    public InvalidCredentialsException() {
        super();
    }
}
