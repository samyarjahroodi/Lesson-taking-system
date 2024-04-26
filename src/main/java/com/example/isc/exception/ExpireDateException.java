package com.example.isc.exception;

public class ExpireDateException extends RuntimeException{

    public ExpireDateException(String message) {
        super(message);
    }
}
