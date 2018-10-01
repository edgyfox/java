package com.casestudy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/jdbc_db";
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
		}
	}
	
	public static Connection getConnection()
	{
		Connection con = null;
		try
		{
			con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			System.out.println("Connection established!");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return con;
	}
	
	public static void closeSQL(Connection con)
	{
		try
		{
			if(con!=null)
			{
				con.close();
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void closeSQL(Connection con, Statement st)
	{
		try
		{
			if(con!=null)
			{
				st.close();
				con.close();
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void closeSQL(Connection con, PreparedStatement pst)
	{
		try
		{
			if(con!=null)
			{
				pst.close();
				con.close();
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
}
