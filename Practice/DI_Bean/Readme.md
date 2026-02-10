# SpringLaptop Program - Dependency Injection using Spring

## Overview

The `SpringLaptop` class demonstrates **Dependency Injection (DI)** using Spring Framework's `@Autowired` annotation. Instead of creating dependencies manually, Spring injects required components automatically.

---

## Package Structure

- **`DI_Bean` package**: Contains the Spring-managed class
- **`IoC` package**: Contains dependencies (`CPU` and `GPU`)

---

## Key Components

### Dependencies

`CPU` and `GPU` act as hardware components that serve as external dependencies required by the laptop.

**Example fields:**

```java
private CPU cpu;
private GPU gpu;
```

### Dependency Injection

Spring injects objects automatically using the `@Autowired` annotation.

**Example:**

```java
@Autowired
private CPU cpu;

@Autowired
private GPU gpu;
```

**Key Points:**
- ✅ No manual object creation using `new`
- ✅ Dependencies are provided by Spring container
- ✅ Promotes loose coupling

---

## Core Method: `makeLaptop()`

This method performs the following operations:
1. CPU execution
2. Displaying CPU statistics
3. GPU execution
4. Displaying GPU statistics

**Example:**

```java
public void makeLaptop() {
    cpu.run();
    cpu.displayCPUStats();
    
    gpu.run();
    gpu.displayGPUStats();
}
```

---

## Working Flow

1. **Spring container creates** `CPU` and `GPU` beans
2. **Beans are injected** into `SpringLaptop`
3. **`makeLaptop()` uses** injected components
4. **Laptop operations** execute successfully

### Flow Diagram

```
┌─────────────────────┐
│  Spring Container   │
└──────────┬──────────┘
           │
           ├──> Creates CPU Bean
           ├──> Creates GPU Bean
           │
           ▼
┌─────────────────────┐
│   SpringLaptop      │
│  @Autowired CPU     │◄── Injection
│  @Autowired GPU     │◄── Injection
└──────────┬──────────┘
           │
           ▼
   makeLaptop() Method
```

---

## Complete Code Example

### CPU Class (IoC Package)

```java
package IoC;

import org.springframework.stereotype.Component;

@Component
public class CPU {
    
    public void run() {
        System.out.println("CPU is running...");
    }
    
    public void displayCPUStats() {
        System.out.println("CPU Stats: Intel i7, 3.5 GHz, 8 cores");
    }
}
```

### GPU Class (IoC Package)

```java
package IoC;

import org.springframework.stereotype.Component;

@Component
public class GPU {
    
    public void run() {
        System.out.println("GPU is running...");
    }
    
    public void displayGPUStats() {
        System.out.println("GPU Stats: NVIDIA RTX 3060, 12GB VRAM");
    }
}
```

### SpringLaptop Class (DI_Bean Package)

```java
package DI_Bean;

import IoC.CPU;
import IoC.GPU;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpringLaptop {
    
    @Autowired
    private CPU cpu;
    
    @Autowired
    private GPU gpu;
    
    public void makeLaptop() {
        cpu.run();
        cpu.displayCPUStats();
        
        gpu.run();
        gpu.displayGPUStats();
    }
}
```

### Main Application

```java
import DI_Bean.SpringLaptop;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"DI_Bean", "IoC"})
public class AppConfig {
}

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);
        
        SpringLaptop laptop = context.getBean(SpringLaptop.class);
        laptop.makeLaptop();
    }
}
```

### Expected Output

```
CPU is running...
CPU Stats: Intel i7, 3.5 GHz, 8 cores
GPU is running...
GPU Stats: NVIDIA RTX 3060, 12GB VRAM
```

---

## Advantages

| Advantage | Description |
|-----------|-------------|
| **Loose Coupling** | Components are not tightly bound to each other |
| **Easy Replacement** | Dependencies can be swapped without changing code |
| **Better Testability** | Easy to mock dependencies for unit testing |
| **Cleaner Code** | No manual object creation clutter |
| **Scalable Design** | Easy to add new dependencies |
| **Maintainability** | Changes to dependencies don't affect dependent classes |

---

## Limitations / Cons

| Limitation | Description |
|------------|-------------|
| **Spring Configuration Required** | Needs proper Spring setup and configuration |
| **Debugging Complexity** | Injection issues may be harder to debug than direct instantiation |
| **Framework Dependency** | Adds Spring framework dependency even for small applications |
| **Learning Curve** | Developers need to understand Spring concepts |
| **Runtime Errors** | Some errors only appear at runtime, not compile-time |

---

## Dependency Injection Types in Spring

### 1. Field Injection (Used in this example)

```java
@Autowired
private CPU cpu;
```

**Pros:** Concise, easy to read  
**Cons:** Hard to test, cannot use final fields

### 2. Constructor Injection (Recommended)

```java
private final CPU cpu;
private final GPU gpu;

@Autowired
public SpringLaptop(CPU cpu, GPU gpu) {
    this.cpu = cpu;
    this.gpu = gpu;
}
```

**Pros:** Immutable fields, easier testing, required dependencies are clear  
**Cons:** More verbose

### 3. Setter Injection

```java
private CPU cpu;

@Autowired
public void setCpu(CPU cpu) {
    this.cpu = cpu;
}
```

**Pros:** Optional dependencies, can be changed after creation  
**Cons:** Mutable, dependencies not immediately clear

---

## Best Practices

1. ✅ **Prefer Constructor Injection** over field injection
2. ✅ Use `@Component` annotation on dependency classes
3. ✅ Enable component scanning with `@ComponentScan`
4. ✅ Keep dependencies as interfaces for better abstraction
5. ✅ Use `@Qualifier` when multiple beans of same type exist

### Improved Version with Constructor Injection

```java
@Component
public class SpringLaptop {
    
    private final CPU cpu;
    private final GPU gpu;
    
    @Autowired
    public SpringLaptop(CPU cpu, GPU gpu) {
        this.cpu = cpu;
        this.gpu = gpu;
    }
    
    public void makeLaptop() {
        cpu.run();
        cpu.displayCPUStats();
        
        gpu.run();
        gpu.displayGPUStats();
    }
}
```

---

## Testing Example

### Unit Test with Mockito

```java
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpringLaptopTest {
    
    @Mock
    private CPU cpu;
    
    @Mock
    private GPU gpu;
    
    @InjectMocks
    private SpringLaptop springLaptop;
    
    @Test
    void testMakeLaptop() {
        // When
        springLaptop.makeLaptop();
        
        // Then
        verify(cpu, times(1)).run();
        verify(cpu, times(1)).displayCPUStats();
        verify(gpu, times(1)).run();
        verify(gpu, times(1)).displayGPUStats();
    }
}
```

---

## Conclusion

The SpringLaptop program demonstrates how **Spring Dependency Injection** helps manage dependencies efficiently, making applications:
- **Modular**: Components are independent
- **Maintainable**: Easy to update and modify
- **Testable**: Simple to unit test with mocks
- **Scalable**: Easy to extend with new features

By delegating object creation to the Spring container, developers can focus on business logic rather than managing object lifecycles manually.

---

## Further Reading

- [Spring Framework Documentation](https://spring.io/projects/spring-framework)
- [Dependency Injection Patterns](https://martinfowler.com/articles/injection.html)
- [Spring Bean Lifecycle](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans)
- [Constructor vs Field Injection](https://www.baeldung.com/constructor-injection-in-spring)

---