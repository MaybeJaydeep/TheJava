package SpringBoot_Practice.DependencyInjection;

import org.springframework.stereotype.Service;

/**
 * Email service demonstrating service layer component
 */
@Service
public class EmailService {
    
    public void sendWelcomeEmail(String email) {
        System.out.println("Sending welcome email to: " + email);
    }
    
    public void sendNotification(String email, String message) {
        System.out.println("Sending notification to " + email + ": " + message);
    }
}
