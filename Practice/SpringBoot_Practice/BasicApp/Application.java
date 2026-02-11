package SpringBoot_Practice.BasicApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Basic Spring Boot Application
 * Demonstrates the minimal setup required for a Spring Boot application
 */
@SpringBootApplication
public class Application {
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("Spring Boot Application Started!");
    }
}
