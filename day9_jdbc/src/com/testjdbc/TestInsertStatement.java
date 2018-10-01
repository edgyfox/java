package com.testjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * @author ArghaNandan
 * @date 23-Sep-2018
 */

class jdbcTable
{
	private int id;
	private String name;
	public jdbcTable(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
}

public class TestInsertStatement {

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
	
	static void insertIntoTable(int id, String name)
	{
		Connection con = null;
		Statement st = null;
		String query = "insert into jdbc values(" + id + ",'" + name + "');";
		
		try
		{
			con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			st = con.createStatement();
			
			try
			{
				st.execute(query);
				System.out.println("Insert statement : " + query + " : executed!");
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
		int id,i=1;
		String name;
		String ch="y";
		Scanner input = new Scanner(System.in);
		do 
		{
			System.out.print("\nEnter id " + i + ": ");
			id = input.nextInt();
			System.out.print("Enter name " + (i++) + ": ");
			name = input.next();
			
			insertIntoTable(id,name);
			
			System.out.print("Continue?(y/n): ");
			ch = input.next();
			
		}while(ch.equals("y"));
	}
}
