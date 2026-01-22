package com.ecom.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ecom.order.request.StockUpdateRequest;
import com.ecom.order.tracing.TracingFeignConfig;

@FeignClient(name = "inventory-service", configuration = TracingFeignConfig.class)
public interface InventoryClient {

	@PutMapping("/inventory/reduce")
	public String updateStock(@RequestBody StockUpdateRequest request);
}
