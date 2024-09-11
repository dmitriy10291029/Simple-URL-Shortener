package com.example.url.shortener.exception;

public class LinkNotFoundException extends RuntimeException {
    public LinkNotFoundException(String code) {
        super(String.format("Link with code \"%s\" was not found", code));
    }
}
