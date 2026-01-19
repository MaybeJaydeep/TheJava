# Loops in Java – Complete Guide (No Diagrams)

This document explains **all types of loops in Java** clearly and practically. It focuses on **how loops work, when to use them, common mistakes, and best practices**, aligned with Java documentation and real-world coding.

---

## What is a Loop?

A **loop** allows you to execute a block of code **repeatedly** based on a condition.

Why loops exist:

* Avoid code repetition
* Handle collections and ranges
* Automate repetitive tasks

---

## 1️⃣ for Loop

### What is a for loop?

A **for loop** is used when the **number of iterations is known beforehand**.

### Syntax

```java
for (initialization; condition; increment/decrement) {
    // code block
}
```

### Example

```java
for (int i = 1; i <= 5; i++) {
    System.out.println(i);
}
```

### Execution Flow

1. Initialization runs once
2. Condition is checked
3. Body executes if condition is true
4. Increment/decrement happens
5. Steps 2–4 repeat

### When to use for loop

* Fixed number of iterations
* Index-based access (arrays)
* Performance-critical loops

### Common Mistakes

* Infinite loop due to missing increment
* Off-by-one errors (`<` vs `<=`)

---

## 2️⃣ while Loop

### What is a while loop?

A **while loop** executes as long as the condition is **true**.

### Syntax

```java
while (condition) {
    // code block
}
```

### Example

```java
int i = 1;
while (i <= 5) {
    System.out.println(i);
    i++;
}
```

### Key Point

* Condition is checked **before** execution
* Loop may execute **zero times**

### When to use while loop

* Unknown number of iterations
* Condition-driven logic
* Reading input until condition changes

### Common Mistakes

* Forgetting to update condition variable
* Infinite loops

---

## 3️⃣ do-while Loop

### What is a do-while loop?

A **do-while loop** guarantees **at least one execution**, even if the condition is false.

### Syntax

```java
do {
    // code block
} while (condition);
```

### Example

```java
int i = 1;
do {
    System.out.println(i);
    i++;
} while (i <= 5);
```

### Key Difference from while

* Condition is checked **after execution**

### When to use do-while

* Menu-driven programs
* User input validation
* Scenarios where at least one run is required

### Common Mistake

* Forgetting semicolon after `while(condition);`

---

## 4️⃣ Enhanced for Loop (for-each loop)

### What is Enhanced for loop?

Introduced in **Java 5**, the enhanced for loop is used to iterate over **arrays and collections** without using indexes.

### Syntax

```java
for (dataType variable : collection) {
    // code block
}
```

### Example (Array)

```java
int[] nums = {1, 2, 3, 4};
for (int num : nums) {
    System.out.println(num);
}
```

### Example (Collection)

```java
List<String> names = List.of("A", "B", "C");
for (String name : names) {
    System.out.println(name);
}
```

### Advantages

* Clean and readable
* No index handling
* Reduces errors

### Limitations

* Cannot modify collection structure
* No access to index
* Cannot iterate backward

---

## 🔍 Comparison Table

| Loop Type    | Condition Check | Guaranteed Execution | Best Use Case    |
| ------------ | --------------- | -------------------- | ---------------- |
| for          | Before          | ❌ No                 | Known iterations |
| while        | Before          | ❌ No                 | Condition-based  |
| do-while     | After           | ✅ Yes                | At least one run |
| enhanced for | N/A             | Depends              | Collections      |

---

## ⚠️ Infinite Loop Example

```java
while (true) {
    // runs forever
}
```

Used intentionally in:

* Servers
* Event listeners
* Background services

---

## ✅ Best Practices

* Use `for` when iteration count is known
* Use `while` for condition-driven loops
* Use `do-while` for menus and validations
* Use enhanced for for **read-only traversal**
* Avoid nested loops unless necessary

---

## 🧠 Interview One-Liners

* for → count-based loop
* while → condition-based loop
* do-while → executes at least once
* enhanced for → clean iteration over collections

---

## 📌 Summary

Loops are fundamental control structures in Java. Choosing the right loop improves:

* Readability
* Performance
* Maintainability

> "Use the simplest loop that clearly expresses intent"

---

