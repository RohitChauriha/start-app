package com.example.backend.back_end_demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.net.InetAddress;


@SpringBootApplication
@Slf4j
public class BackEndDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackEndDemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner startup() {

        return args -> {
            String containerId = InetAddress.getLocalHost().getHostName();
            String containerAddress = InetAddress.getLocalHost().getHostAddress();
//            System.setProperty("HOSTNAME", containerId);
//            System.setProperty("IPAddr", containerAddress);
        };
    }
}
