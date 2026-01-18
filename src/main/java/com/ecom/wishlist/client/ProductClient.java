package com.ecom.wishlist.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="product-service", url="http://product-service:8082")
public interface ProductClient {

    @GetMapping("/api/products/exists/{productId}")
    boolean productExists(Long productId);
}
