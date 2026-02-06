package Evaluation.Feb_4.VehicleManagementSystem;

public class Aeroplane implements Vehicle{
    private int VIN;
    String manufacturer ;
    String fuelType ;
    int mileage;
    
    Aeroplane(){}

    Aeroplane(int VIN,String manufacterer,String fuelType, int mileage){
        this.VIN = VIN;
        this.manufacturer = manufacterer;
        this.fuelType = fuelType;
        this.mileage = mileage;
    }

    Altmeter Alt = () ->  System.out.println("+10,000 ft");

    int getVIN(){
        return VIN;
    }

    @Override
    public boolean getMileage() {
        return mileage >= 10000;
    }

    @Override
    public String getFuelType() {
        return fuelType;
    }

    @Override
    public void run() throws GetServicedException {
        if(getMileage()) throw new GetServicedException("Plane needs to be Serviced...");
        
        System.out.println("Plane started...");
        Alt.height();
    
    }
}