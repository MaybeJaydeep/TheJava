package Sealed_class;

public final class Boy extends AbstractHuman {

    public Boy(String name, int age) {
        super(name, age);
    }

    @Override
    public double testosteroneLevel() {
        return 150.0; // puberty range
    }

    @Override
    public void displayInfo() {
        System.out.println("Boy: " + name + ", Age: " + age);
    }
}
