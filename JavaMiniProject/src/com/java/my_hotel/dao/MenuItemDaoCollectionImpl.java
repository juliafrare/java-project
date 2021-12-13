package com.java.my_hotel.dao;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import com.java.my_hotel.model.MenuItem;
import com.java.my_hotel.util.DateUtil;

public class MenuItemDaoCollectionImpl implements MenuItemDao {
	private ArrayList<MenuItem> menuItemList;
	
	public MenuItemDaoCollectionImpl() {		
		menuItemList = new ArrayList<MenuItem>();
		
		try {
			menuItemList.add(new MenuItem(0, "Water", (float) 1.50, true, DateUtil.convertToDate("01/01/2018"),
					"Drink", true));
			menuItemList.add(new MenuItem(1, "Soda", (float) 3.00, true, DateUtil.convertToDate("08/01/2018"), 
					"Drink", true));
			menuItemList.add(new MenuItem(2, "Chips", (float) 3.50, true, DateUtil.convertToDate("03/04/2019"), 
					"Food", true));
			menuItemList.add(new MenuItem(3, "Salad", (float) 8.00, true, DateUtil.convertToDate("08/04/2019"), 
					"Food", false));
			menuItemList.add(new MenuItem(4, "Pizza", (float) 10.00, true, DateUtil.convertToDate("19/08/2020"), 
					"Food", false));
			menuItemList.add(new MenuItem(5, "Juice", (float) 4.00, false, DateUtil.convertToDate("02/02/2021"), 
					"Drink", false));
			menuItemList.add(new MenuItem(6, "Cookies", (float) 7.00, false, DateUtil.convertToDate("31/12/2023"), 
					"Food", false));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<MenuItem> getMenuItemListAdmin() {		
		return this.menuItemList;
	}
	
	public ArrayList<MenuItem> getMenuItemListCustomer() {
		ArrayList<MenuItem> mil = new ArrayList<MenuItem>();
		Date d = new Date(System.currentTimeMillis());
		
		for(MenuItem m : this.menuItemList) {
			if(d.after(m.getDateOfLaunch())  && m.isActive()) {
				mil.add(m);
			}
		}
		
		return mil;
	}
	
	public void modifyMenuItem(MenuItem menuItem) {
		for(MenuItem m : this.menuItemList) {
			if(m.getId() == menuItem.getId()) {
				m.setName(menuItem.getName());
				m.setPrice(menuItem.getPrice());
				m.setActive(menuItem.isActive());
				m.setCategory(menuItem.getCategory());
				m.setDateOfLaunch(menuItem.getDateOfLaunch());
				m.setFreeDelivery(menuItem.isFreeDelivery());
				break;
			}
		}
	}
	
	public MenuItem getMenuItem(long menuItemId) {
		for(MenuItem m : this.menuItemList) {
			if(m.getId() == menuItemId) {
				return m;
			}
		}
		
		return null;
	}
}
