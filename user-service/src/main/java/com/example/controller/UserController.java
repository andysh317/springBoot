package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.feignclients.OrderClient;

@RestController
@RequestMapping("/users")
public class UserController {
@Autowired
private OrderClient orderClient;

@GetMapping("/{userId}/orders")
public List<String> getUserOrders(@PathVariable String userId) {
    return orderClient.getOrdersByUser(userId);
}

}
