package com.example.GHand.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE, reason = "Valor não aceito!!")
public class ValueNotAcceptedException extends Exception {

    public ValueNotAcceptedException(String message) {
        super(message);
    }
}
