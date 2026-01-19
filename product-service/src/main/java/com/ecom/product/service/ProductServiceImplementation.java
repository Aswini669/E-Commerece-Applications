package com.ecom.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.product.entity.ProductEntity;
import com.ecom.product.repository.ProductRepository;
import com.ecom.product.request.ProductRequest;

@Service
public class ProductServiceImplementation implements ProductService{

	@Autowired
	ProductRepository productRepository;
	
	@Override
	public long createProduct(ProductRequest productRequest) {
		ProductEntity productEntity = new ProductEntity();
		productEntity.setProdName(productRequest.getProdName());
		productEntity.setStickQty(productRequest.getStickQty());
		productEntity.setPrice(productRequest.getPrice());
		productEntity.setCategory(productRequest.getCategory());
		productEntity.setDescription(productRequest.getDescription());
		productEntity = productRepository.save(productEntity);
		return productEntity.getProductId();
	}

	
}
