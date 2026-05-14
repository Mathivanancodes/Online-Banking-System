package com.banking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ScheduledTransferException extends RuntimeException {
    public ScheduledTransferException(String message) {
        super(message);
    }
}
