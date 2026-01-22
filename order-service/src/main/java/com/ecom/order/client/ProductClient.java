package com.ecom.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecom.order.response.ProductResponse;
import com.ecom.order.tracing.TracingFeignConfig;

@FeignClient(name = "product-service", configuration = TracingFeignConfig.class)
public interface ProductClient {

	@GetMapping("/products/{productId}")
	ProductResponse fetchProducts(@PathVariable long productId);
}
