package com.java.my_hotel.dao;
import java.util.ArrayList;
import com.java.my_hotel.model.MenuItem;

public interface MenuItemDao {
	public ArrayList<MenuItem> getMenuItemListAdmin();
	public ArrayList<MenuItem> getMenuItemListCustomer();
	public void modifyMenuItem(MenuItem menuItem);
	public MenuItem getMenuItem(long menuItemId);
}
