package Evaluation.Feb_4.VehicleManagementSystem;

public class Main{
    public static void main(String[] args){
        Truck truck = new Truck(100,"Tata","gas",1900);
        System.out.println("VIN: "+truck.getVIN());
        truck.run();

        Aeroplane plane = new Aeroplane(101,"SpicyJet","FuelJet",1000);
        System.out.println("VIN: " +plane.getVIN());
        plane.run();
    }
}
