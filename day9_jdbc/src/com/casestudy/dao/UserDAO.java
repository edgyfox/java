package com.casestudy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.casestudy.DBUtils;
import com.casestudy.bean.UserBean;

public class UserDAO {

	public static boolean insertUser(UserBean ub)
	{
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		boolean userExists = false;
		String query1 = "select userid from users where userid=?;";
		String query2 = "insert into users values(?,?,?,?);";
		try {
			pst= con.prepareStatement(query1);
			pst.setInt(1, ub.getUserId());
			ResultSet rs = pst.executeQuery();
			if(rs.next())
			{
				System.err.println("User already exists! Try a different username/password combo.");
			}
			else
			{
				pst = con.prepareStatement(query2);
				pst.setInt(1, ub.getUserId());
				pst.setString(2, ub.getUserName());
				pst.setString(3, ub.getUserPass());
				pst.setString(4, ub.getUserCat());
				pst.execute();
				System.out.println("User added!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			DBUtils.closeSQL(con, pst);
		}
		return userExists;
	}
	
	public static String authenticateUser(String usr, String pwd)
	{
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String userCat = "null";
		String query = "select usercat from users where username=? and userpass=?;";
		try
		{
			pst = con.prepareStatement(query);
			pst.setString(1, usr);
			pst.setString(2, pwd);
			rs = pst.executeQuery();
			if(rs.next())
			{
				userCat = rs.getString(1);
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
		return userCat;
	}
	
}
