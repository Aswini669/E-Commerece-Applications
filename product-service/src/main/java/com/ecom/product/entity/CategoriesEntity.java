package com.ecom.product.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CategoriesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long categoruId;
	private long productId;
	private String category;

	public long getCategoruId() {
		return categoruId;
	}

	public void setCategoruId(long categoruId) {
		this.categoruId = categoruId;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
