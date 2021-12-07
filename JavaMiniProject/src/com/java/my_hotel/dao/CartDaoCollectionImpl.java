package com.java.my_hotel.dao;

import java.util.HashMap;
import java.util.List;
import com.java.my_hotel.model.Cart;
import com.java.my_hotel.model.MenuItem;

public class CartDaoCollectionImpl implements CartDao {
	private HashMap<Long, Cart> userCarts;
	
	public void addCartItem(long userId, long menuItemId) {
		
	}
	
	public List<MenuItem> getAllCartItems(long userId) throws CartEmptyException {
		List<MenuItem> cartItems;
		
		return cartItems;
	}
	
	public void removeCartItem(long userId) {
		
	}
}
