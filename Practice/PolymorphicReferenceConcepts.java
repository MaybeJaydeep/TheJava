/**
 * POLYMORPHIC REFERENCE - COMPLETE CONCEPTS & PRACTICAL USAGE
 * 
 * Polymorphism = "Many Forms"
 * A single reference variable can point to objects of different types (parent/interface/abstract class)
 * Actual method called is determined at RUNTIME based on OBJECT TYPE, not REFERENCE TYPE
 */

public class PolymorphicReferenceConcepts {
    public static void main(String[] args) {
        System.out.println("========== POLYMORPHIC REFERENCE CONCEPTS ==========\n");
        
        // ========== CONCEPT 1: BASIC POLYMORPHIC REFERENCE (UPCASTING) ==========
        System.out.println("--- 1. BASIC POLYMORPHIC REFERENCE (UPCASTING) ---");
        demonstrateBasicPolymorphism();
        
        // ========== CONCEPT 2: REFERENCE TYPE vs OBJECT TYPE ==========
        System.out.println("\n--- 2. REFERENCE TYPE vs OBJECT TYPE ---");
        demonstrateReferenceVsObjectType();
        
        // ========== CONCEPT 3: METHOD OVERRIDING WITH POLYMORPHISM ==========
        System.out.println("\n--- 3. METHOD OVERRIDING (LATE BINDING / DYNAMIC DISPATCH) ---");
        demonstrateMethodOverriding();
        
        // ========== CONCEPT 4: DOWNCASTING ---
        System.out.println("\n--- 4. DOWNCASTING ---");
        demonstrateDowncasting();
        
        // ========== CONCEPT 5: INSTANCEOF OPERATOR ---
        System.out.println("\n--- 5. INSTANCEOF OPERATOR (Type Checking) ---");
        demonstrateInstanceof();
        
        // ========== CONCEPT 6: INTERFACE POLYMORPHISM ---
        System.out.println("\n--- 6. INTERFACE POLYMORPHISM ---");
        demonstrateInterfacePolymorphism();
        
        // ========== CONCEPT 7: ABSTRACT CLASS POLYMORPHISM ---
        System.out.println("\n--- 7. ABSTRACT CLASS POLYMORPHISM ---");
        demonstrateAbstractClassPolymorphism();
        
        // ========== CONCEPT 8: PRACTICAL USE CASE - COLLECTIONS ---
        System.out.println("\n--- 8. PRACTICAL USE CASE - COLLECTIONS WITH POLYMORPHIC REFERENCE ---");
        demonstrateCollectionsPolymorphism();
        
        // ========== CONCEPT 9: STATIC vs INSTANCE METHOD BEHAVIOR ---
        System.out.println("\n--- 9. STATIC vs INSTANCE METHOD WITH POLYMORPHIC REFERENCE ---");
        demonstrateStaticVsInstanceWithPolymorphism();
    }
    
    // ========== CONCEPT 1: BASIC POLYMORPHIC REFERENCE (UPCASTING) ==========
    static void demonstrateBasicPolymorphism() {
        // Polymorphic Reference: Parent class reference pointing to child class object
        Vehicle vehicle1 = new Car();     // Upcasting (implicit)
        Vehicle vehicle2 = new Bike();    // Upcasting (implicit)
        Vehicle vehicle3 = new Truck();   // Upcasting (implicit)
        
        System.out.println("vehicle1 reference type: Vehicle, actual object: Car");
        System.out.println("vehicle2 reference type: Vehicle, actual object: Bike");
        System.out.println("vehicle3 reference type: Vehicle, actual object: Truck");
        
        // Method called based on ACTUAL OBJECT TYPE
        vehicle1.drive();  // Calls Car's drive()
        vehicle2.drive();  // Calls Bike's drive()
        vehicle3.drive();  // Calls Truck's drive()
        
        System.out.println("\n✓ Same reference type, different objects, different behavior!");
    }
    
    // ========== CONCEPT 2: REFERENCE TYPE vs OBJECT TYPE ==========
    static void demonstrateReferenceVsObjectType() {
        Vehicle vehicleRef = new Car(); // Reference type = Vehicle, Object type = Car
        
        System.out.println("Reference type: Vehicle");
        System.out.println("Actual object type: Car");
        System.out.println();
        
        // Can access Vehicle methods
        vehicleRef.drive();
        vehicleRef.refuel();
        
        // CANNOT directly access Car-specific methods (even though object is Car)
        // vehicleRef.openTrunk(); // Compilation Error - Vehicle reference doesn't know about openTrunk()
        
        System.out.println("\nReference type determines what methods are ACCESSIBLE at compile-time");
        System.out.println("Object type determines which method is EXECUTED at runtime");
    }
    
    // ========== CONCEPT 3: METHOD OVERRIDING WITH POLYMORPHISM ==========
    static void demonstrateMethodOverriding() {
        Vehicle[] vehicles = {
            new Car(),
            new Bike(),
            new Truck(),
            new Car()
        };
        
        System.out.println("Polymorphic array - storing different vehicle types:");
        
        for (int i = 0; i < vehicles.length; i++) {
            System.out.println("\n[Vehicle " + (i+1) + "]");
            vehicles[i].drive();      // RUNTIME binding - actual object's method called
            vehicles[i].refuel();     // RUNTIME binding - actual object's method called
            System.out.println("Speed: " + vehicles[i].getSpeed() + " km/h");
        }
        
        System.out.println("\n✓ Same reference type (Vehicle) calls different methods based on object type");
        System.out.println("✓ This is LATE BINDING or DYNAMIC DISPATCH");
    }
    
    // ========== CONCEPT 4: DOWNCASTING ==========
    static void demonstrateDowncasting() {
        Vehicle vehicleRef = new Car(); // Upcasting (safe, automatic)
        
        // Downcasting (need explicit cast, risky)
        if (vehicleRef instanceof Car) {
            Car car = (Car) vehicleRef; // Explicit downcast
            System.out.println("Successfully downcasted to Car");
            car.openTrunk();            // Now can access Car-specific method
        }
        
        System.out.println();
        
        // Unsafe downcasting (without instanceof check)
        Vehicle vehicleRef2 = new Bike(); // Object is Bike
        
        try {
            Car car2 = (Car) vehicleRef2;  // Runtime ClassCastException!
            car2.openTrunk();
        } catch (ClassCastException e) {
            System.out.println("ERROR: Cannot cast Bike to Car - " + e.getClass().getSimpleName());
        }
        
        System.out.println("\n✓ Downcasting requires explicit cast and instanceof check to avoid errors");
    }
    
    // ========== CONCEPT 5: INSTANCEOF OPERATOR ==========
    static void demonstrateInstanceof() {
        Vehicle[] vehicles = {new Car(), new Bike(), new Truck()};
        
        for (Vehicle v : vehicles) {
            System.out.println("Object is: " + v.getClass().getSimpleName());
            
            if (v instanceof Car) {
                System.out.println("  → This is a Car, opening trunk...");
                ((Car) v).openTrunk();
            } else if (v instanceof Bike) {
                System.out.println("  → This is a Bike, checking engine...");
                ((Bike) v).checkEngine();
            } else if (v instanceof Truck) {
                System.out.println("  → This is a Truck, loading cargo...");
                ((Truck) v).loadCargo();
            }
            System.out.println();
        }
        
        System.out.println("✓ instanceof checks object's actual type before downcasting");
    }
    
    // ========== CONCEPT 6: INTERFACE POLYMORPHISM ==========
    static void demonstrateInterfacePolymorphism() {
        Animal dog = new Dog();        // Interface reference to Dog
        Animal cat = new Cat();        // Interface reference to Cat
        Animal bird = new Bird();      // Interface reference to Bird
        
        Animal[] animals = {dog, cat, bird};
        
        System.out.println("Polymorphic behavior through Interface:");
        for (Animal animal : animals) {
            System.out.println("\n" + animal.getClass().getSimpleName() + ":");
            animal.eat();
            animal.makeSound();
            animal.move();
        }
        
        System.out.println("\n✓ Interface reference can point to any implementing class");
    }
    
    // ========== CONCEPT 7: ABSTRACT CLASS POLYMORPHISM ==========
    static void demonstrateAbstractClassPolymorphism() {
        Shape circle = new Circle(5);
        Shape rectangle = new Rectangle(4, 6);
        Shape triangle = new Triangle(3, 4, 5);
        
        System.out.println("Polymorphic collection of shapes:");
        System.out.println();
        
        // Calculate areas polymorphically
        Shape[] shapes = {circle, rectangle, triangle};
        double totalArea = 0;
        
        for (Shape shape : shapes) {
            double area = shape.calculateArea();
            totalArea += area;
            System.out.println(shape.getClass().getSimpleName() + " - Area: " + area);
        }
        
        System.out.println("\nTotal area: " + totalArea);
        System.out.println("\n✓ Abstract class reference enables polymorphic behavior");
    }
    
    // ========== CONCEPT 8: PRACTICAL USE CASE - COLLECTIONS ==========
    static void demonstrateCollectionsPolymorphism() {
        // Creating a heterogeneous collection using polymorphic references
        System.out.println("Store Different Payment Methods in Single Collection:\n");
        
        PaymentProcessor[] processors = {
            new CreditCardPayment(),
            new UPIPayment(),
            new NetBankingPayment(),
            new CreditCardPayment(),
            new UPIPayment()
        };
        
        double totalAmount = 0;
        double[] amounts = {5000, 2000, 10000, 3500, 1500};
        
        for (int i = 0; i < processors.length; i++) {
            System.out.println("Payment " + (i+1) + ": Amount = ₹" + amounts[i]);
            processors[i].processPayment(amounts[i]);
            processors[i].generateReceipt();
            totalAmount += amounts[i];
            System.out.println();
        }
        
        System.out.println("Total processed: ₹" + totalAmount);
        System.out.println("\n✓ Polymorphic references enable handling different types uniformly");
    }
    
    // ========== CONCEPT 9: STATIC vs INSTANCE METHOD ==========
    static void demonstrateStaticVsInstanceWithPolymorphism() {
        Vehicle vehicleRef = new Car();
        
        System.out.println("With Polymorphic Reference to Car:\n");
        
        System.out.println("Instance method (RUNTIME binding):");
        vehicleRef.drive();  // Calls Car's drive() - Runtime
        
        System.out.println("\nStatic method (COMPILE-TIME binding):");
        vehicleRef.getMaxSpeed();  // Calls Vehicle's getMaxSpeed() - Compile-time
        
        System.out.println("\n✓ Static methods use COMPILE-TIME binding (reference type)");
        System.out.println("✓ Instance methods use RUNTIME binding (object type)");
    }
}

// ========== VEHICLE HIERARCHY ==========
abstract class Vehicle {
    abstract void drive();
    abstract void refuel();
    
    int getSpeed() {
        return 100;  // Default
    }
    
    static void getMaxSpeed() {
        System.out.println("Calling static method on Vehicle (COMPILE-TIME bound)");
    }
}

class Car extends Vehicle {
    @Override
    void drive() {
        System.out.println("🚗 Car is driving smoothly on road...");
    }
    
    @Override
    void refuel() {
        System.out.println("⛽ Car refueling with petrol...");
    }
    
    @Override
    int getSpeed() {
        return 180;
    }
    
    void openTrunk() {
        System.out.println("🔓 Opening car trunk...");
    }
}

class Bike extends Vehicle {
    @Override
    void drive() {
        System.out.println("🏍️  Bike is riding fast...");
    }
    
    @Override
    void refuel() {
        System.out.println("⛽ Bike refueling with petrol...");
    }
    
    @Override
    int getSpeed() {
        return 150;
    }
    
    void checkEngine() {
        System.out.println("🔧 Checking bike engine...");
    }
}

class Truck extends Vehicle {
    @Override
    void drive() {
        System.out.println("🚚 Truck is driving slowly with heavy load...");
    }
    
    @Override
    void refuel() {
        System.out.println("⛽ Truck refueling with diesel...");
    }
    
    @Override
    int getSpeed() {
        return 100;
    }
    
    void loadCargo() {
        System.out.println("📦 Loading cargo into truck...");
    }
}

// ========== ANIMAL HIERARCHY (INTERFACE POLYMORPHISM) ==========
interface Animal {
    void eat();
    void makeSound();
    void move();
}

class Dog implements Animal {
    @Override
    public void eat() {
        System.out.println("🐕 Dog eating meat...");
    }
    
    @Override
    public void makeSound() {
        System.out.println("🐕 Dog: Woof! Woof!");
    }
    
    @Override
    public void move() {
        System.out.println("🐕 Dog running...");
    }
}

class Cat implements Animal {
    @Override
    public void eat() {
        System.out.println("🐱 Cat eating fish...");
    }
    
    @Override
    public void makeSound() {
        System.out.println("🐱 Cat: Meow! Meow!");
    }
    
    @Override
    public void move() {
        System.out.println("🐱 Cat jumping...");
    }
}

class Bird implements Animal {
    @Override
    public void eat() {
        System.out.println("🐦 Bird eating seeds...");
    }
    
    @Override
    public void makeSound() {
        System.out.println("🐦 Bird: Tweet! Tweet!");
    }
    
    @Override
    public void move() {
        System.out.println("🐦 Bird flying...");
    }
}

// ========== SHAPE HIERARCHY (ABSTRACT CLASS POLYMORPHISM) ==========
abstract class Shape {
    abstract double calculateArea();
}

class Circle extends Shape {
    double radius;
    
    Circle(double radius) {
        this.radius = radius;
    }
    
    @Override
    double calculateArea() {
        return Math.PI * radius * radius;
    }
}

class Rectangle extends Shape {
    double length, width;
    
    Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }
    
    @Override
    double calculateArea() {
        return length * width;
    }
}

class Triangle extends Shape {
    double a, b, c;
    
    Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    @Override
    double calculateArea() {
        double s = (a + b + c) / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }
}

// ========== PAYMENT PROCESSING HIERARCHY (PRACTICAL USE CASE) ==========
interface PaymentProcessor {
    void processPayment(double amount);
    void generateReceipt();
}

class CreditCardPayment implements PaymentProcessor {
    @Override
    public void processPayment(double amount) {
        System.out.println("💳 Processing Credit Card Payment: ₹" + amount);
        System.out.println("   Verifying card details...");
        System.out.println("   Processing through bank...");
    }
    
    @Override
    public void generateReceipt() {
        System.out.println("📋 Credit Card Receipt Generated");
    }
}

class UPIPayment implements PaymentProcessor {
    @Override
    public void processPayment(double amount) {
        System.out.println("📱 Processing UPI Payment: ₹" + amount);
        System.out.println("   Sending OTP...");
        System.out.println("   Verifying PIN...");
    }
    
    @Override
    public void generateReceipt() {
        System.out.println("📋 UPI Receipt Generated");
    }
}

class NetBankingPayment implements PaymentProcessor {
    @Override
    public void processPayment(double amount) {
        System.out.println("🏦 Processing Net Banking Payment: ₹" + amount);
        System.out.println("   Redirecting to bank portal...");
        System.out.println("   Authenticating user...");
    }
    
    @Override
    public void generateReceipt() {
        System.out.println("📋 Net Banking Receipt Generated");
    }
}
