package com.ecom.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ecom.order.request.PaymentRequest;
import com.ecom.order.response.PaymentResponse;
import com.ecom.order.tracing.TracingFeignConfig;

@FeignClient(name = "payment-service", configuration = TracingFeignConfig.class)
public interface PaymentClient {

	@PostMapping("/payments")
	PaymentResponse makePayment(@RequestBody PaymentRequest paymentRequest);
}
