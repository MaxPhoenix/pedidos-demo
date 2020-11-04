package com.example.pedidosdemo.config.exception.handler;

import com.example.pedidosdemo.config.exception.beans.PersistentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PersistentException.class)
    public ResponseEntity<ApiError> databaseError(final PersistentException persistentException) {
       ApiError apiError = new ApiError();
       apiError.setErrorMessage(persistentException.getMessage());
       apiError.setErrorCode(HttpStatus.CONFLICT.value());
       return new ResponseEntity<>(apiError , HttpStatus.CONFLICT);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> databaseError(final IllegalArgumentException illegalArgumentException) {
        ApiError apiError = new ApiError();
        apiError.setErrorMessage(illegalArgumentException.getMessage());
        apiError.setErrorCode(HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(apiError , HttpStatus.CONFLICT);
    }
}
