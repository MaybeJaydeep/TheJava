package Sealed_class;

public final class Patient extends AbstractHuman {

    private final String disease;

    public Patient(String name, int age, String disease) {
        super(name, age);
        this.disease = disease;
    }

    @Override
    public double testosteroneLevel() {
        return 400.0; // can vary due to illness
    }

    @Override
    public void displayInfo() {
        System.out.println("Patient: " + name + ", Disease: " + disease);
    }
}
