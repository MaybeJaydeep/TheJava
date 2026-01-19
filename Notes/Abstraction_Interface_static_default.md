# Abstract Class and Interface in Java

This document explains **Abstract Classes** and **Interfaces** in Java, based on Java Docs and real-world design practices. It also covers **default, static, private, and abstract methods**, including why they exist and when to use them.

---

## 1. Abstract Class

### What is an Abstract Class?

An **abstract class** is a class that **cannot be instantiated** and may contain:

* Abstract methods (without body)
* Concrete methods (with body)
* Instance variables
* Constructors

It represents an **"is-a" relationship** and is used when classes share **common behavior + partial implementation**.

### Syntax

```java
abstract class Bank {
    abstract void getInterestRate();

    void displayBankType() {
        System.out.println("This is a Bank");
    }
}
```

### Key Rules

* Can have **abstract and non-abstract methods**
* Can have **constructors**
* Can have **protected, default, private methods**
* A class can extend **only one abstract class**

---

## 2. Interface

### What is an Interface?

An **interface** is a **pure contract / blueprint** that defines **what a class must do**, not how.

Before Java 8 → only abstract methods
After Java 8 → supports default, static, private methods

### Syntax

```java
interface BankService {
    void openAccount();
}
```

### Key Rules

* Methods are **public by default**
* Variables are **public static final**
* A class can implement **multiple interfaces**

---

## 3. Why Interface Can Implement (Backward Compatibility)

### Problem Before Java 8

If you add a new method to an interface:

```java
interface BankService {
    void openAccount();
    void closeAccount(); // added later
}
```

➡ All existing classes **break** and must implement it.

### Solution (Java 8)

Java introduced:

* `default` methods
* `static` methods

This allows **adding new functionality without breaking existing code**.

---

## 4. default Methods in Interface

### Why default?

* To provide a **default implementation**
* To maintain **backward compatibility**
* Acts like a **blueprint with optional behavior**

### Example

```java
interface BankService {
    void openAccount();

    default void kyc() {
        System.out.println("Basic KYC Done");
    }
}
```

### Rules

* Can be overridden
* Cannot access instance variables
* Used for **shared logic**

---

## 5. static Methods in Interface

### Why static?

* For **utility/helper methods**
* Belongs to interface, not implementing class
* Provides **standard format or rule**

### Example

```java
interface BankUtils {
    static boolean validateIFSC(String ifsc) {
        return ifsc.length() == 11;
    }
}
```

### Usage

```java
BankUtils.validateIFSC("SBIN0001234");
```

### Rules

* Cannot be overridden
* Called using **interface name**

---

## 6. private Methods in Interface (Java 9+)

### Why private?

* To **hide sensitive logic**
* Avoid code duplication inside default methods
* Similar to internal helper methods

> ❌ Not for DB connection directly, but for **hiding sensitive internal logic**

### Example

```java
interface BankService {

    default void withdraw() {
        logTransaction();
        System.out.println("Withdrawal Done");
    }

    private void logTransaction() {
        System.out.println("Transaction Logged Securely");
    }
}
```

### Rules

* Cannot be accessed by implementing classes
* Used only inside interface

---

## 7. abstract Methods

### In Abstract Class

```java
abstract class Account {
    abstract void calculateInterest();
}
```

### In Interface

```java
interface AccountService {
    void calculateInterest(); // abstract by default
}
```

### Rule

* **Must be implemented** by subclass or implementing class

---

## 8. Abstract Class vs Interface (Comparison)

| Feature              | Abstract Class      | Interface                          |
| -------------------- | ------------------- | ---------------------------------- |
| Multiple Inheritance | ❌ No                | ✅ Yes                              |
| Method Types         | Abstract + Concrete | Abstract, default, static, private |
| Constructors         | ✅ Yes               | ❌ No                               |
| State (variables)    | ✅ Yes               | ❌ Constants only                   |
| Access Modifiers     | All                 | public only (methods)              |

---

## 9. Best Practices

### Use Abstract Class When:

* Classes share **common state and behavior**
* You want to provide **base functionality**

### Use Interface When:

* You want **multiple inheritance**
* You are defining **capability/contract**
* You expect frequent evolution (default methods help)

---

## 10. Real-World Analogy

* **Interface** → Company Policy (What must be done)
* **Abstract Class** → Training Manual (Some steps already defined)
* **Concrete Class** → Employee (Actual implementation)

---

## 11. Summary

* `default` → backward compatibility + shared behavior
* `static` → utility / fixed rules
* `private` → hide internal logic
* `abstract` → force implementation

> Interfaces define **what**, Abstract classes define **what + how (partially)**

---

