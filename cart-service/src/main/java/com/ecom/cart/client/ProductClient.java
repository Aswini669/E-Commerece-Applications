package com.ecom.cart.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
<<<<<<< HEAD

import com.ecom.cart.response.ProductResponse;
import com.ecom.cart.tracing.TracingFeignConfig;

@FeignClient(name = "product-service", configuration = TracingFeignConfig.class)
=======
import org.springframework.web.bind.annotation.RequestBody;

import com.ecom.cart.request.CartRequest;
import com.ecom.cart.response.ProductResponse;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@FeignClient(name = "product-service")
>>>>>>> 7b34a51bc93aeda4d71178caf8d35acdf12d1e1a
public interface ProductClient {

	@GetMapping("/products/{productId}")
	ProductResponse fetchProduct(@PathVariable long productId);
}
