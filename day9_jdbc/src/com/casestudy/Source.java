package com.casestudy;

import java.util.Scanner;

import com.casestudy.bean.UserBean;
import com.casestudy.dao.ProductDAO;
import com.casestudy.dao.UserDAO;

public class Source {
	
	static Scanner input = new Scanner(System.in);
	public static void menuForAdmin()
	{
		int ch;
		do
		{
			System.out.println("\nUSER TYPE: ADMIN");
			System.out.println("1. Add products");
			System.out.println("2. View products");
			System.out.println("3. View products by category");
			System.out.println("4. Delete products");
			System.out.println("5. Update products");
			System.out.println("6. Add user");
			System.out.println("7. Log out");
			System.out.print("\nEnter option: ");
			ch = input.nextInt();
			switch(ch)
			{
			case 1:	ProductDAO.addProduct();
					break;
			case 2:	ProductDAO.showProducts();
					break;
			case 3: ProductDAO.showProductsByCategory();
					break;
			case 4:	ProductDAO.deleteProduct();
					break;
			case 5: ProductDAO.updateProduct();
					break;
			case 6:	userRegistration();
					break;
			case 7:	System.out.println("Logging out...\n");
					break;
			default:System.out.println("Invalid option! Try again.\n");
			}
		}while(ch!=7);
	}
	
	public static void menuForUser()
	{
		int ch;
		do
		{
			System.out.println("\nUSER TYPE: USER");
			System.out.println("1. View products");
			System.out.println("2. View products by category");
			System.out.println("3. Sort products");
			System.out.println("4. Log out");
			System.out.print("\nEnter option: ");
			ch = input.nextInt();
			switch(ch)
			{
			case 1:	ProductDAO.showProducts();
					break;
			case 2:	ProductDAO.showProductsByCategory();
					break;
			case 3:	ProductDAO.sortProducts();
					break;
			case 4:	System.out.println("Logging out...\n");
					break;
			default:System.out.println("Invalid option! Try again.\n");
			}
		}while(ch!=4);
	}
	
	public static void userRegistration()
	{
		System.out.print("\nEnter new user ID: ");
		int id = input.nextInt();
		System.out.print("Enter new username: ");
		String usr = input.next();
		System.out.print("Enter password: ");
		String pwd = input.next();
		System.out.print("Enter user category(admin/user): ");
		String cat = input.next();
		UserBean ub = new UserBean(id, usr, pwd, cat);
		UserDAO.insertUser(ub);
	}
	
	public static void main(String[] args) {
		
		do
		{
			System.out.println("\n----Login----\n");
			System.out.print("Username: ");
			String usr = input.next();
			System.out.print("Password: ");
			String pwd = input.next();
			String userCat = UserDAO.authenticateUser(usr, pwd);
			if(userCat.equalsIgnoreCase("admin"))
			{
				menuForAdmin();
			}
			else if(userCat.equalsIgnoreCase("user"))
			{
				menuForUser();
			}
			else
			{
				System.out.println("No user found!");
			}
		}while(true);
	}
}
