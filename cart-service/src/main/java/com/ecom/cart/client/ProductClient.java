package com.ecom.cart.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecom.cart.response.ProductResponse;
import com.ecom.cart.tracing.TracingFeignConfig;

@FeignClient(name = "product-service", configuration = TracingFeignConfig.class)
public interface ProductClient {

	@GetMapping("/products/{productId}")
	ProductResponse fetchProduct(@PathVariable long productId);
}
