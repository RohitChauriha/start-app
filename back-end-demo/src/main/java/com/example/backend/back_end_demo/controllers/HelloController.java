package com.example.backend.back_end_demo.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Slf4j
public class HelloController {
    @GetMapping
    public String helloCheck() {
        log.info("hello check performed!");
        String containerId = System.getProperty("HOSTNAME");
        String containerAddress = System.getProperty("IPAddr");
        return String.format("Hello from machine: %s \nwith internal IP: %s", containerId, containerAddress);
    }
}
