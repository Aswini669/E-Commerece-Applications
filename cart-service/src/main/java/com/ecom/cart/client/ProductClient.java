package com.ecom.cart.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecom.cart.response.ProductResponse;

@FeignClient(name = "product-service")
public interface ProductClient {

	@GetMapping("/products/{productId}")
	ProductResponse fetchProduct(@PathVariable long productId);
}
