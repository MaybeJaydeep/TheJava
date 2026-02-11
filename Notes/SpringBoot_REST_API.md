# Spring Boot REST API

## REST Principles

REST (Representational State Transfer) is an architectural style for designing networked applications.

### Key Principles

1. **Stateless**: Each request contains all information needed
2. **Client-Server**: Separation of concerns
3. **Cacheable**: Responses can be cached
4. **Uniform Interface**: Standard HTTP methods
5. **Layered System**: Architecture can be composed of layers

## HTTP Methods

- **GET**: Retrieve resources
- **POST**: Create new resources
- **PUT**: Update existing resources (full update)
- **PATCH**: Partial update of resources
- **DELETE**: Remove resources

## Creating REST Controllers

### Basic Controller

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

## Request Mapping

### Path Variables

```java
@GetMapping("/users/{id}")
public User getUser(@PathVariable Long id) {
    return userService.findById(id);
}

@GetMapping("/users/{userId}/orders/{orderId}")
public Order getOrder(
    @PathVariable Long userId,
    @PathVariable Long orderId
) {
    return orderService.findByUserAndId(userId, orderId);
}
```

### Query Parameters

```java
@GetMapping("/users/search")
public List<User> searchUsers(
    @RequestParam String name,
    @RequestParam(required = false) String email,
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size
) {
    return userService.search(name, email, page, size);
}
```

### Request Body

```java
@PostMapping("/users")
public User createUser(@RequestBody UserCreateRequest request) {
    return userService.create(request);
}
```

### Request Headers

```java
@GetMapping("/users")
public List<User> getUsers(
    @RequestHeader("Authorization") String token,
    @RequestHeader(value = "User-Agent", required = false) String userAgent
) {
    return userService.findAll();
}
```

## Response Handling

### ResponseEntity

```java
@GetMapping("/{id}")
public ResponseEntity<User> getUser(@PathVariable Long id) {
    return userService.findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
}

@PostMapping
public ResponseEntity<User> createUser(@RequestBody User user) {
    User created = userService.save(user);
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(created);
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    userService.delete(id);
    return ResponseEntity.noContent().build();
}
```

### Custom Headers

```java
@GetMapping("/download")
public ResponseEntity<byte[]> downloadFile() {
    byte[] data = fileService.getFile();
    
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_PDF);
    headers.setContentDispositionFormData("attachment", "file.pdf");
    
    return new ResponseEntity<>(data, headers, HttpStatus.OK);
}
```

## DTOs (Data Transfer Objects)

### Request DTO

```java
public class UserCreateRequest {
    private String name;
    private String email;
    private String password;
    
    // Getters and setters
}
```

### Response DTO

```java
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private LocalDateTime createdAt;
    
    // Getters and setters
}
```

### Mapper

```java
@Component
public class UserMapper {
    
    public User toEntity(UserCreateRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        return user;
    }
    
    public UserResponse toResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setCreatedAt(user.getCreatedAt());
        return response;
    }
}
```

## Validation

### Bean Validation

```java
public class UserCreateRequest {
    
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100)
    private String name;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;
    
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;
    
    @Min(18)
    @Max(120)
    private Integer age;
    
    // Getters and setters
}
```

### Controller Validation

```java
@PostMapping
public ResponseEntity<UserResponse> createUser(
    @Valid @RequestBody UserCreateRequest request
) {
    User user = userService.create(request);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(userMapper.toResponse(user));
}
```

## Exception Handling

### Custom Exceptions

```java
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
```

### Global Exception Handler

```java
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
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(
        MethodArgumentNotValidException ex
    ) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
            errors.put(error.getField(), error.getDefaultMessage())
        );
        
        ErrorResponse error = new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            "Validation failed",
            LocalDateTime.now(),
            errors
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

### Error Response

```java
public class ErrorResponse {
    private int status;
    private String message;
    private LocalDateTime timestamp;
    private Map<String, String> errors;
    
    // Constructors, getters, and setters
}
```

## Pagination and Sorting

```java
@GetMapping
public Page<UserResponse> getUsers(
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size,
    @RequestParam(defaultValue = "id") String sortBy,
    @RequestParam(defaultValue = "asc") String direction
) {
    Sort.Direction sortDirection = direction.equalsIgnoreCase("desc") 
        ? Sort.Direction.DESC 
        : Sort.Direction.ASC;
    
    Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));
    
    return userService.findAll(pageable)
        .map(userMapper::toResponse);
}
```

## CORS Configuration

```java
@Configuration
public class CorsConfig {
    
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                    .allowedOrigins("http://localhost:3000")
                    .allowedMethods("GET", "POST", "PUT", "DELETE")
                    .allowedHeaders("*")
                    .allowCredentials(true);
            }
        };
    }
}
```

Or using annotation:

```java
@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
}
```

## Content Negotiation

```java
@GetMapping(value = "/{id}", produces = {
    MediaType.APPLICATION_JSON_VALUE,
    MediaType.APPLICATION_XML_VALUE
})
public User getUser(@PathVariable Long id) {
    return userService.findById(id);
}
```

## API Versioning

### URI Versioning

```java
@RestController
@RequestMapping("/api/v1/users")
public class UserControllerV1 {
}

@RestController
@RequestMapping("/api/v2/users")
public class UserControllerV2 {
}
```

### Header Versioning

```java
@GetMapping(headers = "API-Version=1")
public List<User> getUsersV1() {
    return userService.findAllV1();
}

@GetMapping(headers = "API-Version=2")
public List<User> getUsersV2() {
    return userService.findAllV2();
}
```

## Best Practices

1. Use proper HTTP status codes
2. Use DTOs instead of exposing entities
3. Implement global exception handling
4. Validate input data
5. Use pagination for large datasets
6. Version your APIs
7. Document APIs (Swagger/OpenAPI)
8. Follow REST naming conventions
9. Keep controllers thin, business logic in services
10. Use ResponseEntity for full control over responses
