# Spring Boot Practice Examples

This folder contains practice code for Spring Boot concepts.

## Structure

### 1. BasicApp
- `Application.java` - Main Spring Boot application class
- `AppConfig.java` - Configuration and Bean creation
- `HelloController.java` - Simple REST endpoints

### 2. DependencyInjection
- `UserService.java` - Service layer with constructor injection
- `UserRepository.java` - Repository layer
- `EmailService.java` - Another service demonstrating DI

### 3. RestAPI
- `Product.java` - Model class
- `ProductController.java` - Full CRUD REST API

### 4. ExceptionHandling
- `CustomException.java` - Custom exception class
- `GlobalExceptionHandler.java` - Global exception handler with @ControllerAdvice
- `ErrorResponse.java` - Standard error response structure

## Key Concepts Demonstrated

1. **@SpringBootApplication** - Main application annotation
2. **@RestController** - REST API controllers
3. **@Service, @Repository** - Stereotype annotations
4. **Constructor Injection** - Dependency injection pattern
5. **CRUD Operations** - GET, POST, PUT, DELETE
6. **Exception Handling** - @ControllerAdvice and custom exceptions
7. **ResponseEntity** - HTTP response handling

## Notes

- These are standalone examples for learning purposes
- In a real application, you would need proper database configuration
- Add Spring Boot dependencies in pom.xml or build.gradle to run these
