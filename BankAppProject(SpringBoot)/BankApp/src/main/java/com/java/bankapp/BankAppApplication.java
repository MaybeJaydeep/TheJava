package com.java.bankapp;

import com.java.bankapp.service.AccountService;
import com.java.bankapp.service.CustomerService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class BankAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankAppApplication.class, args);
    }

    @Bean
    CommandLineRunner run(CustomerService customerService,
                          AccountService accountService) {

        return args -> {
            System.out.println("===== BANK APP TEST START =====");
        };
    }
}