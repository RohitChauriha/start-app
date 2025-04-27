package com.example.backend.back_end_demo.services;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class HelloController {
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @GetMapping("/hello")
    public String sayHello() {
        LOGGER.log(Level.INFO, "hello world started");
        return "Hello World";
    }

    @GetMapping("/")
    public String serviceStatus() {
        return "java service is up";
    }
}