package com.example.backend.back_end_demo.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
@Slf4j
public class HelloController {
    @GetMapping("/hello")
    public Map<String, String> sayHello() throws UnknownHostException {
        String containerId = InetAddress.getLocalHost().getHostName();
        String containerAddress = InetAddress.getLocalHost().getHostAddress();
        log.info("backend - hostname: {}, IP: {}", containerId, containerAddress);
        Map<String, String> result = new HashMap<>();
        result.put("hostname", containerId);
        result.put("ip", containerAddress);
        return result;
    }

    @GetMapping("/")
    public String serviceStatus() {
        return "java service is up";
    }
}
