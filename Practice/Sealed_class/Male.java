
package Sealed_class;
public final class Male extends AbstractHuman {

    public Male(String name, int age) {
        super(name, age);
    }

    @Override
    public double testosteroneLevel() {
        return 700.0; // average adult male level
    }

    @Override
    public void displayInfo() {
        System.out.println("Male Patient: " + name + ", Age: " + age);
    }
}
