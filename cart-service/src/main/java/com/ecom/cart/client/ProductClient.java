package com.ecom.cart.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.ecom.cart.request.CartRequest;
import com.ecom.cart.response.ProductResponse;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@FeignClient(name = "product-service")
public interface ProductClient {

	@GetMapping("/products/{productId}")
	ProductResponse fetchProduct(@PathVariable long productId);
}
