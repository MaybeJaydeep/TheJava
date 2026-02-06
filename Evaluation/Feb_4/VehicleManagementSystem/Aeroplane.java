package Evaluation.Feb_4.VehicleManagementSystem;

public class Aeroplane implements Vehicle{
    private int VIN;
    private String manufacturer ;
    private String fuelType ;
    private int mileage;
    
    Aeroplane(){}

    Aeroplane(int VIN,String manufacturer,String fuelType, int mileage){
        this.VIN = VIN;
        this.manufacturer = manufacturer;
        this.fuelType = fuelType;
        this.mileage = mileage;
    }

    Altmeter alt = () ->  System.out.println("+10,000 ft");

    public int getVIN(){
        return VIN;
    }

    public String getManufacturer(){
        return manufacturer;
    }

    @Override
    public boolean needsService() {
        return mileage >= 10000;
    }

    @Override
    public String getFuelType() {
        return fuelType;
    }

    @Override
    public void run() throws GetServicedException {
        if(needsService()) throw new GetServicedException("Plane needs to be Serviced...");
        
        System.out.println("Plane started...");
        alt.height();
    
    }
}
