package Sealed_class;

public final class Girl extends Female {

    public Girl(String name, int age) {
        super(name, age);
    }

    // Girl-specific hormone behavior
    public String girlHormoneProfile() {
        if (age < 12) {
            return "Low estrogen - pre-puberty";
        } else {
            return "Rising estrogen - puberty phase";
        }
    }

    @Override
    public void displayInfo() {
        System.out.println("Girl: " + name + ", Age: " + age);
        System.out.println("Hormone Profile: " + girlHormoneProfile());
    }
}
