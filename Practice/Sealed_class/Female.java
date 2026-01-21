package Sealed_class;

public sealed class Female extends AbstractHuman
        permits Girl {

    protected Female(String name, int age) {
        super(name, age);
    }

    @Override
    public double testosteroneLevel() {
        return 70.0; // average female level
    }

    @Override
    public void displayInfo() {
        System.out.println("Female Patient: " + name + ", Age: " + age);
    }
}
