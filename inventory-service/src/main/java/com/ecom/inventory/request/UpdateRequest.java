package com.ecom.inventory.request;

public class UpdateRequest {

	private long productId;
	private int newStockQty;

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public int getNewStockQty() {
		return newStockQty;
	}

	public void setNewStockQty(int newStockQty) {
		this.newStockQty = newStockQty;
	}

}
