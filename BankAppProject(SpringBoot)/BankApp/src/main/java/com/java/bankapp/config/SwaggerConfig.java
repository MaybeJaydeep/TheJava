package com.java.bankapp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI bankingApi() {

        return new OpenAPI()
                .info(new Info()
                        .title("Banking API")
                        .version("1.0")
                        .description("Spring Boot Banking Backend Application")
                        .contact(new Contact()
                                .name("Jaydeep Badal")
                                .email("jaydeep@test.com")
                        )
                );
    }
}