package com.org.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        // Dummy authentication - replace with DB check in real app
        if ("admin".equals(request.getUsername()) && "123".equals(request.getPassword())) {
            String token = JwtUtil.generateToken(request.getUsername());

            return ResponseEntity.ok(Collections.singletonMap("token", token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid username or password");
        }
    }
    
    @GetMapping("/welcome")
    public String getWelocme() {
    	return "Hello";
    }
   
    
}

