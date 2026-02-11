package SpringBoot_Practice.BasicApp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Simple REST Controller
 * Demonstrates basic endpoint creation
 */
@RestController
@RequestMapping("/api")
public class HelloController {
    
    @GetMapping("/hello")
    public String hello() {
        return "Hello from Spring Boot!";
    }
    
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to Spring Boot Practice";
    }
}
