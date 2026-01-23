# JVM Architecture & How Java Code Executes (Deep Dive)

This document explains the **deep internal concepts of the JVM (Java Virtual Machine)** and **what happens behind the scenes when Java code runs**. It is written for:

* Backend / JVM interviews
* System-level understanding
* Advanced Java learners

---

## 1️⃣ What is JVM?

The **Java Virtual Machine (JVM)** is an abstract machine that:

* Executes Java bytecode
* Provides platform independence
* Manages memory, threads, and security

> Java is *compiled*, but execution is handled by the JVM.

---

## 2️⃣ Java Execution Flow (High-Level)

```
.java file
   ↓ (javac)
.class (Bytecode)
   ↓
Class Loader
   ↓
Runtime Data Areas
   ↓
Execution Engine
   ↓
Native OS / Hardware
```

---

## 3️⃣ Step-by-Step: How Java Code Executes

### Step 1: Compilation

```bash
javac Hello.java
```

* Java compiler converts source code into **bytecode**
* Bytecode is **platform-independent**

---

### Step 2: Class Loading

Handled by the **Class Loader Subsystem**.

Types of class loaders:

1. **Bootstrap Class Loader**

   * Loads core Java classes (`java.lang.*`)

2. **Extension Class Loader**

   * Loads extension libraries

3. **Application Class Loader**

   * Loads application classes

---

### Step 3: Bytecode Verification

* Ensures bytecode is safe
* Prevents memory violations
* Enforces access rules

If verification fails → `VerifyError`

---

## 4️⃣ JVM Runtime Data Areas (Memory Model)

### 🔹 Method Area

* Stores class metadata
* Method bytecode
* Static variables

---

### 🔹 Heap

* Stores **objects & instance variables**
* Shared across threads
* Managed by Garbage Collector

---

### 🔹 Stack

* One stack per thread
* Stores method calls & local variables

```
Stack Frame
 ├─ Local Variables
 ├─ Operand Stack
 └─ Return Address
```

---

### 🔹 Program Counter (PC) Register

* Holds address of current instruction
* One per thread

---

### 🔹 Native Method Stack

* Used for native (C/C++) methods

---

## 5️⃣ Execution Engine (Where Code Actually Runs)

The **Execution Engine** executes bytecode using:

### 1. Interpreter

* Reads bytecode line-by-line
* Slow but quick startup

### 2. JIT Compiler (Just-In-Time)

* Compiles frequently used bytecode into native machine code
* Improves performance

### 3. Garbage Collector

* Frees unused objects
* Runs automatically

---

## 6️⃣ JIT Compilation (Deep Concept)

* JVM identifies **hot methods**
* Converts them into native code
* Caches optimized code

This is why:

> Java gets faster over time

---

## 7️⃣ Garbage Collection (Behind the Scenes)

### Heap Structure

```
Heap
 ├─ Young Generation
 │   ├─ Eden
 │   ├─ Survivor S0
 │   └─ Survivor S1
 └─ Old Generation
```

* Minor GC → Young Gen
* Major GC → Old Gen

---

## 8️⃣ How JVM Handles Threads

* Each thread has:

  * Stack
  * PC register

* Heap & Method Area are shared

Concurrency is managed at JVM + OS level

---

## 9️⃣ Native Interface (JNI)

* Allows Java to call native code (C/C++)
* Used for OS-level operations

---

## 🔟 JVM vs JRE vs JDK

| Component | Purpose           |
| --------- | ----------------- |
| JVM       | Executes bytecode |
| JRE       | JVM + libraries   |
| JDK       | JRE + tools       |

---

## 1️⃣1️⃣ What Happens When `main()` Runs?

```java
public static void main(String[] args) {
    System.out.println("Hello");
}
```

Execution:

1. Class loaded
2. Static blocks executed
3. `main()` pushed to stack
4. Bytecode interpreted/JIT compiled
5. Objects created in heap

---

## 1️⃣2️⃣ Common JVM Errors (Interview)

* `OutOfMemoryError`
* `StackOverflowError`
* `ClassNotFoundException`
* `NoClassDefFoundError`

---

## 1️⃣3️⃣ One-Liners

* "JVM executes bytecode, not Java source"
* "Heap is shared, stack is per thread"
* "JIT improves performance dynamically"
* "GC runs automatically"

---

## 📌 Summary

* Java is compiled + interpreted
* JVM provides portability & memory management
* Execution engine + JIT make Java fast
* JVM architecture is key for backend roles

> Understanding JVM internals separates **Java users** from **Java engineers**

---

✅ Suitable for **advanced Java backend roles, and system design discussions**
