package SpringBoot_Practice.BasicApp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class demonstrating Bean creation
 */
@Configuration
public class AppConfig {
    
    @Bean
    public MessageService messageService() {
        return new MessageService();
    }
}

class MessageService {
    public String getMessage() {
        return "Message from Bean!";
    }
}
