package com.java.my_hotel.dao;

import java.util.List;

import com.java.my_hotel.model.MenuItem;

public interface CartDao {
	public void addCartItem(long userId, long menuItemId);
	public List<MenuItem> getAllCartItems(long userId) throws CartEmptyException;
	public void removeCartItem(long userId);
}
