package com.java.my_hotel.dao;

import java.util.List;
import java.util.Arrays;
import java.util.HashMap;

import com.java.my_hotel.model.Cart;
import com.java.my_hotel.model.MenuItem;

public class CartDaoCollectionImpl implements CartDao {
	private HashMap<Long, Cart> userCarts;
	
	public CartDaoCollectionImpl() {
		MenuItemDao menuItemDao = new MenuItemDaoCollectionImpl();
		HashMap<Long, Cart> userCarts = new HashMap<Long, Cart>();
		
		userCarts.put((long) 0, new Cart(Arrays.asList(menuItemDao.getMenuItem(0),
				menuItemDao.getMenuItem(1), menuItemDao.getMenuItem(2)), 8.00));
		userCarts.put((long) 1, new Cart(Arrays.asList(menuItemDao.getMenuItem(0),
				menuItemDao.getMenuItem(3)), 9.50));
		userCarts.put((long) 2, new Cart(Arrays.asList(menuItemDao.getMenuItem(1),
				menuItemDao.getMenuItem(2), menuItemDao.getMenuItem(4)), 16.50));
		
		if(this.userCarts == null) {
			this.userCarts = new HashMap<Long, Cart>();
		}
		
		userCarts.forEach((key, value) -> this.userCarts.put(key, value));
	}

	public void addCartItem(long userId, long menuItemId) {
		MenuItemDaoCollectionImpl m = new MenuItemDaoCollectionImpl();
		MenuItem mi = m.getMenuItem(menuItemId);
		
		if(this.userCarts.containsKey(userId)) {
			List<MenuItem> mil = this.userCarts.get(userId).getMenuItemList();
			mil.add(mi);
		}
		else {
			Cart c = new Cart(Arrays.asList(), 0);
			List<MenuItem> mil = c.getMenuItemList();
			mil.add(mi);
			
			this.userCarts.put(userId, c);
		}
	}
	
	public Cart getAllCartItems(long userId) throws CartEmptyException {
		List<MenuItem> cartItems = this.userCarts.get(userId).getMenuItemList();
		
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
		List<MenuItem> cartItems = this.userCarts.get(userId).getMenuItemList();
		
		for(MenuItem m : cartItems) {
			if(userId == m.getId()) {
				cartItems.remove(m);
				break;
			}
		}
	}
}
