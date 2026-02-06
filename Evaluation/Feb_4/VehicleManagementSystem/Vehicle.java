package Evaluation.Feb_4.VehicleManagementSystem;

@FunctionalInterface
interface GPS{
    void gps();
}

@FunctionalInterface
interface Altmeter{
    void height();
}

interface Vehicle {

    boolean getMileage();
    String getFuelType();
    void run() throws GetServicedException;

}

