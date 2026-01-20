package com.ecom.inventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.inventory.client.ProductClient;
import com.ecom.inventory.entity.InventoryEntity;
import com.ecom.inventory.repository.InventoryRepository;
import com.ecom.inventory.request.InventoryRequest;
import com.ecom.inventory.response.InventoryResponse;
import com.ecom.inventory.response.ProductResponse;

@Service
public class InventoryServiceImplementation implements InventoryService{

	@Autowired
	InventoryRepository inventoryRepository;
	@Autowired
	ProductClient productClient;
	
	@Override
	public long createStock(InventoryRequest inventoryRequest) {
		InventoryEntity inventoryEntity = new InventoryEntity();
		inventoryEntity.setProductId(inventoryRequest.getProductId());
		inventoryEntity.setProductName(inventoryRequest.getProductName());
		inventoryEntity.setStockQty(inventoryRequest.getStockQty());
		inventoryEntity = inventoryRepository.save(inventoryEntity);
		return inventoryEntity.getInventoryId();
	}

	@Override
	public InventoryResponse getStock(long productId) {
		InventoryEntity inventoryEntity = inventoryRepository.findByProductId(productId);
		InventoryResponse response = new InventoryResponse();
		response.setInventoryId(inventoryEntity.getInventoryId());
		response.setStockQty(inventoryEntity.getStockQty());
		
		return response;
	}

	@Override
	public ProductResponse fetchProduct(long productId) {
		ProductResponse productResponse = productClient.fetchProduct(productId);
		return productResponse;
	}

}
