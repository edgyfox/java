package com.casestudy.bean;

public class UserBean {
	private int userId;
	private String userName;
	private String userPass;
	private String userCat;
	public UserBean(int userId, String userName, String userPass, String userCat) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPass = userPass;
		this.userCat = userCat;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public String getUserCat() {
		return userCat;
	}
	public void setUserCat(String userCat) {
		this.userCat = userCat;
	}
}
