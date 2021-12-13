package com.java.my_hotel.dao;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

import com.java.my_hotel.model.Cart;
import com.java.my_hotel.model.MenuItem;

public class CartDaoCollectionImpl implements CartDao {
	private HashMap<Long, Cart> userCarts;
	
	public CartDaoCollectionImpl() {
		MenuItemDao menuItemDao = new MenuItemDaoCollectionImpl();
		HashMap<Long, Cart> userCarts = new HashMap<Long, Cart>();
		
		ArrayList<MenuItem> al1 = new ArrayList<MenuItem>();
		al1.add(menuItemDao.getMenuItem(0));
		al1.add(menuItemDao.getMenuItem(1));
		al1.add(menuItemDao.getMenuItem(2));
		
		ArrayList<MenuItem> al2 = new ArrayList<MenuItem>();
		al2.add(menuItemDao.getMenuItem(0));
		al2.add(menuItemDao.getMenuItem(3));
		
		ArrayList<MenuItem> al3 = new ArrayList<MenuItem>();
		al3.add(menuItemDao.getMenuItem(1));
		al3.add(menuItemDao.getMenuItem(2));
		al3.add(menuItemDao.getMenuItem(4));
		
		userCarts.put((long) 0, new Cart(al1, 8.00));
		userCarts.put((long) 1, new Cart(al2, 9.50));
		userCarts.put((long) 2, new Cart(al3, 16.50));
		
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
			throw new CartEmptyException("This cart is empty!");
		}
		
		double total = 0;
		for(MenuItem m : cartItems) {
			total += m.getPrice();
		}
		
		this.userCarts.get(userId).setTotal(total);
			
		return this.userCarts.get(userId);
	}
	
	public void removeCartItem(long userId) {
		if(this.userCarts.containsKey(userId)) {
			List<MenuItem> cartItems = this.userCarts.get(userId).getMenuItemList();

			for(MenuItem m : cartItems) {
				if(userId == m.getId()) {
					cartItems.remove(m);
					break;
				}
			}
		}
	}
}
