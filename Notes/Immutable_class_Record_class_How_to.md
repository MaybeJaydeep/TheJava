# Java Immutable Class & `record` – Complete Notes

This document explains **Immutable Classes in Java** and **Java `record`**, covering:

* Why immutability matters
* Role of `private`, `final`, and `static`
* How to design an immutable class
* What `record` is and why it exists
* Differences between immutable classes and records

These notes are **interview-ready** and aligned with **modern Java (Java 8 → Java 21)** practices.

---

## 1️⃣ What is an Immutable Class?

An **immutable class** is a class whose **object state cannot be changed after creation**.

Once created:

* ❌ Fields cannot be modified
* ❌ No setters
* ✅ Thread-safe by design

### Simple Definition

> If an object’s data never changes, the object is immutable.

---

## 2️⃣ Why Immutability is Important

### ✅ Thread Safety

* No synchronization needed
* Safe in concurrent environments

### ✅ Security

* State cannot be altered unexpectedly

### ✅ Predictability

* No side effects
* Easier debugging

### ✅ Better Caching & Performance

* Objects can be reused safely

---

## 3️⃣ Rules to Create an Immutable Class

To make a class immutable, follow **all rules strictly**.

### Rule 1: Make the Class `final`

```java
public final class User {
}
```

**Why?**

* Prevents subclassing
* Subclasses could add mutability

---

### Rule 2: Make Fields `private final`

```java
private final String name;
private final int age;
```

**Why `private`?**

* Prevents direct access

**Why `final`?**

* Value assigned only once

---

### Rule 3: No Setters

❌ This breaks immutability:

```java
public void setName(String name) {
    this.name = name;
}
```

---

### Rule 4: Initialize via Constructor Only

```java
public User(String name, int age) {
    this.name = name;
    this.age = age;
}
```

---

### Rule 5: Defensive Copy for Mutable Fields

```java
private final List<String> roles;

public User(List<String> roles) {
    this.roles = new ArrayList<>(roles);
}

public List<String> getRoles() {
    return new ArrayList<>(roles);
}
```

**Why?**

* Prevents external modification

---

## 4️⃣ Role of `static`, `final`, `private` in Immutability

### 🔹 `private`

* Encapsulates state
* Prevents external modification

### 🔹 `final`

* Prevents reassignment
* Prevents inheritance (for class)

### 🔹 `static`

* Belongs to class, not object
* Safe for constants

```java
public static final String TYPE = "ADMIN";
```

---

## 5️⃣ Complete Immutable Class Example

```java
public final class Account {
    private final String id;
    private final double balance;

    public Account(String id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }
}
```

---

## 6️⃣ What is a Java `record`?

Introduced in **Java 16 (stable)**, a `record` is a **compact syntax for immutable data carriers**.

### Definition

> A `record` is a special class designed to hold data immutably.

---

## 7️⃣ Record Syntax

```java
public record User(String name, int age) {}
```

### What Java Automatically Generates

* `private final` fields
* Constructor
* Getters (accessors)
* `equals()`
* `hashCode()`
* `toString()`

---

## 8️⃣ Why Records Were Introduced

Before records:

* Too much boilerplate

```java
class User {
  private final String name;
  private final int age;
}
```

After records:

```java
record User(String name, int age) {}
```

---

## 9️⃣ Are Records Immutable?

### ✔️ Shallow Immutability

* Fields are `final`
* References cannot change

### ❌ Not Deeply Immutable

```java
record Data(List<String> values) {}
```

* `values` reference is final
* But list contents can change

➡️ Defensive copying still required

---

## 🔟 Custom Logic in Records

### Compact Constructor

```java
public record User(String name, int age) {
    public User {
        if (age < 18) throw new IllegalArgumentException();
    }
}
```

### Custom Methods

```java
public String greeting() {
    return "Hello " + name;
}
```

---

## 1️⃣1️⃣ Records vs Immutable Class

| Feature     | Immutable Class    | Record       |
| ----------- | ------------------ | ------------ |
| Boilerplate | High               | Very Low     |
| Inheritance | ❌ No               | ❌ No         |
| Mutability  | Deep (if designed) | Shallow      |
| Use case    | Business logic     | Data carrier |

---

## 1️⃣2️⃣ When to Use What

### Use Immutable Class When:

* Complex behavior
* Strong encapsulation
* Business logic

### Use Record When:

* DTOs
* API responses
* Configuration holders

---

## 1️⃣3️⃣ Interview One-Liners

* "Immutability ensures thread safety"
* "Records reduce boilerplate"
* "Records are shallowly immutable"
* "Use immutable classes for behavior, records for data"

---

## 📌 Summary

* Immutable classes prevent state changes
* `private`, `final`, `static` are core to immutability
* Records are concise immutable data carriers
* Records are not a replacement for all classes

> Use **immutability by default**, mutability only when required

---

✅ Suitable for **Java interviews, notes, and modern Java design**
