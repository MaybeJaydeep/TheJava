# Java `sealed` Keyword – Complete Notes (Java 17+)

This document explains the **`sealed` keyword in Java** in depth, including:

* Why it was introduced
* How it works
* Rules & syntax
* Advantages
* Real-world usage
* Comparison with `final` and normal inheritance

These notes are aligned with **Java official design goals**.

---

## 1️⃣ What is the `sealed` Keyword?

The `sealed` keyword is used to **restrict which classes or interfaces can extend or implement a class/interface**.

In simple terms:

> **`sealed` = controlled inheritance**

It allows the designer to explicitly declare **who is allowed to inherit**.

Introduced in:

* Java 15 (preview)
* Java 17 (stable, LTS)

---

## 2️⃣ Why Was `sealed` Introduced?

Before `sealed`, Java had only two extremes:

* ❌ Fully open inheritance (anyone can extend)
* ❌ Completely closed inheritance (`final`)

There was **no middle ground**.

### Problems Before `sealed`

* Inheritance misuse
* Breaking encapsulation
* Unsafe subclassing
* Difficult exhaustiveness checking (switch)

### Solution

`sealed` gives **controlled extensibility**.

---

## 3️⃣ Basic Syntax

```java
public sealed class Shape
    permits Circle, Rectangle, Triangle {
}
```

### Key Points

* `sealed` class **must** declare `permits`
* Only listed classes can extend it

---

## 4️⃣ Rules for Permitted Subclasses

Every subclass must be **one of these**:

1. `final`
2. `sealed`
3. `non-sealed`

### Example

```java
public sealed class Shape permits Circle, Rectangle {}

public final class Circle extends Shape {}

public non-sealed class Rectangle extends Shape {}
```

Why this rule exists:

* Ensures compiler always knows the inheritance boundaries

---

## 5️⃣ `non-sealed` Keyword

`non-sealed` explicitly **opens inheritance again**.

```java
public non-sealed class Rectangle extends Shape {}
```

Meaning:

* Rectangle can be extended by **any class**

---

## 6️⃣ Sealed Interfaces

Interfaces can also be sealed.

```java
public sealed interface Payment
    permits UPI, Card, Cash {
}
```

```java
public final class UPI implements Payment {}
```

Use-case:

* Defining **finite set of behaviors**

---

## 7️⃣ Advantages of `sealed`

### ✅ Controlled Inheritance

* Prevents unauthorized subclassing

### ✅ Better Domain Modeling

* Represents **closed-world hierarchies**

### ✅ Compile-Time Safety

* Compiler knows **all possible subtypes**

### ✅ Safer `switch` Expressions

```java
return switch (shape) {
    case Circle c -> 1;
    case Rectangle r -> 2;
}; // no default needed
```

### ✅ Improves Maintainability

* Prevents breaking changes

---

## 8️⃣ `sealed` vs `final`

| Feature                 | sealed    | final |
| ----------------------- | --------- | ----- |
| Allows inheritance      | ✅ Limited | ❌ No  |
| Control over subclasses | ✅ Yes     | ❌ No  |
| Extensible hierarchy    | ✅ Yes     | ❌ No  |
| Suitable for frameworks | ✅ Yes     | ❌ No  |

---

## 9️⃣ `sealed` vs Normal Class

| Feature            | Normal Class | Sealed Class   |
| ------------------ | ------------ | -------------- |
| Who can extend     | Anyone       | Only permitted |
| Compiler knowledge | Open-ended   | Closed set     |
| Safety             | Low          | High           |

---

## 🔟 Real-World Use Cases

### 1. Payment Systems

```java
sealed interface Payment permits Card, UPI, Cash {}
```

### 2. Expression Trees

```java
sealed interface Expr permits Add, Multiply, Number {}
```

### 3. State Machines

```java
sealed interface State permits Started, Running, Stopped {}
```

### 4. API Design

* Prevents misuse by library consumers

---

## 1️⃣1️⃣ When NOT to Use `sealed`

* When extension by users is expected
* Plugin-based systems
* Open frameworks

---

## 1️⃣2️⃣ Interview One-Liners

* "`sealed` provides controlled inheritance"
* "It bridges the gap between `final` and open inheritance"
* "Helps compiler with exhaustiveness checks"
* "Improves domain-driven design"

---

## 1️⃣3️⃣ Common Interview Questions

**Q: Can a sealed class extend another sealed class?**
Yes, but it must be listed in `permits`.

**Q: Can sealed classes be abstract?**
Yes, most sealed classes are abstract.

**Q: Is `permits` mandatory?**
Yes, unless subclasses are in the same file.

---

## 📌 Summary

* `sealed` = restricted inheritance
* Introduced in Java 17 (stable)
* Improves safety, design, and maintainability
* Works best with `switch` expressions

> Use `sealed` when **you know all valid subtypes upfront**

---

✅ Suitable for **Java notes, documentation, and advanced design discussions**
