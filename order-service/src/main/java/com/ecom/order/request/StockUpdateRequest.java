package com.ecom.order.request;

public class StockUpdateRequest {

	private long productId;
	private int reduceStockQty;

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public int getReduceStockQty() {
		return reduceStockQty;
	}

	public void setReduceStockQty(int reduceStockQty) {
		this.reduceStockQty = reduceStockQty;
	}

}
