package Evaluation.Feb_4.VehicleManagementSystem;

public class Truck implements Vehicle{
    private int VIN;
    private String manufacturer ;
    private String fuelType ;
    private int mileage;

    Truck(){}

    Truck(int VIN){
        this.VIN = VIN;
    }

    Truck(int VIN, String manufacturer, String fuelType, int mileage) {
        this.VIN = VIN;
        this.manufacturer = manufacturer;
        this.fuelType = fuelType;
        this.mileage = mileage;
    }

    public int getVIN(){
        return VIN;
    }
    
    public String getManufacturer(){
        return manufacturer;
    }

    @Override
    public String getFuelType() {
        return fuelType;
    }

    @Override
    public void run() throws GetServicedException {
    if(needsService()){
        throw new GetServicedException("Get your truck serviced...");
    }
        System.out.println("Truck started...");
        GPS gps = () ->  System.out.println("Latitude: 54.52665, Longitude: -88.83006");
        gps.gps();
    }

    @Override
    public boolean needsService() {
        return mileage >= 10000;
    }
    
}

