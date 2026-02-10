package IoC;

public class CPUfactory {

    CPU getCPU(String cpu){
    if(cpu == null) return null;

    if(cpu.equalsIgnoreCase("INTEL") ) return new INTEL();

    if(cpu.equalsIgnoreCase("AMD") ) return new AMD();    
    
    else return null;
    }

}
