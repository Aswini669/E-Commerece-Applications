package com.ecom.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.product.entity.CategoriesEntity;
import com.ecom.product.entity.ProductEntity;
import com.ecom.product.repository.CategoriesRepository;
import com.ecom.product.repository.ProductRepository;
import com.ecom.product.request.ProductRequest;
import com.ecom.product.response.ProductDetails;

import jakarta.transaction.Transactional;

@Service
public class ProductServiceImplementation implements ProductService{

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CategoriesRepository categoriesRepository;
	
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
		
		return productEntity.getProductId();
	}

	@Override
	public ProductDetails getProducts(long productId) {
		ProductEntity productEntity = productRepository.findById(productId).get();
		CategoriesEntity categoriesEntity = categoriesRepository.findByProductId(productEntity.getProductId());
		
		ProductDetails productDetails = new ProductDetails();
		productDetails.setProdName(productEntity.getProdName());
		productDetails.setPrice(productEntity.getPrice());
		productDetails.setStickQty(productEntity.getPrice());
		productDetails.setDescription(productEntity.getDescription());
		productDetails.setCategory(categoriesEntity.getCategory());
		return productDetails;
	}

	
}
