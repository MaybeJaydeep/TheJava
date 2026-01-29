# Java IO vs NIO – Deep Dive (Blocking vs Non-Blocking)

This document provides a **deep, system-level comparison of Java IO and Java NIO**, explaining **how data flows internally, why NIO exists, and when to use which**. This topic is extremely important for **backend, high-performance systems, and interviews**.

---

## 1️⃣ What is Java IO?

**Java IO (`java.io`)** is the traditional I/O model based on **streams**.

### Core Characteristics

* Blocking I/O
* Stream-based (byte or character)
* One thread per I/O operation

### Example

```java
InputStream is = new FileInputStream("data.txt");
int data = is.read(); // blocks until data is available
```

---

## 2️⃣ What is Java NIO?

**Java NIO (`java.nio`)** was introduced to support **high-performance, scalable I/O**.

### Core Characteristics

* Non-blocking I/O
* Buffer-based
* Supports multiplexing (Selector)

### Example

```java
ByteBuffer buffer = ByteBuffer.allocate(1024);
channel.read(buffer); // non-blocking
```

---

## 3️⃣ Blocking vs Non-Blocking (Core Difference)

### Java IO (Blocking)

```
Thread → read() → waits → data arrives → continues
```

### Java NIO (Non-Blocking)

```
Thread → check channel → no data → do other work
```

---

## 4️⃣ Architecture Comparison

### Java IO Architecture

```
Application
   |
 Stream (InputStream / Reader)
   |
 OS Buffer
   |
 Disk / Network
```

---

### Java NIO Architecture

```
Application
   |
 Channel
   |
 Buffer
   |
 OS Kernel Buffer
   |
 Disk / Network
```

---

## 5️⃣ Streams vs Buffers

### Streams (IO)

* Data flows one direction
* Cannot rewind
* Sequential access

### Buffers (NIO)

* Data loaded into buffer
* Can read/write
* Supports rewind, clear, flip

```java
buffer.flip();
buffer.clear();
```

---

## 6️⃣ Channels (NIO Core Concept)

Channels are **two-way data pipes**.

Types:

* `FileChannel`
* `SocketChannel`
* `ServerSocketChannel`

```java
FileChannel channel = FileChannel.open(Path.of("data.txt"));
```

---

## 7️⃣ Selectors (Why NIO Scales)

A **Selector** allows one thread to manage **multiple channels**.

```
One Thread
   |
 Selector
  / |  \
Ch1 Ch2 Ch3
```

Used in:

* Web servers
* Chat servers
* Event-driven systems

---

## 8️⃣ Memory-Mapped Files (NIO Superpower)

```java
MappedByteBuffer buffer = channel.map(
    FileChannel.MapMode.READ_ONLY, 0, size
);
```

* File mapped directly to memory
* Very fast
* Used for large files

---

## 9️⃣ Performance Comparison

| Feature     | Java IO        | Java NIO          |
| ----------- | -------------- | ----------------- |
| Blocking    | Yes            | No                |
| Threads     | One per client | Few threads       |
| Scalability | Low            | High              |
| Complexity  | Simple         | Complex           |
| Use case    | Small apps     | High-load systems |

---

## 🔟 When to Use Java IO

* Simple file operations
* Small input/output
* Command-line tools
* Easy debugging

---

## 1️⃣1️⃣ When to Use Java NIO

* High concurrency systems
* Network servers
* Real-time systems
* Large file processing

---

## 1️⃣2️⃣ Common Interview Questions

**Q: Why NIO is faster than IO?**
A: Non-blocking + fewer threads + buffering.

**Q: Does NIO replace IO?**
A: No, both coexist.

**Q: Why buffers instead of streams?**
A: Better memory and control.

---

## 1️⃣3️⃣ One-Liners

* "IO is blocking, NIO is non-blocking"
* "NIO scales with fewer threads"
* "Selectors enable multiplexing"
* "Channels are bidirectional"

---

## 📌 Summary

* Java IO is simple but blocking
* Java NIO is complex but scalable
* Choice depends on use case

> IO for simplicity, NIO for scalability

---

✅ Suitable for **backend systems, JVM interviews, and performance-critical applications**
