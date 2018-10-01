package com.testjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author ArghaNandan
 * @date 23-Sep-2018
 */
public class TestSelectStatement {
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/cgi_db";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "asdf";
	
	static
	{
		try
		{
			Class.forName(DRIVER);
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
			System.out.println("Driver not found!");
		}
	}
	
	static void selectFromTable()
	{
		Connection con = null;
		Statement st = null;
		ResultSet res = null;
		String query = "select * from jdbc;";
		
		try
		{
			con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			st = con.createStatement();
			
			try
			{
				res = st.executeQuery(query);
				System.out.println("Select statement : " + query + " : executed!");
				while(res.next())
				{
					System.out.println(res.getInt("id") + " : " + res.getString("name"));
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			finally
			{
				if(st!=null)
				{
					st.close();
				}
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(con!=null)
			{
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static void main(String[] args) {
		selectFromTable();
	}
}
