package com.java.my_hotel.dao;

import com.java.my_hotel.model.Cart;

public interface CartDao {
	public void addCartItem(long userId, long menuItemId);
	public Cart getAllCartItems(long userId) throws CartEmptyException;
	public void removeCartItem(long userId);
}
