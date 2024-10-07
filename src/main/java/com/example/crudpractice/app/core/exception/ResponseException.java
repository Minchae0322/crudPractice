package com.example.crudpractice.app.core.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ResponseException extends RuntimeException {

    private HttpStatus status;

    public ResponseException(String msg, HttpStatus status) {
        super(msg);
        this.status = status;
    }

    public ResponseException(HttpStatus status) {
        this.status = status;
    }

}

