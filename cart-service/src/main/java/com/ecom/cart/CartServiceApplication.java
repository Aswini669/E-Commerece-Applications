package com.ecom.cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
<<<<<<< HEAD
import org.springframework.cache.annotation.EnableCaching;
=======
>>>>>>> 7b34a51bc93aeda4d71178caf8d35acdf12d1e1a
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
<<<<<<< HEAD
@EnableCaching
=======
>>>>>>> 7b34a51bc93aeda4d71178caf8d35acdf12d1e1a
public class CartServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartServiceApplication.class, args);
	}

}
