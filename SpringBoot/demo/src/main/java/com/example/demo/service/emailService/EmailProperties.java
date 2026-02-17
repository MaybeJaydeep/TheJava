package com.example.demo.service.emailService;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "app.mail")
public class EmailProperties {
    // getters + setters
    private String senderAddress;
    private String senderName;

}
