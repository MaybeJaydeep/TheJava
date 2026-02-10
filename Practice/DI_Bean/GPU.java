package IoC;

import org.springframework.stereotype.Component;

@Component
public class GPU {
    
    public void run() {
        System.out.println("GPU is running...");
    }
    
    public void displayGPUStats() {
        System.out.println("GPU Stats: NVIDIA RTX 3060, 12GB VRAM");
    }
}