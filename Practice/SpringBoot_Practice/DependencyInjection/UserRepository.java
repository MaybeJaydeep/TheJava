package SpringBoot_Practice.DependencyInjection;

import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;

/**
 * Repository demonstrating data access layer
 */
@Repository
public class UserRepository {
    
    private final Map<String, String> users = new HashMap<>();
    
    public void save(String name, String email) {
        users.put(email, name);
        System.out.println("User saved to repository");
    }
    
    public String findByEmail(String email) {
        return users.getOrDefault(email, "User not found");
    }
}
