package com.example.backend.back_end_demo.services;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class HelloController {
    private final static Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/hello")
    public String sayHello() {
        LOGGER.info("hello world started");
        return "Hello World";
    }

    @GetMapping("/")
    public String serviceStatus() {
        return "java service is up";
    }
}
