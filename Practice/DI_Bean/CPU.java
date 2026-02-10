package IoC;

import org.springframework.stereotype.Component;

@Component
public class CPU {
    
    public void run() {
        System.out.println("CPU is running...");
    }
    
    public void displayCPUStats() {
        System.out.println("CPU Stats: Intel i7, 3.5 GHz, 8 cores");
    }
}