package com.java.my_hotel.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.java.my_hotel.model.MenuItem;

public class MenuItemDaoSqlImpl implements MenuItemDao {
	
	public ArrayList<MenuItem> getMenuItemListAdmin() {
		String query = "SELECT * FROM menu_item WHERE (dateOfLaunch < now() AND active = true)";
		PreparedStatement ps = null;
		Connection conn = null;
		
		ArrayList<MenuItem> menuItemList = new ArrayList<MenuItem>();
		MenuItem m = null;
		
		try {
			conn = ConnectionHandler.getConnection();
			
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				m = new MenuItem(rs.getLong(1), rs.getString(2), 
						rs.getFloat(3), rs.getBoolean(4), rs.getDate(5),
						rs.getString(6), rs.getBoolean(7));
				
				menuItemList.add(m);
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
		
		return menuItemList;
	}
	
	public ArrayList<MenuItem> getMenuItemListCustomer() {
		String query = "SELECT * FROM menu_item";
		PreparedStatement ps = null;
		Connection conn = null;
		
		ArrayList<MenuItem> menuItemList = new ArrayList<MenuItem>();
		MenuItem m = null;
		
		try {
			conn = ConnectionHandler.getConnection();
			
			System.out.println("Database connected!");
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				m = new MenuItem(rs.getLong(1), rs.getString(2), 
						rs.getFloat(3), rs.getBoolean(4), rs.getDate(5),
						rs.getString(6), rs.getBoolean(7));
				
				menuItemList.add(m);
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
		
		return menuItemList;
	}
	
	public void modifyMenuItem(MenuItem menuItem) {
		String query = "UPDATE menu_item SET (name=?, price=?, active=?, dateOfLaunch=?, "
				+ "category=?, freeDelivery=?) WHERE id = ?";
		PreparedStatement ps = null;
		Connection conn = null;
		
		try {
			conn = ConnectionHandler.getConnection();
			
			ps = conn.prepareStatement(query);
			
			ps.setString(1, menuItem.getName());
			ps.setFloat(2, menuItem.getPrice());
			ps.setBoolean(3, menuItem.isActive());
			ps.setDate(4, new Date(menuItem.getDateOfLaunch().getTime()));
			ps.setString(5, menuItem.getCategory());
			ps.setBoolean(6, menuItem.isFreeDelivery());
			ps.setLong(7, menuItem.getId());
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
	
	public MenuItem getMenuItem(long menuItemId) {
		String query = "SELECT * FROM menu_item WHERE id = ?";
		PreparedStatement ps = null;
		Connection conn = null;
		
		MenuItem m = null;
		
		try {
			conn = ConnectionHandler.getConnection();
			
			System.out.println("Database connected!");
			ps = conn.prepareStatement(query);
			
			ps.setLong(1, menuItemId);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				m = new MenuItem(rs.getLong(1), rs.getString(2), 
						rs.getFloat(3), rs.getBoolean(4), rs.getDate(5),
						rs.getString(6), rs.getBoolean(7));
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
		
		return m;
	}
}
