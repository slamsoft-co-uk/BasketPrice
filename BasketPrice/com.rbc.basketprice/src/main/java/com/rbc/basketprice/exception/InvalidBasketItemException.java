package com.rbc.basketprice.exception;

public class InvalidBasketItemException extends RuntimeException {
	public InvalidBasketItemException(String cause){
		super(cause);
	}
}
