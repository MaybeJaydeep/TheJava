# @Transactional, @JsonManagedReference & @JsonBackReference

> Deep-dive notes on transaction management and bidirectional JSON serialization in Spring / JPA / Jackson.

---

## Table of Contents

- [@Transactional](#transactional)
  - [What It Does](#what-it-does)
  - [How It Works (AOP Proxy)](#how-it-works-aop-proxy)
  - [Transaction Attributes](#transaction-attributes)
  - [Propagation](#propagation)
  - [Isolation Levels](#isolation-levels)
  - [Rollback Rules](#rollback-rules)
  - [Common Pitfalls](#transactional-common-pitfalls)
- [@JsonManagedReference & @JsonBackReference](#jsonmanagedreference--jsonbackreference)
  - [The Problem: Infinite Recursion](#the-problem-infinite-recursion)
  - [How They Work](#how-they-work)
  - [Code Example](#code-example)
  - [JSON Output](#json-output)
  - [Alternatives](#alternatives)
  - [Common Pitfalls](#json-common-pitfalls)
- [Quick Reference](#quick-reference)

---

## @Transactional

### What It Does

`@Transactional` (from `org.springframework.transaction.annotation`) wraps a method (or all methods in a class) in a **database transaction**. If the method completes successfully the transaction is **committed**; if an unchecked exception is thrown it is **rolled back**.

```java
@Service
public class OrderService {

    @Transactional
    public void placeOrder(Order order) {
        orderRepo.save(order);
        inventoryService.reduceStock(order);  // if this throws → both rolled back
        paymentService.charge(order);         // if this throws → both rolled back
    }
}
```

Without `@Transactional`, each `repo.save()` runs in its own auto-committed transaction — there is no rollback safety across multiple operations.

---

### How It Works (AOP Proxy)

Spring wraps your bean in a **proxy** at startup. When you call a `@Transactional` method, the proxy intercepts the call, begins a transaction, delegates to your code, then commits or rolls back.

```
Caller
  │
  ▼
┌─────────────────────────────┐
│   Spring AOP Proxy          │
│  1. BEGIN TRANSACTION       │
│  2. call real method ──────▶│──▶ YourService.method()
│  3. COMMIT / ROLLBACK       │
└─────────────────────────────┘
```

> ⚠️ **Self-invocation does NOT work.** Calling a `@Transactional` method from within the same class bypasses the proxy entirely. The transaction will not start.

```java
// ❌ BROKEN — self-invocation skips the proxy
public void doSomething() {
    this.placeOrder(order);  // no transaction started
}

// ✅ CORRECT — inject the bean and call through it
@Autowired
private OrderService self;

public void doSomething() {
    self.placeOrder(order);  // goes through proxy ✓
}
```

---

### Transaction Attributes

```java
@Transactional(
    propagation  = Propagation.REQUIRED,        // default
    isolation    = Isolation.DEFAULT,            // default (DB default)
    readOnly     = false,                        // default
    timeout      = -1,                           // seconds; -1 = no timeout
    rollbackFor  = { SQLException.class },       // also rollback on checked exceptions
    noRollbackFor = { IllegalArgumentException.class }
)
public void myMethod() { ... }
```

---

### Propagation

Propagation defines what happens when a `@Transactional` method is called from within an **existing** transaction.

| Propagation | Behaviour |
|---|---|
| `REQUIRED` *(default)* | Join existing transaction, or create a new one if none exists |
| `REQUIRES_NEW` | Always create a new transaction; suspend the outer one |
| `SUPPORTS` | Join if exists, run without transaction if not |
| `NOT_SUPPORTED` | Always run without a transaction; suspend outer if exists |
| `MANDATORY` | Must run inside an existing transaction; throws if none |
| `NEVER` | Must NOT run inside a transaction; throws if one exists |
| `NESTED` | Create a savepoint inside the current transaction (supports partial rollback) |

#### REQUIRED vs REQUIRES_NEW

```java
@Transactional(propagation = Propagation.REQUIRED)
public void outer() {
    inner();  // joins the same transaction — if inner() fails, outer() also rolls back
}

@Transactional(propagation = Propagation.REQUIRES_NEW)
public void inner() {
    // runs in its own transaction — commits/rollbacks independently
}
```

**Use `REQUIRES_NEW`** when you want an operation (e.g. writing an audit log) to commit even if the outer transaction rolls back.

---

### Isolation Levels

Isolation controls what data a transaction can **see** from other concurrent transactions.

| Isolation | Dirty Read | Non-Repeatable Read | Phantom Read |
|---|---|---|---|
| `READ_UNCOMMITTED` | ✅ possible | ✅ possible | ✅ possible |
| `READ_COMMITTED` | ❌ prevented | ✅ possible | ✅ possible |
| `REPEATABLE_READ` | ❌ prevented | ❌ prevented | ✅ possible |
| `SERIALIZABLE` | ❌ prevented | ❌ prevented | ❌ prevented |

- **Dirty Read** — reading uncommitted changes from another transaction.
- **Non-Repeatable Read** — reading the same row twice yields different data (another TX committed between reads).
- **Phantom Read** — a query returns different rows on two executions (another TX inserted/deleted rows between reads).

```java
@Transactional(isolation = Isolation.REPEATABLE_READ)
public BigDecimal getAccountBalance(Long accountId) { ... }
```

> Most databases default to `READ_COMMITTED`. Use higher isolation only when correctness requires it — it reduces concurrency.

---

### Rollback Rules

By default, Spring **only** rolls back on **unchecked exceptions** (`RuntimeException` and `Error`). Checked exceptions do **not** trigger a rollback unless explicitly configured.

```java
// ✅ Rolls back — RuntimeException is unchecked
@Transactional
public void method() {
    throw new IllegalStateException("oops");
}

// ❌ Does NOT roll back by default — checked exception
@Transactional
public void method() throws IOException {
    throw new IOException("file missing");
}

// ✅ Configure rollback for checked exceptions
@Transactional(rollbackFor = IOException.class)
public void method() throws IOException {
    throw new IOException("file missing");
}

// ✅ Prevent rollback on a specific runtime exception
@Transactional(noRollbackFor = OptimisticLockingFailureException.class)
public void method() { ... }
```

---

### readOnly = true

```java
@Transactional(readOnly = true)
public List<Product> getAllProducts() {
    return productRepo.findAll();
}
```

- Hints to the JPA provider that no writes will happen → Hibernate **skips dirty checking** (no snapshot comparison at flush).
- Allows the database / connection pool to apply read-only optimisations.
- **Does not prevent writes** at the Java level — it is an optimisation hint only.

> ✓ Always use `readOnly = true` on query-only methods for better performance.

---

### @Transactional Common Pitfalls

| Pitfall | Problem | Fix |
|---|---|---|
| Self-invocation | Proxy bypassed, no transaction | Inject the bean or restructure |
| `@Transactional` on `private` method | Proxy cannot intercept private methods | Make the method `public` |
| Checked exception not rolling back | Only `RuntimeException` rolls back by default | Add `rollbackFor = MyException.class` |
| `@Transactional` in `@Repository` AND `@Service` | Double transaction wrapping, unclear boundaries | Put `@Transactional` on the service layer |
| LazyInitializationException | Accessing lazy collection outside open transaction | Keep logic inside `@Transactional` or use `JOIN FETCH` |
| Forgetting `@EnableTransactionManagement` | Transactions silently ignored in non-Boot apps | Add to `@Configuration` class |

---

## @JsonManagedReference & @JsonBackReference

These are **Jackson** annotations (`com.fasterxml.jackson.annotation`) that solve the **infinite recursion** problem when serializing bidirectional JPA relationships to JSON.

---

### The Problem: Infinite Recursion

When JPA entities reference each other bidirectionally, Jackson's default serializer loops forever:

```
Department → employees → [Employee → department → employees → [Employee → ...]]
```

This results in either a `StackOverflowError` or a massive JSON payload.

```java
// Without any annotation — INFINITE LOOP ❌
public class Department {
    private List<Employee> employees;  // → each Employee has a back-ref to Department
}

public class Employee {
    private Department department;     // → Department has a list of Employees → ...
}
```

---

### How They Work

| Annotation | Side | Serialization | Deserialization |
|---|---|---|---|
| `@JsonManagedReference` | Parent ("one" side) | **Included** in JSON output | Reconstructed automatically |
| `@JsonBackReference` | Child ("many" side) | **Omitted** from JSON output | Set by Jackson via the managed ref |

Think of it as: **Managed = the forward reference that IS serialized. Back = the back-pointer that is NOT serialized.**

---

### Code Example

```java
// Department.java — parent / "one" side
@Entity
public class Department {

    @Id @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    @JsonManagedReference          // ✅ this side IS included in JSON
    private List<Employee> employees = new ArrayList<>();
}
```

```java
// Employee.java — child / "many" side
@Entity
public class Employee {

    @Id @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    @JsonBackReference             // ✅ this side is OMITTED from JSON
    private Department department;
}
```

---

### JSON Output

Serializing a `Department`:

```json
{
  "id": 1,
  "name": "Engineering",
  "employees": [
    { "id": 101, "name": "Alice" },
    { "id": 102, "name": "Bob" }
  ]
}
```

> Notice: `employees` is present, but each employee has **no `department` field** — the back-reference is omitted, breaking the cycle.

Serializing an `Employee` directly:

```json
{
  "id": 101,
  "name": "Alice"
}
```

> The `department` field is omitted entirely because it carries `@JsonBackReference`.

---

### Alternatives

#### 1. `@JsonIgnore`

Simpler — just omit the field entirely from serialization and deserialization.

```java
@JsonIgnore
private Department department;
```

**Difference from `@JsonBackReference`:** `@JsonIgnore` is completely ignored in both directions; `@JsonBackReference` is still reconstructed during deserialization.

#### 2. `@JsonIgnoreProperties`

Suppress specific fields on the referenced type without modifying it:

```java
@JsonIgnoreProperties("employees")
private Department department;
```

#### 3. DTOs (Recommended for Production)

The cleanest solution — map entities to Data Transfer Objects and control exactly what gets serialized. No annotation gymnastics needed.

```java
public class DepartmentDTO {
    private Long id;
    private String name;
    private List<EmployeeSummaryDTO> employees;
}

public class EmployeeSummaryDTO {
    private Long id;
    private String name;
    // no department field — no cycle possible
}
```

#### 4. `@JsonIdentityInfo`

Serializes object references by ID after the first occurrence, avoiding duplication and cycles. More complex output.

```java
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
public class Department { ... }
```

---

### JSON Common Pitfalls

| Pitfall | Problem | Fix |
|---|---|---|
| Both sides annotated with `@JsonManagedReference` | Jackson can't resolve which is the "parent" | One side `@JsonManaged`, other side `@JsonBack` |
| `@JsonBackReference` on a `@OneToOne` owning side | The owning entity is never serialized | Put `@JsonBackReference` on the inverse/child side |
| Expecting `department` field in Employee JSON | `@JsonBackReference` omits it always | Use `@JsonIgnoreProperties` or a DTO instead |
| Multiple bidirectional refs in one entity | Default `@JsonManagedReference` doesn't differentiate | Name them: `@JsonManagedReference("dept-employees")` |
| Using with `@JsonBackReference` on lazy proxy | Jackson may trigger lazy load then omit it | Ensure session is open, or use DTOs |

#### Naming Multiple References

If an entity has more than one bidirectional relationship, each pair must have a unique name:

```java
// Department.java
@JsonManagedReference("dept-employees")
private List<Employee> employees;

@JsonManagedReference("dept-managers")
private List<Manager> managers;

// Employee.java
@JsonBackReference("dept-employees")
private Department department;

// Manager.java
@JsonBackReference("dept-managers")
private Department department;
```

---

## Quick Reference

### @Transactional Cheatsheet

```java
// Standard service method — reads and writes
@Transactional
public void process() { ... }

// Read-only query — skip dirty checking
@Transactional(readOnly = true)
public List<X> getAll() { ... }

// Always commit in its own transaction (e.g. audit log)
@Transactional(propagation = Propagation.REQUIRES_NEW)
public void audit(String event) { ... }

// Rollback on checked exceptions too
@Transactional(rollbackFor = Exception.class)
public void riskyOperation() throws Exception { ... }

// High-isolation financial read
@Transactional(isolation = Isolation.SERIALIZABLE, readOnly = true)
public BigDecimal getBalance(Long id) { ... }
```

### @JsonManagedReference / @JsonBackReference Cheatsheet

```java
// Parent entity — "one" side — field IS serialized
@OneToMany(mappedBy = "parent")
@JsonManagedReference
private List<Child> children;

// Child entity — "many" side — field is OMITTED from JSON
@ManyToOne
@JoinColumn(name = "parent_id")
@JsonBackReference
private Parent parent;

// Multiple bidirectional refs — must be named
@JsonManagedReference("order-items")
private List<OrderItem> items;

@JsonBackReference("order-items")
private Order order;
```

### Decision Guide

```
Circular JSON problem?
│
├── Need the back-reference during deserialization?
│   └── Yes → @JsonManagedReference + @JsonBackReference
│
├── Just want to exclude a field?
│   └── Yes → @JsonIgnore  or  @JsonIgnoreProperties
│
├── Need both sides in JSON but without duplication?
│   └── Yes → @JsonIdentityInfo
│
└── Building an API for clients?
    └── Always → Use DTOs (cleanest, most flexible)
```

---

*Part of the JPA / Spring reference notes. See also: [JPA Relationship Mapping](./JPA_HIBERNATE_RELATIONSHIPS.md)*