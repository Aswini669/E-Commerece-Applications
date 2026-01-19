package com.ecom.product.service;

import org.springframework.stereotype.Service;

import com.ecom.product.request.ProductRequest;

@Service
public interface ProductService {

	long createProduct(ProductRequest productRequest);
}
