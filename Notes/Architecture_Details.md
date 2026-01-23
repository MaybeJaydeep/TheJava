# Components of JVM, JRE, and JDK – Complete Notes

This document explains **JVM, JRE, and JDK** in depth, focusing on:

* What each one is
* Their internal components
* How they relate to each other
* What is used at runtime vs development time

These notes align with **How_Java_Works** notes deep dive.

---

## 1️⃣ JVM (Java Virtual Machine)

### What is JVM?

The **JVM** is the engine that **executes Java bytecode**. It provides:

* Platform independence
* Memory management
* Thread management
* Security

> JVM does NOT understand Java source code. It only understands **bytecode**.

---

## Components of JVM

### 1. Class Loader Subsystem

Responsible for loading `.class` files into memory.

**Types of Class Loaders:**

* **Bootstrap Class Loader** – loads core Java classes (`java.lang.*`)
* **Extension Class Loader** – loads extension libraries
* **Application Class Loader** – loads application-level classes

---

### 2. Bytecode Verifier

* Verifies bytecode correctness
* Ensures no illegal memory access
* Enforces access control rules

If verification fails → `VerifyError`

---

### 3. Runtime Data Areas (Memory Areas)

#### 🔹 Method Area

* Class metadata
* Static variables
* Method bytecode

#### 🔹 Heap

* Objects and instance variables
* Shared among all threads
* Managed by Garbage Collector

#### 🔹 Stack

* One stack per thread
* Stores method calls and local variables

#### 🔹 Program Counter (PC) Register

* Stores current instruction address
* One per thread

#### 🔹 Native Method Stack

* Used for native (C/C++) code

---

### 4. Execution Engine

Responsible for executing bytecode.

Includes:

* **Interpreter** – executes bytecode line by line
* **JIT Compiler** – converts hot bytecode into native code
* **Garbage Collector** – frees unused memory

---

### 5. Java Native Interface (JNI)

* Bridge between Java and native languages (C/C++)
* Enables OS-level operations

---

## 2️⃣ JRE (Java Runtime Environment)

### What is JRE?

The **JRE** provides everything required to **run Java applications**, but not to develop them.

> JRE = JVM + Libraries

---

## Components of JRE

### 1. JVM

* Core execution engine

### 2. Java Class Libraries

* Predefined APIs (`java.lang`, `java.util`, `java.io`, etc.)

### 3. Supporting Files

* Configuration files
* Property files

---

### What JRE Does NOT Contain

* Compiler (`javac`)
* Debugging tools
* Development utilities

---

## 3️⃣ JDK (Java Development Kit)

### What is JDK?

The **JDK** is a **complete Java development package**.

> JDK = JRE + Development Tools

---

## Components of JDK

### 1. JRE

* Used to run applications

### 2. Development Tools

Common tools:

* `javac` – Java compiler
* `java` – JVM launcher
* `javadoc` – Documentation generator
* `jar` – Packaging tool
* `jshell` – Interactive Java shell
* Debuggers and profilers

---

## 4️⃣ Relationship Between JVM, JRE, and JDK

```
JDK
 ├─ JRE
 │   ├─ JVM
 │   ├─ Core Libraries
 │   └─ Runtime Files
 └─ Development Tools
```

---

## 5️⃣ Key Differences (Interview Table)

| Feature            | JVM   | JRE   | JDK   |
| ------------------ | ----- | ----- | ----- |
| Executes bytecode  | ✅ Yes | ✅ Yes | ✅ Yes |
| Contains libraries | ❌ No  | ✅ Yes | ✅ Yes |
| Compiler included  | ❌ No  | ❌ No  | ✅ Yes |
| Used by developers | ❌ No  | ❌ No  | ✅ Yes |

---

## 6️⃣ Common Interview Questions

**Q: Can Java run without JDK?**
Yes, Java programs run using JRE.

**Q: Is JVM platform-dependent?**
Yes, JVM implementation is platform-dependent, but bytecode is platform-independent.

**Q: Why JDK is needed if JVM exists?**
JVM runs code, JDK creates code.

---

## 7️⃣ Interview One-Liners

* "JVM executes bytecode"
* "JRE runs Java applications"
* "JDK is for development"
* "Write once, run anywhere is achieved by JVM"

---

## 📌 Summary

* JVM executes Java bytecode
* JRE provides runtime environment
* JDK provides development tools
* JVM is a subset of JRE, JRE is a subset of JDK

> JVM is the heart, JRE is the body, JDK is the factory

---

✅ Suitable for **Java backend roles, and system-level discussions**
