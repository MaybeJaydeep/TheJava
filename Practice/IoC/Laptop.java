package IoC;

/*
Developer driven approach
public class Laptop {
    public static void main(String[] args) {
        Intel cpu = new Intel();
        cpu.run();
        cpu.displayCPUStats();
        GeForceRtx gpu = new GeForceRtx();
        gpu.run()
        gpu.displayGPUStats();
    }
}
*/

// IoC -> Inversion of Control
public class Laptop{
    public static void main(String[] args) {
        CPUfactory cpufactory = new CPUfactory();
        CPU cpu = cpufactory.getCPU("Intel");
        cpu.run();
        cpu.displayCPUStats();

        GPUfactory gpufactory = new GPUfactory();
        GPU gpu = gpufactory.getGPU("geforcertx");
        gpu.run();
        gpu.displayGPUStats();
    }
}

