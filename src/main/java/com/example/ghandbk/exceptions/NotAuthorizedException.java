package com.example.ghandbk.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class NotAuthorizedException extends Exception {

    public NotAuthorizedException(String message) {
        super(message);
    }
}
