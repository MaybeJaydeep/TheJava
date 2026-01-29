# Java File I/O & Input Reading – Deep Dive Notes

This document provides a **deep, practical, understanding** of **Java File I/O** and **input reading mechanisms**, with a strong focus on:

* `BufferedReader`
* `Scanner`
* Other I/O approaches
* Performance & optimization considerations

These notes are suitable for **backend development, competitive programming, and system-level interviews**.

---

## 1️⃣ What is I/O in Java?

I/O (Input/Output) in Java refers to **reading data from a source** and **writing data to a destination**.

### Common Sources

* Keyboard (stdin)
* Files
* Network streams

### Common Destinations

* Console
* Files
* Network sockets

---

## 2️⃣ Java I/O Package Overview

Main packages:

* `java.io` → Stream-based (byte & character)
* `java.nio` / `java.nio.file` → Non-blocking, buffer-based

---

## 3️⃣ Byte Streams vs Character Streams

### Byte Streams

* Handle raw binary data
* Classes end with `InputStream` / `OutputStream`

Examples:

* `FileInputStream`
* `BufferedInputStream`

### Character Streams

* Handle text data
* Classes end with `Reader` / `Writer`

Examples:

* `FileReader`
* `BufferedReader`

> For text → use **character streams**

---

## 4️⃣ File Reading Using `BufferedReader`

### Why `BufferedReader`?

* Reads data in **chunks (buffer)** instead of char-by-char
* Reduces system calls
* Much faster than `FileReader` alone

### Example

```java
BufferedReader br = new BufferedReader(new FileReader("data.txt"));
String line;
while ((line = br.readLine()) != null) {
    System.out.println(line);
}
br.close();
```

### How it works internally

```
File → OS Buffer → JVM Buffer → Application
```

---

## 5️⃣ Reading Input Using `Scanner`

### What is `Scanner`?

`Scanner` parses input using **regular expressions**.

### Example

```java
Scanner sc = new Scanner(System.in);
int x = sc.nextInt();
String name = sc.next();
```

### Pros

* Easy to use
* Built-in parsing

### Cons (Very Important)

* Slower than `BufferedReader`
* Regex overhead
* Not suitable for large input

---

## 6️⃣ `BufferedReader` vs `Scanner` (Deep Comparison)

| Feature                 | BufferedReader | Scanner   |
| ----------------------- | -------------- | --------- |
| Speed                   | Very fast      | Slow      |
| Parsing                 | Manual         | Automatic |
| Regex                   | ❌ No           | ✅ Yes     |
| Memory                  | Low            | Higher    |
| Competitive Programming | ✅ Yes          | ❌ No      |

---

## 7️⃣ Reading from Console (stdin)

### Using `InputStreamReader`

```java
BufferedReader br = new BufferedReader(
    new InputStreamReader(System.in)
);
```

Used when:

* Reading console input efficiently
* Large input size

---

## 8️⃣ File Writing in Java

### Using `BufferedWriter`

```java
BufferedWriter bw = new BufferedWriter(new FileWriter("out.txt"));
bw.write("Hello");
bw.newLine();
bw.close();
```

### Why BufferedWriter?

* Fewer disk writes
* Better performance

---

## 9️⃣ Other Input Approaches

### 🔹 `DataInputStream`

* Reads primitive types
* Faster than Scanner

```java
DataInputStream dis = new DataInputStream(System.in);
```

---

### 🔹 `Console` Class

```java
Console console = System.console();
String pwd = console.readPassword();
```

* Secure input (passwords)
* Not supported in IDE consoles

---

### 🔹 `Files` API (NIO)

```java
List<String> lines = Files.readAllLines(Path.of("data.txt"));
```

Pros:

* Clean syntax

Cons:

* Loads entire file into memory

---

## 🔟 Performance & Optimization (Very Important)

### 1. Prefer Buffered Streams

* Always wrap streams with buffering

### 2. Avoid Scanner for Large Input

* Use `BufferedReader` instead

### 3. Use `StringBuilder` for Concatenation

```java
StringBuilder sb = new StringBuilder();
```

### 4. Use try-with-resources

```java
try (BufferedReader br = new BufferedReader(new FileReader("a.txt"))) {
}
```

### 5. Choose Right API

| Scenario         | Best Choice    |
| ---------------- | -------------- |
| Large text input | BufferedReader |
| Simple input     | Scanner        |
| Binary data      | InputStream    |
| Config files     | Files API      |

---

## 1️⃣1️⃣ Common Interview Questions

**Q: Why BufferedReader is faster than Scanner?**
A: Scanner uses regex parsing, BufferedReader reads raw text.

**Q: Why buffering improves performance?**
A: Reduces I/O system calls.

**Q: Can Scanner be avoided in backend apps?**
A: Yes, it is usually avoided for performance reasons.

---

## 1️⃣2️⃣ One-Liners

* "BufferedReader is faster due to buffering"
* "Scanner trades performance for convenience"
* "Always close streams"
* "Choose I/O based on data size"

---

## 📌 Summary

* Java provides multiple I/O mechanisms
* Buffered streams are performance-friendly
* Scanner is convenient but slow
* Optimization depends on use case

> Good I/O decisions directly impact application performance

---

✅ Suitable for **Java backend systems, and performance-critical applications**
