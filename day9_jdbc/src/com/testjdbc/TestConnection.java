package com.testjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author ArghaNandan
 * @date 23-Sep-2018
 */
public class TestConnection {
	public static void main(String[] args) {
		
		Connection conn = null;
		
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch(ClassNotFoundException cnfe)
		{
			cnfe.printStackTrace();
		}
		
		try
		{
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/training", "arghanandan", "Argha@16oc");
			if(conn!=null)
			{
				System.out.println("Connection established!");
			}
			else
			{
				System.out.println("Some problem while establishing connection.");
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(conn!=null)
				{
					conn.close();
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}
