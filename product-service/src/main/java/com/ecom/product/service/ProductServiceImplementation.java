package com.ecom.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.product.client.InventoryClient;
import com.ecom.product.entity.CategoriesEntity;
import com.ecom.product.entity.ProductEntity;
import com.ecom.product.exception.InvalidProductIdException;
import com.ecom.product.exception.InventoryStockFailedException;
import com.ecom.product.repository.CategoriesRepository;
import com.ecom.product.repository.ProductRepository;
import com.ecom.product.request.ProductRequest;
import com.ecom.product.request.StockRequest;
import com.ecom.product.response.InventoryResponse;
import com.ecom.product.response.ProductDetails;

import jakarta.transaction.Transactional;

@Service
public class ProductServiceImplementation implements ProductService {

	@Autowired
	ProductRepository productRepository;
	@Autowired
	CategoriesRepository categoriesRepository;
	@Autowired
	InventoryClient inventoryClient;

	@Override
	@Transactional
	public long createProduct(ProductRequest productRequest) {
		ProductEntity productEntity = new ProductEntity();
		productEntity.setProdName(productRequest.getProdName());
		productEntity.setPrice(productRequest.getPrice());
		productEntity.setDescription(productRequest.getDescription());
		productEntity = productRepository.save(productEntity);

		CategoriesEntity categoriesEntity = new CategoriesEntity();
		categoriesEntity.setProductId(productEntity.getProductId());
		categoriesEntity.setCategory(productRequest.getCategory());
		categoriesRepository.save(categoriesEntity);

		StockRequest stockRequest = new StockRequest();
		stockRequest.setProductId(productEntity.getProductId());
		stockRequest.setProductName(productEntity.getProdName());
		stockRequest.setStockQty(productRequest.getStickQty());
		String result = inventoryClient.createStock(stockRequest);
		if (result == null) {
			throw new InventoryStockFailedException("Failed to create stock");
		}
		return productEntity.getProductId();
	}

	@Override
	public ProductDetails getProducts(long productId) {
		ProductEntity productEntity = productRepository.findById(productId)
				.orElseThrow(() -> new InvalidProductIdException("No record found ! Invalid product id: " + productId));
		System.out.println("prodyct name: dsjbkhf=================================================" +productEntity.getProdName());
		CategoriesEntity categoriesEntity = categoriesRepository.findByProductId(productEntity.getProductId());

		InventoryResponse inventory = inventoryClient.getStock(productId);

		ProductDetails productDetails = new ProductDetails();
		productDetails.setProductId(productId);
		productDetails.setProductName(productEntity.getProdName());
		productDetails.setPrice(productEntity.getPrice());
		productDetails.setStockQty(inventory.getStockQty());
		productDetails.setDescription(productEntity.getDescription());
		productDetails.setCategory(categoriesEntity.getCategory());
		return productDetails;
	}

}
