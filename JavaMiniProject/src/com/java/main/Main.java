package com.java.main;

import java.sql.*;

public class Main {

	public static void main(String[] args) {
		Connection connection = null;
		String url = "jdbc:mysql://localhost:3308/myhotel";
		String username = "root";
		String password = "root";

		System.out.println("Connecting database...");

		try {
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("Database connected!");
		} catch (SQLException e) {
		    throw new IllegalStateException("Cannot connect the database!", e);
		} finally {
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("Connection closed.");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
	}

}
