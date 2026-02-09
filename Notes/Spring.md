# Spring Framework - Comprehensive Concepts Guide

## Table of Contents
- [Introduction](#introduction)
- [Core Concepts](#core-concepts)
- [Dependency Injection](#dependency-injection)
- [Spring Bean Lifecycle](#spring-bean-lifecycle)
- [Spring Annotations](#spring-annotations)
- [Spring Boot](#spring-boot)
- [Spring MVC](#spring-mvc)
- [Spring Data JPA](#spring-data-jpa)
- [Spring Security](#spring-security)
- [Spring AOP](#spring-aop)
- [Configuration Management](#configuration-management)
- [Best Practices](#best-practices)

---

## Introduction

**Spring Framework** is a comprehensive programming and configuration model for modern Java-based enterprise applications. It provides infrastructure support for developing Java applications, allowing developers to focus on business logic rather than plumbing code.

### Key Features
- **Lightweight**: Minimal overhead with POJO-based development
- **Inversion of Control (IoC)**: Container manages object lifecycle
- **Aspect-Oriented Programming (AOP)**: Separation of cross-cutting concerns
- **Transaction Management**: Consistent programming model
- **MVC Framework**: Web application development
- **Data Access**: Simplified database operations

---

## Core Concepts

### 1. Inversion of Control (IoC)

IoC is a design principle where the control of object creation and dependency management is transferred from the application code to the Spring container.

**Traditional Approach:**
```java
public class UserService {
    private UserRepository repository = new UserRepository(); // Tight coupling
}
```

**Spring IoC Approach:**
```java
public class UserService {
    private UserRepository repository; // Loose coupling
    
    public UserService(UserRepository repository) {
        this.repository = repository;
    }
}
```

### 2. Spring Container

The Spring IoC container is responsible for:
- Creating objects
- Configuring objects
- Managing the complete lifecycle of objects

**Two types of containers:**
- **BeanFactory**: Basic container providing DI features
- **ApplicationContext**: Advanced container with enterprise features (preferred)

```java
ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
UserService userService = context.getBean(UserService.class);
```

### 3. Beans

A **Bean** is an object that is instantiated, assembled, and managed by the Spring IoC container.

**Bean Definition:**
```java
@Configuration
public class AppConfig {
    
    @Bean
    public UserService userService() {
        return new UserService(userRepository());
    }
    
    @Bean
    public UserRepository userRepository() {
        return new UserRepository();
    }
}
```

---

## Dependency Injection

Dependency Injection (DI) is a pattern where objects define their dependencies only through constructor arguments, factory method arguments, or properties.

### Types of Dependency Injection

#### 1. Constructor Injection (Recommended)

```java
@Service
public class UserService {
    private final UserRepository userRepository;
    private final EmailService emailService;
    
    // Spring automatically injects dependencies
    public UserService(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }
}
```

**Benefits:**
- Immutability (using final fields)
- Required dependencies are clear
- Easier to test
- Prevents circular dependencies

#### 2. Setter Injection

```java
@Service
public class UserService {
    private UserRepository userRepository;
    
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
```

**Use cases:**
- Optional dependencies
- Dependencies that can be reconfigured

#### 3. Field Injection (Not Recommended)

```java
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository; // Avoid this approach
}
```

**Drawbacks:**
- Cannot create immutable fields
- Harder to test
- Hides dependencies
- Tight coupling with Spring framework

### Dependency Resolution

```java
@Component
public class OrderService {
    
    // Single constructor - @Autowired optional
    public OrderService(OrderRepository orderRepository) {
        // Automatically injected
    }
}

@Component
public class PaymentService {
    
    // Multiple constructors - @Autowired required
    @Autowired
    public PaymentService(PaymentGateway gateway) {
    }
    
    public PaymentService() {
    }
}
```

---

## Spring Bean Lifecycle

Understanding the bean lifecycle is crucial for initialization and cleanup operations.

### Lifecycle Phases

1. **Instantiation**: Container creates bean instance
2. **Populate Properties**: Dependencies are injected
3. **BeanNameAware**: `setBeanName()` called
4. **BeanFactoryAware**: `setBeanFactory()` called
5. **ApplicationContextAware**: `setApplicationContext()` called
6. **Pre-Initialization**: `BeanPostProcessor.postProcessBeforeInitialization()`
7. **InitializingBean**: `afterPropertiesSet()` called
8. **Custom Init Method**: Method specified in `@Bean(initMethod="...")`
9. **Post-Initialization**: `BeanPostProcessor.postProcessAfterInitialization()`
10. **Bean Ready**: Bean is ready for use
11. **Destruction**: Container shutdown triggers cleanup

### Lifecycle Callbacks

```java
@Component
public class DatabaseConnection {
    
    // 1. Using @PostConstruct
    @PostConstruct
    public void init() {
        System.out.println("Initializing database connection");
    }
    
    // 2. Using @PreDestroy
    @PreDestroy
    public void cleanup() {
        System.out.println("Closing database connection");
    }
}
```

```java
@Configuration
public class AppConfig {
    
    // 3. Using initMethod and destroyMethod
    @Bean(initMethod = "connect", destroyMethod = "disconnect")
    public DatabaseService databaseService() {
        return new DatabaseService();
    }
}
```

```java
// 4. Implementing InitializingBean and DisposableBean
@Component
public class CacheService implements InitializingBean, DisposableBean {
    
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Loading cache");
    }
    
    @Override
    public void destroy() throws Exception {
        System.out.println("Clearing cache");
    }
}
```

### Bean Scopes

```java
@Component
@Scope("singleton") // Default scope
public class SingletonBean {
    // One instance per Spring container
}

@Component
@Scope("prototype")
public class PrototypeBean {
    // New instance every time bean is requested
}

@Component
@Scope("request")
public class RequestScopedBean {
    // One instance per HTTP request (web applications)
}

@Component
@Scope("session")
public class SessionScopedBean {
    // One instance per HTTP session (web applications)
}

@Component
@Scope("application")
public class ApplicationScopedBean {
    // One instance per ServletContext (web applications)
}
```

---

## Spring Annotations

### Core Stereotype Annotations

```java
@Component  // Generic component
public class GenericComponent {
}

@Service    // Service layer component
public class UserService {
}

@Repository // Data access layer component
public class UserRepository {
}

@Controller // Web controller (Spring MVC)
public class UserController {
}

@RestController // REST API controller (@Controller + @ResponseBody)
public class UserRestController {
}
```

### Configuration Annotations

```java
@Configuration
public class AppConfig {
    
    @Bean
    public DataSource dataSource() {
        return new HikariDataSource();
    }
    
    @Bean
    @Primary // Preferred bean when multiple candidates exist
    public UserService primaryUserService() {
        return new UserServiceImpl();
    }
    
    @Bean
    @Qualifier("secondaryService") // Named qualifier
    public UserService secondaryUserService() {
        return new SecondaryUserServiceImpl();
    }
}
```

### Dependency Injection Annotations

```java
@Service
public class OrderService {
    
    @Autowired // Inject by type
    private OrderRepository repository;
    
    @Autowired
    @Qualifier("emailService") // Inject specific bean by name
    private NotificationService notificationService;
    
    @Value("${app.name}") // Inject property value
    private String appName;
    
    @Value("${app.max-retries:3}") // With default value
    private int maxRetries;
}
```

### Component Scanning

```java
@Configuration
@ComponentScan(basePackages = "com.example.app")
public class AppConfig {
}

// Or exclude specific components
@ComponentScan(
    basePackages = "com.example.app",
    excludeFilters = @ComponentScan.Filter(
        type = FilterType.ANNOTATION,
        classes = Configuration.class
    )
)
```

### Conditional Annotations

```java
@Configuration
public class DatabaseConfig {
    
    @Bean
    @ConditionalOnProperty(name = "database.type", havingValue = "mysql")
    public DataSource mysqlDataSource() {
        return new MySQLDataSource();
    }
    
    @Bean
    @ConditionalOnProperty(name = "database.type", havingValue = "postgres")
    public DataSource postgresDataSource() {
        return new PostgreSQLDataSource();
    }
    
    @Bean
    @ConditionalOnMissingBean(DataSource.class)
    public DataSource defaultDataSource() {
        return new H2DataSource();
    }
}
```

---

## Spring Boot

Spring Boot simplifies Spring application development by providing auto-configuration and production-ready features.

### Key Features

- **Auto-configuration**: Automatically configures Spring application based on dependencies
- **Starter Dependencies**: Pre-configured dependency descriptors
- **Embedded Server**: No need for external application server
- **Production-ready**: Metrics, health checks, externalized configuration

### Spring Boot Application

```java
@SpringBootApplication // Combines @Configuration, @EnableAutoConfiguration, @ComponentScan
public class Application {
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

### Application Properties

**application.properties:**
```properties
# Server configuration
server.port=8080
server.servlet.context-path=/api

# Database configuration
spring.datasource.url=jdbc:mysql://localhost:3306/mydb
spring.datasource.username=root
spring.datasource.password=secret
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Logging
logging.level.root=INFO
logging.level.com.example=DEBUG
```

**application.yml:**
```yaml
server:
  port: 8080
  servlet:
    context-path: /api

spring:
  datasource:
    url: jdbc:mysql://localhost:3306/mydb
    username: root
    password: secret
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

logging:
  level:
    root: INFO
    com.example: DEBUG
```

### Configuration Properties

```java
@ConfigurationProperties(prefix = "app")
@Component
public class AppProperties {
    private String name;
    private String version;
    private Security security = new Security();
    
    // Getters and setters
    
    public static class Security {
        private boolean enabled;
        private int timeout;
        
        // Getters and setters
    }
}
```

```yaml
app:
  name: My Application
  version: 1.0.0
  security:
    enabled: true
    timeout: 3600
```

### Profiles

```java
@Configuration
@Profile("development")
public class DevConfig {
    @Bean
    public DataSource dataSource() {
        return new H2DataSource();
    }
}

@Configuration
@Profile("production")
public class ProdConfig {
    @Bean
    public DataSource dataSource() {
        return new MySQLDataSource();
    }
}
```

**Activating profiles:**
```properties
spring.profiles.active=development
```

---

## Spring MVC

Spring MVC is a web framework built on the Servlet API for building web applications.

### Controller Basics

```java
@RestController
@RequestMapping("/api/users")
public class UserController {
    
    private final UserService userService;
    
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }
    
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }
    
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.save(user);
    }
    
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.update(id, user);
    }
    
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }
}
```

### Request Mapping Variants

```java
@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    // Query parameters: /api/products/search?name=laptop&minPrice=500
    @GetMapping("/search")
    public List<Product> search(
        @RequestParam String name,
        @RequestParam(required = false, defaultValue = "0") Double minPrice
    ) {
        return productService.search(name, minPrice);
    }
    
    // Path variables: /api/products/123/reviews
    @GetMapping("/{productId}/reviews")
    public List<Review> getReviews(@PathVariable Long productId) {
        return reviewService.findByProductId(productId);
    }
    
    // Request headers
    @GetMapping("/featured")
    public List<Product> getFeatured(
        @RequestHeader("User-Agent") String userAgent
    ) {
        return productService.getFeatured();
    }
    
    // Multiple path variables
    @GetMapping("/{category}/{subcategory}")
    public List<Product> getByCategory(
        @PathVariable String category,
        @PathVariable String subcategory
    ) {
        return productService.findByCategoryAndSubcategory(category, subcategory);
    }
}
```

### Response Handling

```java
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    
    // Return ResponseEntity for full control
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id) {
        return orderService.findById(id)
            .map(order -> ResponseEntity.ok(order))
            .orElse(ResponseEntity.notFound().build());
    }
    
    // Custom status code
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order created = orderService.save(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    
    // Custom headers
    @GetMapping("/export")
    public ResponseEntity<byte[]> exportOrders() {
        byte[] data = orderService.exportToCsv();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "orders.csv");
        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }
}
```

### Exception Handling

```java
// Global exception handler
@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(
        ResourceNotFoundException ex
    ) {
        ErrorResponse error = new ErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            ex.getMessage(),
            LocalDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidation(
        ValidationException ex
    ) {
        ErrorResponse error = new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            ex.getMessage(),
            LocalDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception ex) {
        ErrorResponse error = new ErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Internal server error",
            LocalDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
```

### Validation

```java
@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @PostMapping
    public ResponseEntity<User> createUser(
        @Valid @RequestBody UserCreateRequest request
    ) {
        User user = userService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}

// DTO with validation
public class UserCreateRequest {
    
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;
    
    @NotNull(message = "Age is required")
    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 120, message = "Age must be less than 120")
    private Integer age;
    
    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Invalid phone number")
    private String phone;
    
    // Getters and setters
}
```

---

## Spring Data JPA

Spring Data JPA simplifies database access with repository abstraction.

### Entity Mapping

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
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;
    
    @ManyToMany
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
    
    // Getters and setters
}
```

### Repository Interface

```java
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    // Query method - derived from method name
    List<User> findByName(String name);
    
    List<User> findByEmailContaining(String email);
    
    Optional<User> findByEmail(String email);
    
    List<User> findByCreatedAtAfter(LocalDateTime date);
    
    // Custom query using JPQL
    @Query("SELECT u FROM User u WHERE u.name = :name AND u.email = :email")
    Optional<User> findByNameAndEmail(
        @Param("name") String name,
        @Param("email") String email
    );
    
    // Native SQL query
    @Query(value = "SELECT * FROM users WHERE created_at > :date", nativeQuery = true)
    List<User> findUsersCreatedAfter(@Param("date") LocalDateTime date);
    
    // Update query
    @Modifying
    @Query("UPDATE User u SET u.name = :name WHERE u.id = :id")
    int updateUserName(@Param("id") Long id, @Param("name") String name);
    
    // Delete query
    @Modifying
    @Query("DELETE FROM User u WHERE u.createdAt < :date")
    int deleteOldUsers(@Param("date") LocalDateTime date);
    
    // Pagination and sorting
    Page<User> findByDepartmentId(Long departmentId, Pageable pageable);
    
    // Projection
    @Query("SELECT u.name as name, u.email as email FROM User u")
    List<UserProjection> findAllProjected();
}

// Projection interface
public interface UserProjection {
    String getName();
    String getEmail();
}
```

### Service Layer

```java
@Service
@Transactional
public class UserService {
    
    private final UserRepository userRepository;
    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
    
    public User save(User user) {
        return userRepository.save(user);
    }
    
    public User update(Long id, User userDetails) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        
        return userRepository.save(user);
    }
    
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
    
    @Transactional(readOnly = true)
    public Page<User> findPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        return userRepository.findAll(pageable);
    }
}
```

---

## Spring Security

Spring Security provides comprehensive security services for Java applications.

### Basic Security Configuration

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/public/**").permitAll()
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                .requestMatchers("/api/user/**").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .httpBasic(Customizer.withDefaults());
        
        return http.build();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```

### JWT Authentication

```java
@Component
public class JwtTokenProvider {
    
    @Value("${jwt.secret}")
    private String jwtSecret;
    
    @Value("${jwt.expiration}")
    private long jwtExpiration;
    
    public String generateToken(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpiration);
        
        return Jwts.builder()
            .setSubject(userDetails.getUsername())
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact();
    }
    
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
            .setSigningKey(jwtSecret)
            .parseClaimsJws(token)
            .getBody();
        
        return claims.getSubject();
    }
    
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
```

### User Details Service

```java
@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    private final UserRepository userRepository;
    
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        
        return org.springframework.security.core.userdetails.User
            .withUsername(user.getUsername())
            .password(user.getPassword())
            .authorities(user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList()))
            .build();
    }
}
```

### Method Security

```java
@Configuration
@EnableMethodSecurity
public class MethodSecurityConfig {
}

@Service
public class UserService {
    
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(Long id) {
        // Only admins can delete users
    }
    
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public User getUser(Long id) {
        // Users and admins can view
    }
    
    @PreAuthorize("#username == authentication.principal.username")
    public User updateProfile(String username, UserUpdateRequest request) {
        // Users can only update their own profile
    }
    
    @PostAuthorize("returnObject.username == authentication.principal.username")
    public User getUserProfile(Long id) {
        // Verify after execution that user owns the profile
    }
}
```

---

## Spring AOP

Aspect-Oriented Programming allows separation of cross-cutting concerns.

### AOP Configuration

```java
@Configuration
@EnableAspectJAutoProxy
public class AopConfig {
}
```

### Logging Aspect

```java
@Aspect
@Component
@Slf4j
public class LoggingAspect {
    
    // Before advice
    @Before("execution(* com.example.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Executing: {}", joinPoint.getSignature().getName());
    }
    
    // After returning advice
    @AfterReturning(
        pointcut = "execution(* com.example.service.*.*(..))",
        returning = "result"
    )
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("Method {} returned: {}", 
            joinPoint.getSignature().getName(), result);
    }
    
    // After throwing advice
    @AfterThrowing(
        pointcut = "execution(* com.example.service.*.*(..))",
        throwing = "error"
    )
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        log.error("Method {} threw exception: {}", 
            joinPoint.getSignature().getName(), error.getMessage());
    }
    
    // Around advice
    @Around("execution(* com.example.service.*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        
        Object result = joinPoint.proceed();
        
        long executionTime = System.currentTimeMillis() - start;
        log.info("Method {} executed in {} ms", 
            joinPoint.getSignature().getName(), executionTime);
        
        return result;
    }
}
```

### Custom Annotations with AOP

```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TrackExecutionTime {
}

@Aspect
@Component
@Slf4j
public class PerformanceAspect {
    
    @Around("@annotation(TrackExecutionTime)")
    public Object trackTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long duration = System.currentTimeMillis() - start;
        
        log.info("{} took {} ms", joinPoint.getSignature().getName(), duration);
        return result;
    }
}

// Usage
@Service
public class UserService {
    
    @TrackExecutionTime
    public List<User> findAll() {
        // Method execution time will be logged
        return userRepository.findAll();
    }
}
```

---

## Configuration Management

### Externalized Configuration

```java
@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {
    
    @Value("${database.url}")
    private String databaseUrl;
    
    @Bean
    public DataSource dataSource(
        @Value("${database.url}") String url,
        @Value("${database.username}") String username,
        @Value("${database.password}") String password
    ) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        return new HikariDataSource(config);
    }
}
```

### Environment-Specific Configuration

```yaml
# application-dev.yml
spring:
  datasource:
    url: jdbc:h2:mem:testdb
  jpa:
    show-sql: true

# application-prod.yml
spring:
  datasource:
    url: jdbc:mysql://prod-server:3306/mydb
  jpa:
    show-sql: false
```

### Configuration Validation

```java
@ConfigurationProperties(prefix = "app")
@Validated
@Component
public class AppProperties {
    
    @NotBlank
    private String name;
    
    @Min(1)
    @Max(65535)
    private int port;
    
    @Valid
    private Security security = new Security();
    
    public static class Security {
        @NotBlank
        private String jwtSecret;
        
        @Positive
        private long jwtExpiration;
        
        // Getters and setters
    }
    
    // Getters and setters
}
```

---

## Best Practices

### 1. Dependency Injection

✅ **Do:**
- Prefer constructor injection for required dependencies
- Use `@RequiredArgsConstructor` from Lombok for cleaner code
- Keep dependencies immutable with `final` fields

❌ **Don't:**
- Avoid field injection (makes testing difficult)
- Don't create circular dependencies

```java
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final EmailService emailService;
}
```

### 2. Transaction Management

✅ **Do:**
- Use `@Transactional` at service layer
- Mark read-only operations with `@Transactional(readOnly = true)`
- Keep transactions as short as possible

❌ **Don't:**
- Don't use transactions in controllers
- Avoid long-running transactions

```java
@Service
@Transactional
public class UserService {
    
    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
    
    public User save(User user) {
        // Transaction committed automatically
        return userRepository.save(user);
    }
}
```

### 3. Exception Handling

✅ **Do:**
- Use `@ControllerAdvice` for global exception handling
- Create custom exception classes
- Return meaningful error responses

```java
@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFound(ResourceNotFoundException ex) {
        ApiError error = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
```

### 4. API Design

✅ **Do:**
- Use proper HTTP methods (GET, POST, PUT, DELETE)
- Follow REST conventions
- Use DTOs to separate internal models from API contracts
- Version your APIs

```java
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    
    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable Long id) {
        User user = userService.findById(id);
        return userMapper.toResponse(user);
    }
}
```

### 5. Testing

✅ **Do:**
- Write unit tests for services
- Write integration tests for repositories
- Use `@WebMvcTest` for controller tests
- Use `@DataJpaTest` for repository tests

```java
@SpringBootTest
class UserServiceTest {
    
    @Autowired
    private UserService userService;
    
    @MockBean
    private UserRepository userRepository;
    
    @Test
    void testFindById() {
        User user = new User(1L, "John Doe");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        
        User found = userService.findById(1L);
        
        assertNotNull(found);
        assertEquals("John Doe", found.getName());
    }
}
```

### 6. Security

✅ **Do:**
- Always validate and sanitize user input
- Use parameterized queries to prevent SQL injection
- Implement proper authentication and authorization
- Hash passwords using BCrypt or similar

```java
@Service
public class UserService {
    
    private final PasswordEncoder passwordEncoder;
    
    public User createUser(UserCreateRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return userRepository.save(user);
    }
}
```

### 7. Performance

✅ **Do:**
- Use pagination for large datasets
- Implement caching for frequently accessed data
- Use lazy loading for relationships when appropriate
- Monitor and optimize database queries

```java
@Service
public class UserService {
    
    @Cacheable(value = "users", key = "#id")
    public User findById(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
    
    @CacheEvict(value = "users", key = "#user.id")
    public User update(User user) {
        return userRepository.save(user);
    }
}
```

### 8. Code Organization

✅ **Do:**
- Follow layered architecture (Controller → Service → Repository)
- Keep controllers thin
- Put business logic in service layer
- Use meaningful package names

```
src/main/java/com/example/app/
├── controller/
│   └── UserController.java
├── service/
│   └── UserService.java
├── repository/
│   └── UserRepository.java
├── model/
│   └── User.java
├── dto/
│   ├── UserCreateRequest.java
│   └── UserResponse.java
├── exception/
│   └── ResourceNotFoundException.java
└── config/
    └── SecurityConfig.java
```

---

## Additional Resources

- [Spring Framework Documentation](https://spring.io/projects/spring-framework)
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Data JPA Documentation](https://spring.io/projects/spring-data-jpa)
- [Spring Security Documentation](https://spring.io/projects/spring-security)
- [Baeldung Spring Tutorials](https://www.baeldung.com/spring-tutorial)

---

## Contributing

Feel free to contribute to this guide by:
1. Forking the repository
2. Creating a feature branch
3. Making your changes
4. Submitting a pull request

---

## License

This guide is provided as-is for educational purposes.

---

**Last Updated:** February 2026