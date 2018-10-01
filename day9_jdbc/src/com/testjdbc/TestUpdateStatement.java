package com.testjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author ArghaNandan
 * @date 23-Sep-2018
 */
public class TestUpdateStatement {
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
		PreparedStatement pst = null;
		String query = "update jdbc set name=? where id=?";
		
		try
		{
			con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			pst = con.prepareStatement(query);
			
			try
			{
				pst.setString(1, "John");
				pst.setInt(2, 1);
				pst.executeUpdate();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			finally
			{
				if(pst!=null)
				{
					pst.close();
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
