package com.java.my_hotel.dao;

import java.sql.*;
import java.util.ArrayList;

import com.java.my_hotel.model.Cart;
import com.java.my_hotel.model.MenuItem;

public class CartDaoSqlImpl implements CartDao {
	
	public void addCartItem(long userId, long menuItemId) {
		String query = "INSERT INTO cart_items(cartId, itemId) VALUES (?, ?)";
		PreparedStatement ps = null;
		Connection conn = null;
		
		try {
			conn = ConnectionHandler.getConnection();
			
			ps = conn.prepareStatement(query);
			
			ps.setLong(1, userId);
			ps.setLong(2, menuItemId);
			ps.execute();
			
		} catch (SQLException e) {
		    throw new IllegalStateException("Cannot connect the database!", e);
		} finally {
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("Connection closed.");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
	}
	
	public Cart getAllCartItems(long userId) throws CartEmptyException {
		String query = "SELECT cart_items.cartId, menu_item.name, menu_item.price, menu_item.active," + 
				"menu_item.dateOfLaunch, menu_item.category, menu_item.freeDelivery " + 
				"FROM cart_items " + 
				"INNER JOIN menu_item ON cart_items.itemId = menu_item.id " + 
				"INNER JOIN cart ON cart_items.cartId = cart.id " + 
				"WHERE cart_items.cartId = ?";
		PreparedStatement ps = null;
		Connection conn = null;
		
		ArrayList<MenuItem> cartItems = new ArrayList<MenuItem>();
		Double total = 0.0;
		Cart c = new Cart(cartItems, total);
		
		try {
			conn = ConnectionHandler.getConnection();
			
			ps = conn.prepareStatement(query);
			
			ps.setLong(1, userId);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				MenuItem m = new MenuItem(rs.getLong(1), rs.getString(2), 
						rs.getFloat(3), rs.getBoolean(4), rs.getDate(5),
						rs.getString(6), rs.getBoolean(7));
				cartItems.add(m);
			}
			
			c.setMenuItemList(cartItems);
			
			for(MenuItem m : cartItems) {
				total += m.getPrice();
				c.setTotal(total);
			}			
			
		} catch (SQLException e) {
		    throw new IllegalStateException("Cannot connect the database!", e);
		} finally {
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("Connection closed.");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
		
		return c;
	}
	
	public void removeCartItem(long userId) {
		String query = "DELETE FROM cart WHERE (id=?)";
		PreparedStatement ps = null;
		Connection conn = null;
		
		try {
			conn = ConnectionHandler.getConnection();
			
			ps = conn.prepareStatement(query);
			
			ps.setLong(1, userId);
			ps.execute();
			
		} catch (SQLException e) {
		    throw new IllegalStateException("Cannot connect the database!", e);
		} finally {
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("Connection closed.");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
	}
}
