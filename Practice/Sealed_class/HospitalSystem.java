package Sealed_class;

public class HospitalSystem {

    public static void main(String[] args) {

        Human[] humans = {
                new Male("Rahul", 35),
                new Girl("Radha", 15),
                new Boy("Aarav", 10),
                new Employee("Dr. Mehta", 45, "Surgeon"),
                new Patient("Suresh", 60, "Diabetes")
        };

        for (Human human : humans) {
            human.displayInfo();
            System.out.println("Testosterone Level: " + human.testosteroneLevel());
            System.out.println("Is Adult: " + human.isAdult());
            System.out.println("--------------------------------");
        }
    }
}
