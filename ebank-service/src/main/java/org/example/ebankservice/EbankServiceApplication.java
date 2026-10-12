package org.example.ebankservice;

import org.example.ebankservice.entities.BankAccount;
import org.example.ebankservice.services.EbankService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class EbankServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbankServiceApplication.class, args);
    }


    @Bean
    CommandLineRunner start(EbankService ebankService) {
        return args -> {
            for (int i = 1; i <= 3; i++) {
                for (int j = 1; j <= 5; j++) {
                    ebankService.save(BankAccount.builder()
                            .balance(1000 + Math.random() * 60000)
                            .type(Math.random() > 0.5 ? "CURRENT_ACCOUNT" : "SAVING_ACCOUNT")
                            .customerId((long) i).build());
                }
            }
        };
    }
}

