package com.aop.demo.aspects;

public class CustomException extends RuntimeException {
    private int value;
    private String early_request;

    public CustomException(int value, String early_request) {
        this.value = value;
        this.early_request = early_request;
    }
}
