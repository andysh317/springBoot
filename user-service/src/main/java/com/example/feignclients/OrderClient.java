package com.example.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//caling order service from user service
@FeignClient(name = "order-service")
public interface OrderClient {
    @GetMapping("/orders/user/{userId}")
    List<String> getOrdersByUser(@PathVariable String userId);
}
