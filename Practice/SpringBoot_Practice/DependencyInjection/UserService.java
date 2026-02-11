package SpringBoot_Practice.DependencyInjection;

import org.springframework.stereotype.Service;

/**
 * Service demonstrating Dependency Injection
 */
@Service
public class UserService {
    
    private final UserRepository userRepository;
    private final EmailService emailService;
    
    // Constructor Injection (Recommended)
    public UserService(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }
    
    public void createUser(String name, String email) {
        System.out.println("Creating user: " + name);
        userRepository.save(name, email);
        emailService.sendWelcomeEmail(email);
    }
    
    public void getUser(String email) {
        String user = userRepository.findByEmail(email);
        System.out.println("Found user: " + user);
    }
}
