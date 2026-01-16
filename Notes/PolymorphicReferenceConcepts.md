# Polymorphic Reference Concepts

## Definition
**Polymorphic Reference** = A single reference variable that can point to objects of different types (parent class, interface, or abstract class). The actual method executed depends on the object type at RUNTIME.

---

## Core Concept

```
Parent Reference → Can point to → Child Object(s)
```

```java
Vehicle vehicleRef = new Car();      // ✓ Valid - Upcasting
Vehicle vehicleRef = new Bike();     // ✓ Valid - Upcasting
Vehicle vehicleRef = new Truck();    // ✓ Valid - Upcasting
```

---

## Concept 1: Upcasting (Parent Reference to Child Object)

### Implicit Upcasting
```java
// Automatic, no explicit cast needed, always safe
Animal dog = new Dog();           // Dog IS-A Animal
Vehicle car = new Car();          // Car IS-A Vehicle
PaymentMethod payment = new UPI(); // UPI IS-A PaymentMethod
```

### Advantages
- ✓ Always safe (no runtime errors)
- ✓ Automatic (no explicit cast needed)
- ✓ Enables polymorphism

---

## Concept 2: Reference Type vs Object Type

```
Reference Type = What compiler sees (determines accessible methods)
Object Type = What actually exists in memory (determines executed method)
```

### Example
```java
Vehicle vehicleRef = new Car();  // Reference type = Vehicle
                                 // Object type = Car

vehicleRef.drive();       // ✓ Works (Vehicle has drive)
vehicleRef.refuel();      // ✓ Works (Vehicle has refuel)
vehicleRef.openTrunk();   // ✗ ERROR (Vehicle reference doesn't know about openTrunk)

// Solution: Cast to Car
((Car) vehicleRef).openTrunk(); // ✓ Now works
```

### Key Point
- **Compile-time:** Check against reference type
- **Runtime:** Execute actual object's method

---

## Concept 3: Method Overriding (Late Binding)

```java
class Vehicle {
    void drive() {
        System.out.println("Vehicle driving");
    }
}

class Car extends Vehicle {
    @Override
    void drive() {
        System.out.println("Car driving smoothly");
    }
}

class Bike extends Vehicle {
    @Override
    void drive() {
        System.out.println("Bike riding fast");
    }
}

// POLYMORPHIC USAGE
Vehicle[] vehicles = {new Car(), new Bike(), new Car()};

for (Vehicle v : vehicles) {
    v.drive();  // Different method executes each time!
}
```

**Output:**
```
Car driving smoothly
Bike riding fast
Car driving smoothly
```

---

## Concept 4: Downcasting (Parent Reference to Child Type)

### Explicit Downcasting
```java
Vehicle vehicleRef = new Car();

// Unsafe way (can throw ClassCastException)
Car car = (Car) vehicleRef;
car.openTrunk();

// Safe way (using instanceof)
if (vehicleRef instanceof Car) {
    Car car = (Car) vehicleRef;
    car.openTrunk();  // ✓ Safe
}
```

### Rules
- ✓ Use `instanceof` before downcasting
- ✗ Don't downcast without checking type
- ✗ Cannot downcast to unrelated types

### Common Error
```java
Vehicle vehicleRef = new Bike();
Car car = (Car) vehicleRef;  // ✗ ClassCastException at runtime!
```

---

## Concept 5: instanceof Operator

### Purpose
Check if object is instance of a class/interface before downcasting

### Syntax
```java
if (object instanceof ClassName) {
    // Safe to downcast
    ClassName casted = (ClassName) object;
}
```

### Example
```java
Vehicle[] vehicles = {new Car(), new Bike(), new Truck()};

for (Vehicle v : vehicles) {
    if (v instanceof Car) {
        ((Car) v).openTrunk();
    } else if (v instanceof Bike) {
        ((Bike) v).checkEngine();
    } else if (v instanceof Truck) {
        ((Truck) v).loadCargo();
    }
}
```

---

## Concept 6: Interface Polymorphism

### Multiple Implementations
```java
interface Animal {
    void eat();
    void makeSound();
}

class Dog implements Animal { ... }
class Cat implements Animal { ... }
class Bird implements Animal { ... }

// Polymorphic references
Animal[] animals = {
    new Dog(),
    new Cat(),
    new Bird()
};

for (Animal a : animals) {
    a.eat();         // Different eating behavior
    a.makeSound();   // Different sound
}
```

### Advantages
- Multiple unrelated classes can be treated uniformly
- No inheritance hierarchy needed
- Flexible and loosely coupled

---

## Concept 7: Abstract Class Polymorphism

### Usage Pattern
```java
abstract class Shape {
    abstract double calculateArea();
}

class Circle extends Shape { ... }
class Rectangle extends Shape { ... }
class Triangle extends Shape { ... }

// Polymorphic reference
Shape[] shapes = {
    new Circle(5),
    new Rectangle(4, 6),
    new Triangle(3, 4, 5)
};

double totalArea = 0;
for (Shape s : shapes) {
    totalArea += s.calculateArea();  // Different calculation each time
}
```

---

## Concept 8: Practical Use Case - Collections

### Problem
Store different types in one collection

### Solution
Use polymorphic references

```java
// Payment processing example
PaymentProcessor[] payments = {
    new CreditCardPayment(),
    new UPIPayment(),
    new NetBankingPayment(),
    new CreditCardPayment()
};

for (PaymentProcessor p : payments) {
    p.processPayment(1000);  // Different implementation each time
    p.generateReceipt();      // Different receipt format each time
}
```

### Real-World Benefits
- Handle multiple payment types uniformly
- Add new payment type without changing existing code
- Process all types in single loop

---

## Concept 9: Static vs Instance Methods with Polymorphic Reference

| Aspect | Static Methods | Instance Methods |
|--------|---|---|
| **Binding** | Compile-time | Runtime |
| **Based On** | Reference type | Object type |
| **Polymorphic?** | ✗ NO | ✓ YES |
| **Overridable?** | ✗ NO (Shadowing) | ✓ YES |

### Example
```java
Vehicle vehicleRef = new Car();

vehicleRef.drive();        // ✓ Calls Car's drive() - RUNTIME BINDING
vehicleRef.getMaxSpeed();  // Calls Vehicle's getMaxSpeed() - COMPILE-TIME BINDING
```

---

## Key Differences Summary

### Upcasting vs Downcasting
```
Upcasting:     Child → Parent (automatic, safe)
Downcasting:   Parent → Child (explicit, risky, needs instanceof)
```

### Reference Type vs Object Type
```
Reference Type: Used by compiler for validation
Object Type:    Used by JVM for actual execution
```

### Method Overriding vs Method Shadowing
```
Overriding:   Instance methods (polymorphic, runtime binding)
Shadowing:    Static methods (not polymorphic, compile-time)
```

---

## Rules to Remember

1. **Parent reference CAN hold child object** ✓
2. **Cannot access child-specific members** without casting
3. **Always use instanceof before downcasting** ✓
4. **Static methods are NOT polymorphic**
5. **Only instance methods use dynamic dispatch**
6. **Polymorphic reference enables code reuse**

---

## Quick Revision Checklist

- [ ] Polymorphic reference = parent reference → child object
- [ ] Upcasting is automatic and safe
- [ ] Downcasting needs instanceof check
- [ ] Reference type checked at compile-time
- [ ] Object type used at runtime for method execution
- [ ] Static methods don't use polymorphism
- [ ] Interface can be polymorphic reference
- [ ] Abstract class can be polymorphic reference
- [ ] Collections benefit from polymorphic references
- [ ] Dynamic dispatch = runtime method selection

---

## Real-World Analogy

**Vending Machine:**
```
Reference Type: "Beverage Dispenser"
Objects: Cola machine, Juice machine, Water machine

Customer (compiler): "Does dispenser have dispense() method?"
Response: "Yes, all dispensers have it" ✓

At runtime, when button pressed:
- Cola machine → Dispenses cola
- Juice machine → Dispenses juice
- Water machine → Dispenses water

Same interface, different behavior at runtime!
```

---

## Code Example Template

```java
// Polymorphic reference pattern
ParentClass ref = new ChildClass();

ref.overriddenMethod();  // Calls ChildClass version (runtime)
ref.parentMethod();      // Calls ParentClass version (if not overridden)

// Type checking and downcasting
if (ref instanceof ChildClass) {
    ChildClass child = (ChildClass) ref;
    child.childSpecificMethod();  // ✓ Now accessible
}
```

