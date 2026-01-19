package com.ecom.product.service;

import org.springframework.stereotype.Service;

import com.ecom.product.request.ProductRequest;
import com.ecom.product.response.ProductDetails;

@Service
public interface ProductService {

	long createProduct(ProductRequest productRequest);
	ProductDetails getProducts(long productId);
}
