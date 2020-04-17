package com.payment.transfer.module.exception;

public class InsufficientFundException extends RuntimeException {


    public InsufficientFundException() {
        super();
    }

    public InsufficientFundException(String message) {
        super(message);
    }

    public InsufficientFundException(String message, Throwable cause) {
        super(message, cause);
    }
}
