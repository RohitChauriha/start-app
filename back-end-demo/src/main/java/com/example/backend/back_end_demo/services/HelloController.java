package com.example.backend.back_end_demo.services;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello World";
    }
    @GetMapping("/")
    public String serviceStatus() {
        return "java service is up";
    }
}