package com.example.pedidosdemo.config.exception.beans;

public class PersistentException extends RuntimeException{

    public PersistentException(String message) {
        super(message);
    }

    public PersistentException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersistentException(Throwable cause) {
        super(cause);
    }
}
