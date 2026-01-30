# Java Collections Framework – Complete Notes

These notes provide a **deep overview of the Java Collections Framework**, including:

* Core concepts
* Hierarchy
* Major interfaces and classes
* Important behaviors
* Performance characteristics
* Common interview topics

This is designed for **backend development**.

---

## 1️⃣ What is the Java Collections Framework?

The Java Collections Framework (JCF) is a **unified architecture for storing and manipulating groups of objects**.

It provides:

* Ready-made data structures
* Standard interfaces
* Efficient algorithms
* Type safety with generics

---

## 2️⃣ Why Collections Framework Exists

Before JCF:

* Different APIs for different structures
* No standardization

JCF provides:

* Common interfaces
* Reusable implementations
* Interoperability

---

## 3️⃣ Core Interfaces

Main root interfaces:

* `Collection`
* `Map` (separate hierarchy)

```
Iterable
   ↓
Collection
   ├── List
   ├── Set
   └── Queue

Map (separate — not a subtype of Collection)
```

---

## 4️⃣ Iterable Interface

* Root for all collections
* Enables for-each loop

```java
for (String s : list) { }
```

Method:

```
iterator()
```

---

## 5️⃣ Collection Interface

Defines common operations:

* add()
* remove()
* size()
* contains()
* clear()

---

## 6️⃣ List Interface

### Characteristics

* Ordered
* Allows duplicates
* Index-based access

### Major Implementations

#### 🔹 ArrayList

* Dynamic array
* Fast random access
* Slow insert/delete in middle

#### 🔹 LinkedList

* Doubly linked list
* Fast insert/delete
* Slow random access

#### 🔹 Vector (Legacy)

* Thread-safe
* Slower

---

## 7️⃣ Set Interface

### Characteristics

* No duplicates
* Unordered (generally)

### Major Implementations

#### 🔹 HashSet

* Uses hashing
* Fast operations
* No order guarantee

#### 🔹 LinkedHashSet

* Maintains insertion order

#### 🔹 TreeSet

* Sorted
* Based on Red-Black Tree
* O(log n)

---

## 8️⃣ Queue Interface

### Characteristics

* FIFO typically

### Implementations

#### 🔹 PriorityQueue

* Elements ordered by priority
* Not FIFO strictly

#### 🔹 ArrayDeque

* Faster than Stack
* Used as queue or stack

---

## 9️⃣ Map Interface (Separate Hierarchy)

### Characteristics

* Key-value pairs
* Keys unique
* Values can duplicate

---

### Major Implementations

#### 🔹 HashMap

* Most used
* O(1) average
* Allows one null key

#### 🔹 LinkedHashMap

* Maintains insertion order

#### 🔹 TreeMap

* Sorted by keys
* O(log n)

#### 🔹 Hashtable (Legacy)

* Thread-safe
* No null keys/values

---

## 🔟 Internal Data Structures (Important)

| Collection | Internal Structure   |
| ---------- | -------------------- |
| ArrayList  | Dynamic array        |
| LinkedList | Doubly linked list   |
| HashMap    | Hash table + buckets |
| TreeMap    | Red-black tree       |
| HashSet    | HashMap internally   |

---

## 1️⃣1️⃣ Big-O Complexity (Interview Favorite)

### List

| Operation | ArrayList | LinkedList |
| --------- | --------- | ---------- |
| Get       | O(1)      | O(n)       |
| Insert    | O(n)      | O(1)       |
| Delete    | O(n)      | O(1)       |

---

### Set / Map

| Structure   | Search   | Insert   |
| ----------- | -------- | -------- |
| HashSet/Map | O(1) avg | O(1)     |
| TreeSet/Map | O(log n) | O(log n) |

---

## 1️⃣2️⃣ Comparable vs Comparator

### Comparable

* Natural ordering
* Implemented inside class

### Comparator

* External ordering logic

---

## 1️⃣3️⃣ Fail-Fast vs Fail-Safe

### Fail-Fast

* Throws exception on modification during iteration
* Example: ArrayList iterator

### Fail-Safe

* Works on copy
* Example: ConcurrentHashMap

---

## 1️⃣4️⃣ Synchronization in Collections

* Legacy: Vector, Hashtable
* Modern: `Collections.synchronizedList()`
* Concurrent package: `ConcurrentHashMap`

---

## 1️⃣5️⃣ Important Utility Class – Collections

Utility methods:

* sort()
* reverse()
* shuffle()
* min()/max()
* synchronized wrappers

---

## 1️⃣6️⃣ Common Interview Questions

* Difference between ArrayList & LinkedList
* HashMap vs TreeMap
* HashSet internally uses what?
* Why Map is not part of Collection?
* How hashing works?

---

## 1️⃣7️⃣ One-Liners

* "Collections provide standard data structures"
* "Map is not a subtype of Collection"
* "HashSet uses HashMap internally"
* "TreeMap is sorted"

---

## 📌 Summary

* Collections Framework standardizes data structures
* Choose structure based on access pattern
* Performance depends on internal design

> Right collection choice = better performance + cleaner code

---

✅ Suitable for **Java backend development**
