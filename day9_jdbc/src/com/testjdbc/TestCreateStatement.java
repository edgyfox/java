package com.testjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author ArghaNandan
 * @date 23-Sep-2018
 */
public class TestCreateStatement {
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/training";
	private static final String USERNAME = "arghanandan";
	private static final String PASSWORD = "Argha@16oc";
	
	static
	{
		try
		{
			Class.forName(DRIVER);
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
			System.out.println("Error loading Driver class!");
		}
	}
	
	public static void main(String[] args) {
		
		createTable();
		
	}
	
	public static void createTable()
	{
		
		Connection con = null;
		Statement state = null;
		String query = "create table jdbc(id int, name varchar(20));";
		try
		{
			con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			state = con.createStatement();
			
			try
			{
				state.execute(query);
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			finally
			{
				if(state!=null)
				{
					state.close();
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
}
