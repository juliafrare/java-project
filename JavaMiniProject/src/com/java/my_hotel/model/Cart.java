	package com.java.my_hotel.model;

import java.util.ArrayList;
import java.util.Objects;

public class Cart {
	private ArrayList<MenuItem> menuItemList;
	private double total;
	
	public Cart(ArrayList<MenuItem> menuItemList, double total) {
		super();
		this.menuItemList = menuItemList;
		this.total = total;
	}

	public ArrayList<MenuItem> getMenuItemList() {
		return menuItemList;
	}

	public void setMenuItemList(ArrayList<MenuItem> menuItemList) {
		this.menuItemList = menuItemList;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "Cart [menuItemList=" + menuItemList + ", total=" + total + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(menuItemList, total);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cart other = (Cart) obj;
		return Objects.equals(menuItemList, other.menuItemList)
				&& Double.doubleToLongBits(total) == Double.doubleToLongBits(other.total);
	}
	
}
