package com.casestudy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.casestudy.DBUtils;
import com.casestudy.bean.ProductBean;

public class ProductDAO {
	
	static Scanner input = new Scanner(System.in);
	public static void showProducts()
	{
		Connection con = DBUtils.getConnection();
		final String query = "select * from products;";
		Statement st = null;
		ResultSet rs = null;
		ProductBean pb = null;
		try
		{
			st = con.createStatement();
			rs = st.executeQuery(query);
			if(!rs.next())
			{
				System.err.println("\nNo products available.");
			}
			else
			{
				do
				{
					System.out.println(rs.getInt(1)+ " : " 
							+ rs.getString(2) + " : "
							+ rs.getString(3) + " : " 
							+ rs.getDouble(4) + " : "
							+ rs.getString(5));					
				}while(rs.next());
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			DBUtils.closeSQL(con, st);
		}
	}
	
	public static void showProductsByCategory()
	{
		System.out.print("\nEnter category: ");
		String cat = input.next();
		
		if(!catExists(cat))
		{
			System.err.println(cat + " doesn't exist in database!");
		}
		else
		{
			Connection con = DBUtils.getConnection();
			PreparedStatement pst = null;
			ResultSet rs = null;
			String query = "select * from products where prodcat=?;";
			try
			{
				pst = con.prepareStatement(query);
				pst.setString(1, cat);
				rs = pst.executeQuery();
				while(rs.next())
				{
					System.out.println("Product [ " + rs.getInt(1) + " : " + rs.getString(2)
					+ " : " + rs.getString(3) + " : " + rs.getDouble(4) + " ]");
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			finally
			{
				DBUtils.closeSQL(con, pst);
			}
		}
	}
	
	public static void addProduct()
	{
		System.out.print("Enter product ID: ");
		int id = input.nextInt();
		System.out.print("Enter product brand: ");
		String brand = input.next();
		System.out.print("Enter product name:");
		String name = input.next();
		System.out.print("Enter product price: ");
		double price = input.nextDouble();
		System.out.print("Enter product category: ");
		String cat = input.next();
		ProductBean pb = new ProductBean(id, brand, name, price, cat);
		if(prodExists(pb))
		{
			System.err.println("\nProduct already exists in database!");
		}
		else
		{
			Connection con = DBUtils.getConnection();
			PreparedStatement pst = null;
			String query = "insert into products values(?,?,?,?,?);";
			try
			{
				pst = con.prepareStatement(query);
				pst.setInt(1, id);
				pst.setString(2, brand);
				pst.setString(3, name);
				pst.setDouble(4, price);
				pst.setString(5, cat);
				pst.execute();
				System.out.println(pb.getProdId() + " added to database.");
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			finally
			{
				DBUtils.closeSQL(con, pst);
			}
		}
	}
	
	public static void deleteProduct()
	{
		System.out.print("\nEnter product ID to deletion: ");
		int id = input.nextInt();
		ProductBean pb = new ProductBean();
		pb.setProdId(id);
		if(!prodExists(pb))
		{
			System.err.println("Product doesn't exist in database!");
		}
		else
		{
			Connection con = DBUtils.getConnection();
			String query = "delete from products where prodid=?;";
			PreparedStatement pst = null;
			try
			{
				pst = con.prepareStatement(query);
				pst.setInt(1, pb.getProdId());
				pst.execute();
				System.out.println(pb.getProdId() + " deleted from database.");
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			finally
			{
				DBUtils.closeSQL(con, pst);
			}
		}
	}
	
	public static void updateProduct()
	{
		System.out.print("Enter product ID for updation: ");
		int id = input.nextInt();
		ProductBean pb = new ProductBean();
		pb.setProdId(id);
		if(!prodExists(pb))
		{
			System.err.println(id + " doesn't exist in database!");
		}
		else
		{
			Connection con = DBUtils.getConnection();
			String querybrand = "update products set prodbrand=? where prodid=?;";
			String queryname = "update products set prodname=? where prodid=?;";
			String queryprice = "update products set prodprice=? where prodid=?";
			String querycat = "update products set prodcat=? where prodid=?;";
			PreparedStatement pst = null;
			try
			{
				pst = con.prepareStatement(querybrand);
				System.out.print("Enter new brand for " + id + ": ");
				String brand = input.next();
				pst.setString(1, brand);
				pst.setInt(2, id);
				pst.execute();
				pst = con.prepareStatement(queryname);
				System.out.print("Enter new name for " + id + ": ");
				String name = input.next();
				pst.setString(1, name);
				pst.setInt(2, id);
				pst.execute();
				pst = con.prepareStatement(queryprice);
				System.out.print("Enter new price for " +  id + ": ");
				double price = input.nextDouble();
				pst.setDouble(1, price);
				pst.setInt(2, id);
				pst.execute();
				pst = con.prepareStatement(querycat);
				System.out.print("Enter new category for " + id + ": ");
				String cat = input.next();
				pst.setString(1, cat);
				pst.setInt(2, id);
				pst.execute();
				System.out.println("\n" + id + " updated in database!");
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			finally
			{
				DBUtils.closeSQL(con, pst);
			}
		}
	}
	
	public static void sortProducts()
	{
		
	}
	
	public static boolean prodExists(ProductBean pb)
	{
		boolean exists = false;
		Connection con = DBUtils.getConnection();
		String query = "select prodid from products where prodid=?;";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try
		{
			pst = con.prepareStatement(query);
			pst.setInt(1, pb.getProdId());
			rs = pst.executeQuery();
			if(rs.next())
			{
				exists = true;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			DBUtils.closeSQL(con, pst);
		}
		return exists;
	}
	
	public static boolean catExists(String cat)
	{
		boolean exists = false;
		Connection con = DBUtils.getConnection();
		String query = "select * from products where prodcat=?;";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try
		{
			pst = con.prepareStatement(query);
			pst.setString(1, cat);
			rs = pst.executeQuery();
			if(rs.next())
			{
				exists = true;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			DBUtils.closeSQL(con, pst);
		}
		return exists;
	}
	
}
