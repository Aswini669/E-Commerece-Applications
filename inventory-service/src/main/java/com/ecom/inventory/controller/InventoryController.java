package com.ecom.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.inventory.request.InventoryRequest;
import com.ecom.inventory.request.UpdateRequest;
import com.ecom.inventory.response.InventoryResponse;
import com.ecom.inventory.response.ProductResponse;
import com.ecom.inventory.service.InventoryService;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

	@Autowired
	InventoryService inventoryService;
	
	@PostMapping
	public String createStock(@RequestBody InventoryRequest inventoryRequest) {
		long invenId = inventoryService.createStock(inventoryRequest);
		return "ypur inventory id is: " + invenId;
	}
	
	@GetMapping("/getInventory/{productId}")
	public ResponseEntity<InventoryResponse> getStock(@PathVariable long productId){
		InventoryResponse response = inventoryService.getStock(productId);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{productId}")
	public ResponseEntity<ProductResponse> fetchProduct(@PathVariable long productId){
		return ResponseEntity.ok(null);
	}
	
	@PutMapping("/reduce")
	public String updateStock(@RequestBody UpdateRequest updateRequest) {
		int newStock = inventoryService.updateProductStock(updateRequest);
		return "product stock updated. New Stock is: " + newStock;
	}
}
