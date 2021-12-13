package com.java.my_hotel.main;

import java.text.ParseException;

import com.java.my_hotel.dao.*;
import com.java.my_hotel.model.*;
import com.java.my_hotel.util.DateUtil;

public class Main {

	public static void main(String[] args) {
		// Collections Implementation
		System.out.println("Collections Implementation");
		
		CartDao cartDaoC = new CartDaoCollectionImpl();
		MenuItemDao menuItemDaoC = new MenuItemDaoCollectionImpl();
		
		System.out.println("menuItemListAdmin: " + menuItemDaoC.getMenuItemListAdmin());
		System.out.println("menuItemListCustomer: " + menuItemDaoC.getMenuItemListCustomer());
		System.out.println("getMenuItem #6: " + menuItemDaoC.getMenuItem(6));
		
		for(int i = 0; i < 3; i++) {
			try {
				System.out.println("Cart ID #" + i + ": " + cartDaoC.getAllCartItems(i));
			} catch (CartEmptyException e) {
				e.printStackTrace();
			}
		}
		
		// SQL Implementation
		System.out.println("SQL Implementation");
		
		CartDao cartDaoS = new CartDaoSqlImpl();
		MenuItemDao menuItemDaoS = new MenuItemDaoSqlImpl();
		
		System.out.println("menuItemListAdmin: " + menuItemDaoS.getMenuItemListAdmin());
		System.out.println("menuItemListCustomer: " + menuItemDaoS.getMenuItemListCustomer());
		System.out.println("getMenuItem #6: " + menuItemDaoS.getMenuItem(6));
		
		try {
			menuItemDaoS.modifyMenuItem(new MenuItem(6, "Cookies", (float) 7.00, true, DateUtil.convertToDate("13/12/2021"), 
					"Food", false));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		System.out.println("getMenuItem #6: " + menuItemDaoS.getMenuItem(6));
		
		
		for(int i = 0; i < 3; i++) {
			try {
				System.out.println("Cart ID #" + i + ": " + cartDaoS.getAllCartItems(i));
			} catch (CartEmptyException e) {
				e.printStackTrace();
			}
		}
		
		cartDaoS.removeCartItem(0);
		try {
			System.out.println("Cart ID #0: " + cartDaoS.getAllCartItems(0));
		} catch (CartEmptyException e) {
			e.printStackTrace();
		}

	}

}
