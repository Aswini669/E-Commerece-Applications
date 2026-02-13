package com.ecom.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.product.request.ProductRequest;
import com.ecom.product.response.ProductDetails;
import com.ecom.product.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	ProductService productService;
	
	@PostMapping("/products")
	public String createProduct(@RequestBody ProductRequest productRequest) {
		long prodId = productService.createProduct(productRequest);
		return "your product id: " + prodId;
	}
	
	@GetMapping("/products/{productId}")
<<<<<<< HEAD
	public ResponseEntity<ProductDetails> fetchProducts(@PathVariable long productId) {
		System.out.println("Inside fetch product service................................................");
=======
	public ResponseEntity<ProductDetails> getProducts(@PathVariable long productId) {
		System.out.println("ksndkfnknlkf");
>>>>>>> 7b34a51bc93aeda4d71178caf8d35acdf12d1e1a
		ProductDetails productDetails = productService.getProducts(productId);
		return ResponseEntity.ok(productDetails);
	}
}
