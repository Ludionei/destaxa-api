package com.destaxa.api.exception;

public class PaymentTimeoutException extends RuntimeException {

	private static final long serialVersionUID = 5945501685724402296L;

	public PaymentTimeoutException(String message) {
        super(message);
    }

    public PaymentTimeoutException(String message, Throwable cause) {
        super(message, cause);
    }

}