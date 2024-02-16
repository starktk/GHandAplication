package com.example.ghandbk.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
public class InvalidValueException extends Exception{

    public InvalidValueException(String message) {
        super(message);
    }
}
