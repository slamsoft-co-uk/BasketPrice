package com.rbc.basketprice.exception;

public class BasketItemNotFoundException extends RuntimeException {
	public BasketItemNotFoundException(String cause){
		super(cause);
	}
}
