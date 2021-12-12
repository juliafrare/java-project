package com.java.my_hotel.dao;
import java.util.ArrayList;
import java.util.Date;
import com.java.my_hotel.model.MenuItem;

public class MenuItemDaoCollectionImpl implements MenuItemDao {
	private ArrayList<MenuItem> menuItemList;
	
	public MenuItemDaoCollectionImpl() {
		super();
		
		ArrayList<MenuItem> menuItemList = new ArrayList<MenuItem>();
		
		for(MenuItem m : menuItemList) {
			this.menuItemList.add(m);
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
