package com.java.my_hotel.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.java.my_hotel.model.Cart;
import com.java.my_hotel.model.MenuItem;

public class CartDaoCollectionImpl implements CartDao {
	private HashMap<Long, Cart> userCarts;
	
	public CartDaoCollectionImpl(HashMap<Long, Cart> userCarts) {
		super();
		
		if(this.userCarts == null) {
			this.userCarts = new HashMap<Long, Cart>();
		}
		
		userCarts.forEach((key, value) -> this.userCarts.put(key, value));
	}

	public void addCartItem(long userId, long menuItemId) {
		MenuItemDaoCollectionImpl m = new MenuItemDaoCollectionImpl();
		
		MenuItem mi = m.getMenuItem(menuItemId);
		
		if(this.userCarts.containsKey(userId)) {
			ArrayList<MenuItem> mil = this.userCarts.get(userId).getMenuItemList();
			mil.add(mi);
		}
		else {
			Cart c = new Cart(new ArrayList<MenuItem>(), 0);
			ArrayList<MenuItem> mil = c.getMenuItemList();
			mil.add(mi);
			
			this.userCarts.put(userId, c);
		}
	}
	
	public Cart getAllCartItems(long userId) throws CartEmptyException {
		ArrayList<MenuItem> cartItems = this.userCarts.get(userId).getMenuItemList();
		
		if(cartItems.isEmpty()) {
			throw new CartEmptyException();
		}
		
		double total = 0;
		for(MenuItem m : cartItems) {
			total += m.getPrice();
		}
		
		this.userCarts.get(userId).setTotal(total);
			
		return this.userCarts.get(userId);
	}
	
	public void removeCartItem(long userId) {
		ArrayList<MenuItem> cartItems = this.userCarts.get(userId).getMenuItemList();
		
		for(MenuItem m : cartItems) {
			if(userId == m.getId()) {
				cartItems.remove(m);
				break;
			}
		}
	}
}
