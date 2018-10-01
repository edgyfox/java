package com.casestudy.bean;

public class ProductBean {
	private int prodId;
	private String prodBrand;
	private String prodName;
	private double prodPrice;
	private String prodCat;
	public ProductBean(){}
	public ProductBean(int prodId, String prodBrand, String prodName, double prodPrice, String prodCat) {
		super();
		this.prodId = prodId;
		this.prodBrand = prodBrand;
		this.prodName = prodName;
		this.prodPrice = prodPrice;
		this.prodCat = prodCat;
	}
	public int getProdId() {
		return prodId;
	}
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	public String getProdBrand() {
		return prodBrand;
	}
	public void setProdBrand(String prodBrand) {
		this.prodBrand = prodBrand;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public double getProdPrice() {
		return prodPrice;
	}
	public void setProdPrice(double prodPrice) {
		this.prodPrice = prodPrice;
	}
	public String getProdCat() {
		return prodCat;
	}
	public void setProdCat(String prodCat) {
		this.prodCat = prodCat;
	}
	@Override
	public String toString() {
		return "Product [id = " + this.prodId + ", name = " + this.prodName + 
				", brand = " + this.prodBrand + ", price = " + this.prodPrice +
				", category = " + this.prodCat;
	}
}
