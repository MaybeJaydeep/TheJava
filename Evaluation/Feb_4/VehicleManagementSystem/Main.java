package Evaluation.Feb_4.VehicleManagementSystem;

public class Main{
    public static void main(String[] args){
        Truck truck = new Truck(100,"Tata","gas",1900);
        System.out.println("VIN: "+truck.getVIN());
        try {
            truck.run();
        } catch (GetServicedException e) {
            System.out.println("Truck requires service: " + e.getMessage());
        }

        Aeroplane plane = new Aeroplane(101,"SpicyJet","FuelJet",1000);
        System.out.println("VIN: " +plane.getVIN());
        try {
            plane.run();
        } catch (GetServicedException e) {
            System.out.println("Aeroplane requires service: " + e.getMessage());
        }
    }
}
