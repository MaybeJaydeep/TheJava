# JPA / Hibernate Relationship Mapping

> A concise reference for mapping entity relationships in JPA/Hibernate — covering annotations, ownership, fetch types, and cascade behaviour.

---

## Table of Contents

- [Overview](#overview)
- [The Owning Side & mappedBy](#the-owning-side--mappedby)
- [@OneToOne](#onetoone)
- [@OneToMany / @ManyToOne](#onetomany--manytoone)
- [@ManyToMany](#manytomany)
- [CascadeType](#cascadetype)
- [FetchType](#fetchtype)
- [orphanRemoval](#orphanremoval)
- [Quick Reference](#quick-reference)

---

## Overview

JPA provides four relationship annotations to map Java object associations to relational DB tables:

| Annotation | Cardinality | DB Structure | Default Fetch |
|---|---|---|---|
| `@OneToOne` | 1 ↔ 1 | FK in one table (+ UNIQUE) | EAGER |
| `@ManyToOne` | N → 1 | FK in the "many" table | EAGER |
| `@OneToMany` | 1 → N | FK in the "many" table | LAZY |
| `@ManyToMany` | N ↔ N | Join/pivot table | LAZY |

---

## The Owning Side & `mappedBy`

Every **bidirectional** relationship has:

- **Owning side** — holds the `@JoinColumn`. Hibernate reads this side to write the FK to the database.
- **Inverse side** — uses `mappedBy` to declare it is non-owning. Changes here are **not** persisted automatically.

```java
// Owning side — writes the FK column
@ManyToOne
@JoinColumn(name = "department_id")
private Department department;

// Inverse side — read-only from DB perspective
@OneToMany(mappedBy = "department")
private List<Employee> employees;
```

> **Rule:** The value of `mappedBy` must match the **field name** on the owning side, not the column name.

---

## @OneToOne

One entity is associated with exactly one other entity.

**Example:** `User` has one `Profile`.

### Entity Diagram

```
┌─────────────┐         ┌──────────────────┐
│    User     │ 1     1 │     Profile      │
│─────────────│─────────│──────────────────│
│ PK id       │         │ PK id            │
│    username │         │ FK user_id (UQ)  │
│    email    │         │    bio           │
└─────────────┘         │    avatar_url    │
                        └──────────────────┘
```

### Code

```java
// User.java — inverse side
@Entity
public class User {
    @Id @GeneratedValue
    private Long id;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Profile profile;
}

// Profile.java — owning side (holds the FK)
@Entity
public class Profile {
    @Id @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;
}
```

### Generated DDL

```sql
CREATE TABLE user    (id BIGINT PRIMARY KEY, username VARCHAR, email VARCHAR);
CREATE TABLE profile (
    id         BIGINT PRIMARY KEY,
    user_id    BIGINT UNIQUE REFERENCES user(id),  -- UNIQUE enforces 1:1
    bio        TEXT,
    avatar_url VARCHAR
);
```

### Key Points

- The **FK lives on the owning side** (Profile). Use `@JoinColumn` to name it.
- Add `unique = true` (or a DB `UNIQUE` constraint) to enforce 1:1 at the database level.
- Default fetch is **EAGER** — always override to `LAZY` to avoid unnecessary joins.

---

## @OneToMany / @ManyToOne

The most common relationship. One parent has many children. The FK **always** lives in the "many" (child) table.

**Example:** `Department` has many `Employee`s.

### Entity Diagram

```
┌──────────────┐          ┌─────────────────────┐
│  Department  │ 1      N │      Employee       │
│──────────────│──────────│─────────────────────│
│ PK id        │          │ PK id               │
│    name      │          │ FK department_id    │
│    location  │          │    name             │
└──────────────┘          │    salary           │
                          └─────────────────────┘
```

### Code

```java
// Department.java — inverse (one) side
@Entity
public class Department {
    @Id @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Employee> employees = new ArrayList<>();

    // Helper methods to keep both sides in sync
    public void addEmployee(Employee e) {
        employees.add(e);
        e.setDepartment(this);
    }

    public void removeEmployee(Employee e) {
        employees.remove(e);
        e.setDepartment(null);
    }
}

// Employee.java — owning (many) side
@Entity
public class Employee {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;
}
```

### Generated DDL

```sql
CREATE TABLE department (id BIGINT PRIMARY KEY, name VARCHAR, location VARCHAR);
CREATE TABLE employee   (
    id            BIGINT PRIMARY KEY,
    department_id BIGINT REFERENCES department(id),
    name          VARCHAR,
    salary        DECIMAL
);
```

### Key Points

- `@ManyToOne` is **always** the owning side.
- Always sync both sides in bidirectional relationships using helper methods. If you only set the `@OneToMany` side, Hibernate **will not write the FK**.
- You can go unidirectional — keep only `@ManyToOne` and omit `@OneToMany` for a simpler design.

---

## @ManyToMany

Many entities relate to many others. Requires a **join table** (pivot table) in the database.

**Example:** `Student` enrolls in many `Course`s; each `Course` has many `Student`s.

### Entity Diagram

```
┌───────────┐    ┌─────────────────┐    ┌──────────┐
│  Student  │    │ student_course  │    │  Course  │
│───────────│    │─────────────────│    │──────────│
│ PK id     │───▶│ FK student_id   │◀───│ PK id    │
│    name   │    │ FK course_id    │    │    title │
└───────────┘    └─────────────────┘    └──────────┘
```

### Code

```java
// Student.java — owning side (defines the join table)
@Entity
public class Student {
    @Id @GeneratedValue
    private Long id;

    @ManyToMany
    @JoinTable(
        name = "student_course",
        joinColumns        = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> courses = new HashSet<>();
}

// Course.java — inverse side
@Entity
public class Course {
    @Id @GeneratedValue
    private Long id;

    @ManyToMany(mappedBy = "courses")
    private Set<Student> students = new HashSet<>();
}
```

### Generated DDL

```sql
CREATE TABLE student (id BIGINT PRIMARY KEY, name VARCHAR);
CREATE TABLE course  (id BIGINT PRIMARY KEY, title VARCHAR);

CREATE TABLE student_course (
    student_id BIGINT REFERENCES student(id),
    course_id  BIGINT REFERENCES course(id),
    PRIMARY KEY (student_id, course_id)
);
```

### When to Use an Intermediate Entity Instead

If the join table needs **extra columns** (e.g. `enrolled_date`, `grade`), replace `@ManyToMany` with an explicit entity using two `@ManyToOne` relationships:

```java
@Entity
public class Enrollment {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne @JoinColumn(name = "course_id")
    private Course course;

    private LocalDate enrolledDate;
    private String    grade;
}
```

> **Tip:** Prefer an intermediate entity whenever the join table grows beyond simple FK pairs.

---

## CascadeType

Cascade controls which JPA lifecycle operations are propagated from parent to children.

```java
@OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
```

| Type | Effect |
|---|---|
| `PERSIST` | Saving parent also saves new children |
| `MERGE` | Merging parent state also merges children |
| `REMOVE` | Deleting parent also deletes children |
| `REFRESH` | Refreshing parent reloads children from DB |
| `DETACH` | Detaching parent also detaches children |
| `ALL` | Applies all of the above |

> ⚠️ **Avoid `CascadeType.ALL` on `@ManyToMany`** — it can cause unexpected mass deletions. Prefer `PERSIST` and `MERGE` only.

---

## FetchType

Controls **when** associated data is loaded from the database.

### EAGER

Loads the related entity immediately with the parent via a SQL `JOIN`.

```sql
-- Single query, but always loads related data
SELECT * FROM user
LEFT JOIN profile ON profile.user_id = user.id
```

### LAZY

Loads the related entity **only when the field is accessed** in code. Uses a proxy object.

```sql
-- Initial query
SELECT * FROM user WHERE id = ?

-- Only if profile field is accessed
SELECT * FROM profile WHERE user_id = ?
```

### Default Fetch Types

| Annotation | Default | Recommendation |
|---|---|---|
| `@OneToOne` | `EAGER` | Override to `LAZY` |
| `@ManyToOne` | `EAGER` | Override to `LAZY` |
| `@OneToMany` | `LAZY` | Keep as `LAZY` ✓ |
| `@ManyToMany` | `LAZY` | Keep as `LAZY` ✓ |

> **Best practice:** Set `fetch = FetchType.LAZY` on all relationships. When you need data eagerly, use `JOIN FETCH` in JPQL for that specific query.

### JOIN FETCH (solve N+1)

```java
// Single query — loads department and all employees together
SELECT d FROM Department d
JOIN FETCH d.employees
WHERE d.id = :id
```

---

## orphanRemoval

When `orphanRemoval = true`, removing a child entity from the parent's collection **automatically deletes it from the database** — no need to call `em.remove()` explicitly.

```java
@OneToMany(mappedBy = "department", orphanRemoval = true)
private List<Employee> employees;
```

```java
// With orphanRemoval = true  → employee is DELETED from DB
department.getEmployees().remove(employee);

// Without orphanRemoval       → FK is set to NULL, employee row remains
department.getEmployees().remove(employee);
```

> Use `orphanRemoval = true` when children have no meaning outside the parent — e.g. `OrderItem` without an `Order`.

**Difference from `CascadeType.REMOVE`:**

| | `CascadeType.REMOVE` | `orphanRemoval = true` |
|---|---|---|
| Triggered by | `em.remove(parent)` | Removing child from collection |
| Parent deleted? | Yes | No (parent can survive) |

---

## Quick Reference

```java
// @OneToOne — owning side
@OneToOne
@JoinColumn(name = "fk_column", unique = true)
private RelatedEntity related;

// @ManyToOne — always the owning side
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "fk_column")
private ParentEntity parent;

// @OneToMany — always the inverse side
@OneToMany(mappedBy = "fieldNameOnOwningSide", cascade = CascadeType.ALL, orphanRemoval = true)
private List<ChildEntity> children = new ArrayList<>();

// @ManyToMany — owning side
@ManyToMany
@JoinTable(
    name = "join_table_name",
    joinColumns        = @JoinColumn(name = "this_fk"),
    inverseJoinColumns = @JoinColumn(name = "other_fk")
)
private Set<OtherEntity> others = new HashSet<>();

// @ManyToMany — inverse side
@ManyToMany(mappedBy = "fieldNameOnOwningSide")
private Set<ThisEntity> items = new HashSet<>();
```

### Common Pitfalls

- **Forgetting to sync both sides** in bidirectional relationships → FK won't be written.
- **Using `CascadeType.ALL` on `@ManyToMany`** → unexpected cascaded deletes.
- **Leaving default EAGER fetch** on `@OneToOne` / `@ManyToOne` → N+1 query problems.
- **Not using `@JoinColumn`** → Hibernate generates a column name you didn't intend.
- **Setting only the inverse side** (`@OneToMany` without setting `@ManyToOne`) → relationship is ignored by Hibernate.

---

