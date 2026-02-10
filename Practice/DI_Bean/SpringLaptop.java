package DI_Bean;

import IoC.CPU;
import IoC.GPU;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpringLaptop {
    
    @Autowired
    private CPU cpu;
    
    @Autowired
    private GPU gpu;
    
    public void makeLaptop() {
        cpu.run();
        cpu.displayCPUStats();
        
        gpu.run();
        gpu.displayGPUStats();
    }
}