# OOP Core Concepts – Abstraction, Inheritance & Polymorphism

📅 **Date:** 13/01/2026
✍️ **Author:** Jaydeep Badal

---

## 1️⃣ Abstraction

### 🔹 What is Abstraction?

Abstraction means **hiding implementation details** and showing **only essential behavior** to the user.

```
User
 │
 │  start()
 ▼
[ Car ]  ──► Engine, Gear, Fuel logic (hidden)
```

---

## 1.1 How Abstraction is Achieved in Java

| Way            | Keyword     |
| -------------- | ----------- |
| Abstract Class | `abstract`  |
| Interface      | `interface` |

---

## 1.2 Abstract Class

### 🔹 Characteristics

* Can have **abstract & non-abstract methods**
* Can have **constructors**
* Supports **partial abstraction**

```java
abstract class Vehicle {
    abstract void start();

    void fuelType() {
        System.out.println("Petrol/Diesel");
    }
}

class Car extends Vehicle {
    void start() {
        System.out.println("Car starts with key");
    }
}
```

---

## 1.3 Interface (Java 8 Enhanced)

### 🔹 Characteristics

* Supports **100% abstraction (before Java 8)**
* Java 8 allows:

  * `default` methods
  * `static` methods

```java
interface Payment {
    void pay();

    default void receipt() {
        System.out.println("Receipt generated");
    }
}
```

---

## 1.4 Abstract Class vs Interface

| Feature              | Abstract Class | Interface             |
| -------------------- | -------------- | --------------------- |
| Multiple inheritance | ❌              | ✅                     |
| Constructor          | ✅              | ❌                     |
| Default methods      | ❌              | ✅ (Java 8)            |
| Variables            | Instance       | `public static final` |

---

## 2️⃣ Inheritance

### 🔹 What is Inheritance?

Inheritance allows a class to **acquire properties and behavior** of another class.

```
Parent (Vehicle)
     ▲
     │
Child (Car)
```

---

## 2.1 Example

```java
class Vehicle {
    void start() {
        System.out.println("Vehicle starts");
    }
}

class Bike extends Vehicle {
    void ride() {
        System.out.println("Bike riding");
    }
}
```

---

## 2.2 Types of Inheritance in Java

| Type                 | Supported  |
| -------------------- | ---------- |
| Single               | ✅         |
| Multilevel           | ✅         |
| Hierarchical         | ✅         |
| Multiple (class)     | ❌         |
| Multiple (interface) | ✅         |

---

## 2.3 Why Multiple Inheritance Not Supported (Class)?

```
class A { void show() {} }
class B { void show() {} }
class C extends A, B ❌  // Diamond Problem
```

➡ Solved using **Interfaces**

---

## 3️⃣ Polymorphism

### 🔹 What is Polymorphism?

**One interface, many forms**

```
Shape s
 │
 ├─ Circle
 ├─ Square
 └─ Triangle
```

---

## 3.1 Compile-Time Polymorphism (Method Overloading)

```java
class MathUtil {
    int add(int a, int b) {
        return a + b;
    }

    double add(double a, double b) {
        return a + b;
    }
}
```

✔ Same method name
✔ Different parameters
✔ Resolved at **compile time**

---

## 3.2 Runtime Polymorphism (Method Overriding)

```java
class Animal {
    void sound() {
        System.out.println("Animal sound");
    }
}

class Dog extends Animal {
    @Override
    void sound() {
        System.out.println("Bark");
    }
}

Animal a = new Dog();
a.sound();   // Bark
```

✔ Resolved at **runtime**
✔ Uses **dynamic method dispatch**

---

## 3.3 Rules of Method Overriding

* Method signature must be same
* Access modifier cannot be reduced
* `final` methods cannot be overridden
* `static` methods cannot be overridden (method hiding)

---

## 4️⃣ Abstraction + Inheritance + Polymorphism (Together)

```java
abstract class Shape {
    abstract void draw();
}

class Circle extends Shape {
    void draw() {
        System.out.println("Drawing Circle");
    }
}

Shape s = new Circle();
s.draw();
```

```
Abstraction → Shape
Inheritance → Circle extends Shape
Polymorphism → Shape ref = Circle obj
```

---

## 5️⃣ Interview Comparison Table

| Concept      | Purpose          |
| ------------ | ---------------- |
| Abstraction  | Hide complexity  |
| Inheritance  | Code reuse       |
| Polymorphism | Dynamic behavior |

---

## ⭐ Additional Interview Points

* Polymorphism depends on **inheritance**
* Abstraction enables **loose coupling**
* Prefer **composition over inheritance**
* Interfaces are widely used in **Spring, APIs**


🚀 **End of 12-13 Jan Session**
