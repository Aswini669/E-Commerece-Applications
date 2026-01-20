package com.ecom.inventory.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecom.inventory.response.ProductResponse;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@FeignClient(name = "product-service")
public interface ProductClient {

	@CircuitBreaker(name = "productServiceCB", fallbackMethod = "fallbackProduct")
	@Retry(name = "productServiceRetry")
	
	@GetMapping("/products/{productId}")
	ProductResponse fetchProduct(@PathVariable long productId);
	
	default ProductResponse fallbackProduct(long productId, Throwable ex) {
		System.out.println("Inside Product client fallback method...");
		System.out.println(ex.getMessage());
		return null;
	}
}
