package IoC;

public class GPUfactory {
    
    GPU getGPU(String gpu){
        if(gpu == null) return null;

        if(gpu.equalsIgnoreCase("GEFORCERTX")) return new GeForceRtx();

        if(gpu.equalsIgnoreCase("RADEON")) return new Radeon();
        else return null;
    }
}
