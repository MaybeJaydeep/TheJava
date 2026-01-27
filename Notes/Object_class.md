# Java `Object` Class – Deep Dive Notes

This document provides **deep knowledge of `java.lang.Object`**, the **root class of Java**. Every Java class implicitly extends `Object`. Understanding this class is critical for **OOP design, collections, JVM behavior, and interviews**.

---

## 1️⃣ What is the `Object` Class?

`java.lang.Object` is the **top-most superclass** in Java.

```java
public class Object { ... }
```

### Key Facts

* Every class in Java directly or indirectly extends `Object`
* If no parent is specified, Java automatically extends `Object`

```java
class User { }
// internally becomes
class User extends Object { }
```

---

## 2️⃣ Why `Object` Class Exists

The `Object` class provides **common behavior** that every Java object must have:

* Identity
* Comparison
* String representation
* Thread coordination
* Cloning
* Finalization (legacy)

> `Object` ensures **uniform behavior across all Java objects**

---

## 3️⃣ Methods of Object Class (Complete List)

| Method           | Purpose                        |
| ---------------- | ------------------------------ |
| `toString()`     | String representation          |
| `equals(Object)` | Logical equality               |
| `hashCode()`     | Hash-based collections         |
| `getClass()`     | Runtime class info             |
| `clone()`        | Object cloning                 |
| `wait()`         | Thread communication           |
| `notify()`       | Wake one thread                |
| `notifyAll()`    | Wake all threads               |
| `finalize()`     | Cleanup before GC (deprecated) |

---

## 4️⃣ `toString()` – Deep Concept

### Default Behavior

```java
User@6d03e736
```

Internally:

```
ClassName@HexHashCode
```

### Why Override `toString()`?

* Better debugging
* Meaningful logs

```java
@Override
public String toString() {
    return "User{name='John'}";
}
```

---

## 5️⃣ `equals()` – Logical Equality

### Default Implementation

```java
public boolean equals(Object obj) {
    return this == obj;
}
```

* Compares **memory references**

### Correct Override Pattern

```java
@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return id == user.id;
}
```

### Common Mistakes

* Not overriding `hashCode()`
* Using `==` for object comparison

---

## 6️⃣ `hashCode()` – Contract & Collections

### Contract

1. Equal objects must have same hashCode
2. Same hashCode does NOT guarantee equality

### Why Important?

* Used by `HashMap`, `HashSet`

```java
@Override
public int hashCode() {
    return Objects.hash(id);
}
```

---

## 7️⃣ `equals()` + `hashCode()` Contract (Interview Favorite)

```
if a.equals(b) == true
then a.hashCode() == b.hashCode()
```

Violation leads to **broken collections behavior**.

---

## 8️⃣ `getClass()` – Runtime Type Information

```java
obj.getClass().getName();
```

* Returns actual runtime class
* Used in reflection and frameworks

---

## 9️⃣ `clone()` – Shallow vs Deep Copy

### Rules

* Class must implement `Cloneable`
* Otherwise → `CloneNotSupportedException`

```java
@Override
protected Object clone() throws CloneNotSupportedException {
    return super.clone();
}
```

### Shallow Copy

* Copies references

### Deep Copy

* Copies actual objects

---

## 🔟 Thread Methods: `wait()`, `notify()`, `notifyAll()`

### Why in Object class?

Because **every object can be used as a monitor lock**.

```java
synchronized(obj) {
    obj.wait();
}
```

### Key Rules

* Must be called inside synchronized block
* Used for inter-thread communication

---

## 1️⃣1️⃣ `finalize()` (Deprecated)

* Called before garbage collection
* Unpredictable execution
* Deprecated since Java 9

❌ Avoid using it

---

## 1️⃣2️⃣ Object Class & JVM Relationship

* Object identity → Heap memory
* Reference → Stack
* `hashCode()` often derived from memory

---

## 1️⃣3️⃣ Object Class vs Wrapper Classes

| Object                   | Wrapper         |
| ------------------------ | --------------- |
| Root of all classes      | Wrap primitives |
| Supports synchronization | Immutable       |

---

## 1️⃣4️⃣ Common Interview Questions

**Q: Why wait/notify in Object and not Thread?**
A: Any object can act as a monitor.

**Q: Can we override `getClass()`?**
A: No, it is final.

**Q: Why `hashCode()` is needed?**
A: For hash-based collections.

---

## 1️⃣5️⃣ Interview One-Liners

* "Every class extends Object"
* "equals and hashCode go together"
* "wait/notify are monitor methods"
* "Object is the root of Java hierarchy"

---

## 📌 Summary

* `Object` is the backbone of Java OOP
* Its methods enable identity, equality, concurrency
* Incorrect overrides cause serious bugs

> Mastering `Object` class = mastering Java fundamentals

---

✅ Suitable for **Java backend development, and framework-level understanding**
