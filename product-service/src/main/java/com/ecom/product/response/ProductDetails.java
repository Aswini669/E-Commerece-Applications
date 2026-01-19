package com.ecom.product.response;

public class ProductDetails {

	private String prodName;
	private int stickQty;
	private int price;
	private String description;
	private String category;

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public int getStickQty() {
		return stickQty;
	}

	public void setStickQty(int stickQty) {
		this.stickQty = stickQty;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
