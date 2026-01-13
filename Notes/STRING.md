# Java String – Memory, Methods & Comparison

📅 **Date:** 13/01/2026
✍️ **Author:** Jaydeep Badal

---

## 1️⃣ String Memory & String Constant Pool (SCP)

```java
String s1 = new String("Hello World");
String s2 = new String("Hello World");
String s3 = new String("Hello");
```

### ❗ Important Correction (Interview-Favorite)

* ❌ `s1` and `s2` **do NOT** point to the same memory location
* ❌ None of the above references point directly to SCP

### ✅ Actual Behavior

```
Heap Memory
------------
s1 ──► "Hello World"   (new object)
s2 ──► "Hello World"   (new object)
s3 ──► "Hello"         (new object)

String Constant Pool (SCP)
-------------------------
"Hello World"
"Hello"
```

✔ `new String("...")` **always creates a new Heap object**
✔ SCP stores **string literals**, not heap references

---

## 2️⃣ Why Strings Are Immutable?

### ✅ Security

* Used for **passwords, usernames, tokens**
* Prevents modification after creation

### ✅ Thread Safety

* Immutable objects are **inherently thread-safe**

### ✅ Memory Optimization (SCP)

* Same literals reused instead of duplicated

---

## 3️⃣ Proof Example (== vs equals)

```java
String a = "Hello World";
String b = "Hello World";

System.out.println(a == b);      // true (same SCP reference)
System.out.println(a.equals(b)); // true (same content)

String c = new String("Hello World");
System.out.println(a == c);      // false (SCP vs Heap)
```

---

## 4️⃣ String Creation Summary

| Creation Style        | Stored In | Reused |
| --------------------- | --------- | ------ |
| `"Hello"`             | SCP       | ✅      |
| `new String("Hello")` | Heap      | ❌      |
| `intern()`            | SCP       | ✅      |

```java
String x = new String("Java").intern();
```

---

## 5️⃣ Commonly Used String Methods

| Method                | Description             | Return Type |
| --------------------- | ----------------------- | ----------- |
| charAt()              | Character at index      | char        |
| compareTo()           | Lexicographical compare | int         |
| compareToIgnoreCase() | Ignore case compare     | int         |
| concat()              | Append string           | String      |
| contains()            | Substring check         | boolean     |
| equals()              | Content comparison      | boolean     |
| equalsIgnoreCase()    | Ignore case comparison  | boolean     |
| indexOf()             | First occurrence        | int         |
| lastIndexOf()         | Last occurrence         | int         |
| isEmpty()             | Empty check             | boolean     |
| length()              | String length           | int         |
| matches()             | Regex match             | boolean     |
| replace()             | Replace literal         | String      |
| replaceAll()          | Replace regex           | String      |
| split()               | Split string            | String[]    |
| startsWith()          | Prefix check            | boolean     |
| endsWith()            | Suffix check            | boolean     |
| substring()           | Extract substring       | String      |
| toCharArray()         | Convert to char[]       | char[]      |
| toLowerCase()         | Lowercase               | String      |
| toUpperCase()         | Uppercase               | String      |
| trim()                | Remove spaces           | String      |
| valueOf()             | Convert to String       | String      |

---

## 6️⃣ String vs StringBuffer vs StringBuilder

```
+----------------+----------------+-------------------+------------------+
| Feature        | String         | StringBuffer     | StringBuilder   |
+----------------+----------------+-------------------+------------------+
| Thread Safe    | ❌ No          | ✅ Yes            | ❌ No           |
| Mutability     | Immutable      | Mutable          | Mutable         |
| Performance    | Slow           | Slow–Medium      | Fastest         |
| Synchronization| ❌ No          | ✅ Yes            | ❌ No           |
| Use Case       | Fixed strings  | Multi-threaded   | Single-threaded |
+----------------+----------------+-------------------+------------------+
```

### 🔑 Key Notes

* `String` → creates **new object on modification**
* `StringBuffer` → **thread-safe but slower**
* `StringBuilder` → **best performance**, not thread-safe

---

## 7️⃣ When to Use What?

| Scenario                | Recommended   |
| ----------------------- | ------------- |
| Constants, keys         | String        |
| Concurrent modification | StringBuffer  |
| Fast string building    | StringBuilder |

---

## ⭐ Additional Points (Worth Adding)

* `String` overrides **equals() and hashCode()**
* Used heavily in **HashMap keys**
* Immutable strings improve **cache & performance**
* `StringBuilder` introduced in **Java 5**
* SCP exists inside **Heap memory (Java 7+)**

---

🚀 **End of Notes**
