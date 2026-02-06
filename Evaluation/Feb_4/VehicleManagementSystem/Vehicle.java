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

    /**
     * Indicates whether this vehicle needs to be serviced.
     *
     * @return {@code true} if the vehicle requires maintenance; {@code false} otherwise.
     */
    boolean needsService();

    /**
     * @deprecated Use {@link #needsService()} instead. This method name is misleading
     * as it does not return a mileage value.
     */
    @Deprecated
    default boolean getMileage() {
        return needsService();
    }
    String getFuelType();
    void run() throws GetServicedException;

}

