package com.java.my_hotel.dao;

import java.lang.Exception;

public class CartEmptyException extends Throwable {
	public CartEmptyException(String message) {
		super(message);
	}
}
