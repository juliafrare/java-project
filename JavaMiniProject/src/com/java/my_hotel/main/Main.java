package com.java.my_hotel.main;

import java.text.ParseException;

import com.java.my_hotel.dao.*;
import com.java.my_hotel.model.*;
import com.java.my_hotel.util.DateUtil;

public class Main {

	public static void main(String[] args) {
		
		try {
			int implementation = Integer.valueOf(args[0]); // 0 for collections, 1 for sql
			
			CartDao cartDao;
			MenuItemDao menuItemDao;
			
			if(implementation == 0) {
				cartDao = new CartDaoCollectionImpl();
				menuItemDao = new MenuItemDaoCollectionImpl();
				test(cartDao, menuItemDao);
			}
			else if(implementation == 1) {
				cartDao = new CartDaoSqlImpl();
				menuItemDao = new MenuItemDaoSqlImpl();
				test(cartDao, menuItemDao);
			}
			else {
				System.out.println("Argument needs to be 0 or 1.");
			}
		}
		catch(IndexOutOfBoundsException e) {
			System.out.println("Argument needs to be 0 or 1");
			e.printStackTrace();
		}

	}

	public static void test(CartDao cartDao, MenuItemDao menuItemDao) {
		System.out.println("menuItemListAdmin: " + menuItemDao.getMenuItemListAdmin());
		System.out.println("menuItemListCustomer: " + menuItemDao.getMenuItemListCustomer());
		System.out.println("getMenuItem #6: " + menuItemDao.getMenuItem(6));
		
		try {
			menuItemDao.modifyMenuItem(new MenuItem(6, "Cookies", (float) 7.00, true, DateUtil.convertToDate("13/12/2021"), 
					"Food", false));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		System.out.println("getMenuItem #6: " + menuItemDao.getMenuItem(6));
		
		
		for(int i = 0; i < 3; i++) {
			try {
				System.out.println("Cart ID #" + i + ": " + cartDao.getAllCartItems(i));
			} catch (CartEmptyException e) {
				e.printStackTrace();
			}
		}
		
		cartDao.removeCartItem(0);
		try {
			System.out.println("Cart ID #0: " + cartDao.getAllCartItems(0));
		} catch (CartEmptyException e) {
			e.printStackTrace();
		}
	}
}
