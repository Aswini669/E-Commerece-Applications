package com.ecom.inventory.service;

import org.springframework.stereotype.Service;

import com.ecom.inventory.request.InventoryRequest;
import com.ecom.inventory.request.UpdateRequest;
import com.ecom.inventory.response.InventoryResponse;
import com.ecom.inventory.response.ProductResponse;

@Service
public interface InventoryService {

	long createStock(InventoryRequest inventoryRequest);
	InventoryResponse getStock(long productId);
	ProductResponse fetchProduct(long productId);
	int updateProductStock(UpdateRequest updateRequest);
}
