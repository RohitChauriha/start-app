package com.startapp.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.net.InetAddress;


@SpringBootApplication
@Slf4j
public class ConsumerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner startup() {
        return args -> {
            System.setProperty("HOSTNAME", InetAddress.getLocalHost().getHostName());
            System.setProperty("IPAddr", InetAddress.getLocalHost().getHostAddress());
        };
    }
}
