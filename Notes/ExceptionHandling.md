# Java Exception Handling – Complete Notes

This document explains **Exception Handling in Java** in a clear, conceptual way. It covers:

* What exceptions are
* Exception hierarchy
* Checked vs unchecked exceptions
* Keywords: `try`, `catch`, `finally`, `throw`, `throws`
* Custom exceptions
* Best practices

---

## 1 What is an Exception?

An **exception** is an **unexpected event** that disrupts the normal flow of program execution.

Examples:

* Dividing by zero
* Accessing an invalid array index
* File not found
* Database connection failure

### Simple Definition

> An exception is a runtime problem that can be handled.

---

## 2 Why Exception Handling is Needed

* Prevent program crash
* Separate error-handling logic from business logic
* Provide meaningful error messages
* Graceful recovery

Without exception handling:

```java
int a = 10 / 0; // Program crashes
```

With exception handling:

```java
try {
    int a = 10 / 0;
} catch (ArithmeticException e) {
    System.out.println("Cannot divide by zero");
}
```

---

## 3 Exception Hierarchy (Core Concept)

All exceptions in Java inherit from `java.lang.Throwable`.

### Hierarchy (ASCII Diagram)

```
                Throwable
                    |
        ---------------------------
        |                         |
      Error                    Exception
                                 |
                ---------------------------------
                |                               |
       Checked Exception              RuntimeException
```

---

## 4 Error vs Exception

### 🔴 Error

* Serious problems
* Not meant to be handled
* Caused by JVM

Examples:

* `OutOfMemoryError`
* `StackOverflowError`

### 🟢 Exception

* Can be handled by application
* Recoverable

---

## 5 Checked vs Unchecked Exceptions

### Checked Exceptions

* Checked at **compile time**
* Must be handled using `try-catch` or `throws`

Examples:

* `IOException`
* `SQLException`

### Unchecked Exceptions

* Occur at **runtime**
* Extend `RuntimeException`

Examples:

* `NullPointerException`
* `ArithmeticException`
* `ArrayIndexOutOfBoundsException`

### Comparison Table

| Feature                 | Checked   | Unchecked        |
| ----------------------- | --------- | ---------------- |
| Checked at compile time | ✅ Yes     | ❌ No             |
| Must handle             | ✅ Yes     | ❌ No             |
| Inherits from           | Exception | RuntimeException |

---

## 6 `try` and `catch`

Used to **handle exceptions**.

```java
try {
    int result = 10 / 0;
} catch (ArithmeticException e) {
    System.out.println(e.getMessage());
}
```

### Multiple catch blocks

```java
try {
    String s = null;
    s.length();
} catch (NullPointerException e) {
    System.out.println("Null value");
} catch (Exception e) {
    System.out.println("Generic exception");
}
```

---

## 7 `finally` Block

* Executes **always** (exception or not)
* Used for resource cleanup

```java
try {
    int a = 10 / 2;
} catch (Exception e) {
    System.out.println("Error");
} finally {
    System.out.println("Cleanup done");
}
```

⚠️ `finally` does NOT execute only in extreme cases (JVM crash).

---

## 8 `throw` Keyword

Used to **explicitly throw an exception**.

```java
if (age < 18) {
    throw new IllegalArgumentException("Underage");
}
```

---

## 9 `throws` Keyword

Used to **declare exceptions** that a method may pass to the caller.

```java
void readFile() throws IOException {
    // risky code
}
```

Caller must handle it.

---

## 10 Custom Exceptions

Used for **domain-specific errors**.

```java
class InvalidBalanceException extends Exception {
    public InvalidBalanceException(String msg) {
        super(msg);
    }
}
```

Usage:

```java
throw new InvalidBalanceException("Low balance");
```

---

## 11 `try-with-resources`

Introduced in Java 7.

Automatically closes resources.

```java
try (FileInputStream fis = new FileInputStream("a.txt")) {
    // use file
}
```

Resource must implement `AutoCloseable`.

---

## 12 Best Practices

* Catch specific exceptions first
* Never swallow exceptions
* Use meaningful messages
* Do not use exceptions for flow control
* Always clean resources

---

## 1️3 One-Liners

* "All exceptions inherit from Throwable"
* "Checked exceptions are compile-time checked"
* "RuntimeException represents programming bugs"
* "finally is used for cleanup"

---

## 📌 Summary

* Exception handling prevents crashes
* Java has a strict exception hierarchy
* Checked vs unchecked is a key interview topic
* Custom exceptions improve clarity

> Handle exceptions to make systems **robust and maintainable**

---

✅ Suitable for **Java interviews, exams, and production-level understanding**
