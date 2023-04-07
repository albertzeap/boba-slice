package com.cognixia.jump.exception;

public class ToppingsLimitException extends Exception {
	
    private static final long serialVersionUID = 1L;

    public ToppingsLimitException(String dishName) {
		super("You have exceeded the limit of 5 toppings for your dish: " + dishName);
	}

}
