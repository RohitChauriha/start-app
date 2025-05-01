package com.example.backend.back_end_demo.services;


import com.example.backend.back_end_demo.domain.Customer;
import com.example.backend.back_end_demo.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class HelloController {
    private final CustomerRepository repository;

    public HelloController(CustomerRepository repository) {
        this.repository = repository;
    }

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

    @PostMapping("/customer")
    public ResponseEntity<Object> createCustomers(@RequestBody List<Customer> customers) {
        log.info("creating customers!!!");
//        repository.save(new Customer("Jack", "Bauer"));
//        repository.save(new Customer("Chloe", "O'Brian"));
//        repository.save(new Customer("Kim", "Bauer"));
//        repository.save(new Customer("David", "Palmer"));
//        repository.save(new Customer("Michelle", "Dessler"));
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/customer")
    public Iterable<Customer> getCustomers() {
        log.info("fetching customers!!!");
        return repository.findAll();
    }

    @GetMapping("/")
    public String serviceStatus() {
        return "java service is up";
    }
}
