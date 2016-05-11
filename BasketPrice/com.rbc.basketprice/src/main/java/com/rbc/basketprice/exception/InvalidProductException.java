package com.rbc.basketprice.exception;

public class InvalidProductException extends RuntimeException {
	public InvalidProductException(String cause){
		super(cause);
	}
}
