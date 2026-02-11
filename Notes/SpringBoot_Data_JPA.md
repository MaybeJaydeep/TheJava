# Spring Boot Data JPA

## What is Spring Data JPA?

Spring Data JPA is a part of Spring Data that makes it easy to implement JPA-based repositories. It reduces boilerplate code and provides powerful query capabilities.

## Dependencies

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
</dependency>
```

## Configuration

### application.properties

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/mydb
spring.datasource.username=root
spring.datasource.password=secret
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

### DDL Auto Options

- `none`: No action
- `validate`: Validate schema, no changes
- `update`: Update schema (add new columns/tables)
- `create`: Create schema, destroy previous data
- `create-drop`: Create schema, drop on session close

## Entity Mapping

### Basic Entity

```java
@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String name;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    // Getters and setters
}
```

### Generation Strategies

```java
// Auto-increment (MySQL, PostgreSQL)
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

// Sequence (Oracle, PostgreSQL)
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
@SequenceGenerator(name = "user_seq", sequenceName = "user_sequence")
private Long id;

// Table generator
@GeneratedValue(strategy = GenerationType.TABLE)
private Long id;

// UUID
@GeneratedValue(strategy = GenerationType.UUID)
private UUID id;
```

## Relationships

### One-to-Many

```java
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Employee> employees = new ArrayList<>();
    
    // Helper methods
    public void addEmployee(Employee employee) {
        employees.add(employee);
        employee.setDepartment(this);
    }
    
    public void removeEmployee(Employee employee) {
        employees.remove(employee);
        employee.setDepartment(null);
    }
}

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;
}
```

### Many-to-Many

```java
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    @ManyToMany
    @JoinTable(
        name = "student_course",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> courses = new HashSet<>();
}

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    @ManyToMany(mappedBy = "courses")
    private Set<Student> students = new HashSet<>();
}
```

### One-to-One

```java
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String username;
    
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private UserProfile profile;
}

@Entity
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String bio;
    private String avatar;
    
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
```

## Repository Interface

### Basic Repository

```java
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
```

This provides:
- `save(entity)`
- `findById(id)`
- `findAll()`
- `deleteById(id)`
- `count()`
- And many more...

### Query Methods

```java
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    // Find by single field
    List<User> findByName(String name);
    Optional<User> findByEmail(String email);
    
    // Find with conditions
    List<User> findByNameAndEmail(String name, String email);
    List<User> findByNameOrEmail(String name, String email);
    
    // Find with comparison
    List<User> findByAgeGreaterThan(int age);
    List<User> findByAgeLessThanEqual(int age);
    List<User> findByAgeBetween(int start, int end);
    
    // Find with pattern matching
    List<User> findByNameContaining(String name);
    List<User> findByNameStartingWith(String prefix);
    List<User> findByNameEndingWith(String suffix);
    
    // Find with null checks
    List<User> findByEmailIsNull();
    List<User> findByEmailIsNotNull();
    
    // Find with collection
    List<User> findByIdIn(List<Long> ids);
    
    // Find with date
    List<User> findByCreatedAtAfter(LocalDateTime date);
    List<User> findByCreatedAtBefore(LocalDateTime date);
    
    // Ordering
    List<User> findByNameOrderByCreatedAtDesc(String name);
    
    // Limiting results
    User findFirstByOrderByCreatedAtDesc();
    List<User> findTop10ByOrderByCreatedAtDesc();
    
    // Boolean queries
    boolean existsByEmail(String email);
    long countByName(String name);
    
    // Delete queries
    void deleteByName(String name);
    long deleteByCreatedAtBefore(LocalDateTime date);
}
```

### Custom Queries with @Query

#### JPQL Queries

```java
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    @Query("SELECT u FROM User u WHERE u.name = :name")
    List<User> findByNameCustom(@Param("name") String name);
    
    @Query("SELECT u FROM User u WHERE u.name = :name AND u.email = :email")
    Optional<User> findByNameAndEmailCustom(
        @Param("name") String name,
        @Param("email") String email
    );
    
    @Query("SELECT u FROM User u WHERE u.createdAt > :date")
    List<User> findUsersCreatedAfter(@Param("date") LocalDateTime date);
    
    // Join query
    @Query("SELECT u FROM User u JOIN u.department d WHERE d.name = :deptName")
    List<User> findByDepartmentName(@Param("deptName") String deptName);
}
```

#### Native SQL Queries

```java
@Query(value = "SELECT * FROM users WHERE name LIKE %:name%", nativeQuery = true)
List<User> searchByName(@Param("name") String name);

@Query(value = "SELECT * FROM users WHERE created_at > :date", nativeQuery = true)
List<User> findUsersCreatedAfterNative(@Param("date") LocalDateTime date);
```

#### Update Queries

```java
@Modifying
@Query("UPDATE User u SET u.name = :name WHERE u.id = :id")
int updateUserName(@Param("id") Long id, @Param("name") String name);

@Modifying
@Query("DELETE FROM User u WHERE u.createdAt < :date")
int deleteOldUsers(@Param("date") LocalDateTime date);
```

## Pagination and Sorting

### Pagination

```java
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findByName(String name, Pageable pageable);
}

// Service
@Service
public class UserService {
    
    public Page<User> getUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable);
    }
}
```

### Sorting

```java
// Single field
Pageable pageable = PageRequest.of(0, 10, Sort.by("name"));

// Multiple fields
Pageable pageable = PageRequest.of(0, 10, 
    Sort.by("name").ascending()
        .and(Sort.by("createdAt").descending())
);

// Direction
Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
Pageable pageable = PageRequest.of(0, 10, sort);
```

## Projections

### Interface Projection

```java
public interface UserProjection {
    String getName();
    String getEmail();
}

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u.name as name, u.email as email FROM User u")
    List<UserProjection> findAllProjected();
}
```

### Class Projection (DTO)

```java
public class UserDTO {
    private String name;
    private String email;
    
    public UserDTO(String name, String email) {
        this.name = name;
        this.email = email;
    }
    
    // Getters
}

@Query("SELECT new com.example.dto.UserDTO(u.name, u.email) FROM User u")
List<UserDTO> findAllDTO();
```

## Specifications

For dynamic queries:

```java
public class UserSpecifications {
    
    public static Specification<User> hasName(String name) {
        return (root, query, cb) -> 
            name == null ? null : cb.equal(root.get("name"), name);
    }
    
    public static Specification<User> hasEmail(String email) {
        return (root, query, cb) -> 
            email == null ? null : cb.equal(root.get("email"), email);
    }
    
    public static Specification<User> createdAfter(LocalDateTime date) {
        return (root, query, cb) -> 
            date == null ? null : cb.greaterThan(root.get("createdAt"), date);
    }
}

// Repository
public interface UserRepository extends JpaRepository<User, Long>, 
                                        JpaSpecificationExecutor<User> {
}

// Usage
Specification<User> spec = Specification
    .where(UserSpecifications.hasName("John"))
    .and(UserSpecifications.createdAfter(LocalDateTime.now().minusDays(7)));

List<User> users = userRepository.findAll(spec);
```

## Auditing

### Enable Auditing

```java
@Configuration
@EnableJpaAuditing
public class JpaConfig {
}
```

### Auditable Entity

```java
@Entity
@EntityListeners(AuditingEntityListener.class)
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @LastModifiedDate
    private LocalDateTime updatedAt;
    
    @CreatedBy
    @Column(nullable = false, updatable = false)
    private String createdBy;
    
    @LastModifiedBy
    private String updatedBy;
}
```

### Auditor Provider

```java
@Component
public class AuditorAwareImpl implements AuditorAware<String> {
    
    @Override
    public Optional<String> getCurrentAuditor() {
        // Get current user from security context
        return Optional.of("system");
    }
}
```

## Transactions

```java
@Service
@Transactional
public class UserService {
    
    private final UserRepository userRepository;
    
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }
    
    public User save(User user) {
        return userRepository.save(user);
    }
    
    @Transactional(rollbackFor = Exception.class)
    public void transferData(Long fromId, Long toId) {
        // Multiple operations in single transaction
        User from = userRepository.findById(fromId).orElseThrow();
        User to = userRepository.findById(toId).orElseThrow();
        
        // Operations...
        
        userRepository.save(from);
        userRepository.save(to);
    }
}
```

## Best Practices

1. Use `@Transactional(readOnly = true)` for read operations
2. Use `FetchType.LAZY` for relationships (default for @ManyToOne, @OneToOne)
3. Avoid N+1 queries - use JOIN FETCH
4. Use projections for read-only data
5. Use pagination for large datasets
6. Keep transactions short
7. Use proper cascade types
8. Use `orphanRemoval = true` for parent-child relationships
9. Override `equals()` and `hashCode()` in entities
10. Use database indexes for frequently queried fields
