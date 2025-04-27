package com.example.backend.back_end_demo.services;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        log.info("hello world started");
        return "Hello World";
    }

    @GetMapping("/")
    public String serviceStatus() {
        return "java service is up";
    }
}
