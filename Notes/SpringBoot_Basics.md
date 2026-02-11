# Spring Boot Basics

## What is Spring Boot?

Spring Boot is an opinionated framework built on top of Spring Framework that simplifies the development of production-ready applications. It eliminates boilerplate configuration and provides auto-configuration based on classpath dependencies.

## Key Features

1. **Auto-Configuration**: Automatically configures Spring application based on dependencies
2. **Starter Dependencies**: Pre-configured dependency descriptors
3. **Embedded Server**: Tomcat, Jetty, or Undertow embedded by default
4. **Production-Ready**: Actuator for monitoring and health checks
5. **No XML Configuration**: Convention over configuration approach

## Creating a Spring Boot Application

### Main Application Class

```java
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

**@SpringBootApplication** combines:
- `@Configuration`: Marks class as configuration source
- `@EnableAutoConfiguration`: Enables auto-configuration
- `@ComponentScan`: Scans for components in current package and sub-packages

## Application Properties

### application.properties

```properties
# Server Configuration
server.port=8080
server.servlet.context-path=/api

# Application Name
spring.application.name=my-app

# Logging
logging.level.root=INFO
logging.level.com.example=DEBUG
logging.file.name=app.log
```

### application.yml

```yaml
server:
  port: 8080
  servlet:
    context-path: /api

spring:
  application:
    name: my-app

logging:
  level:
    root: INFO
    com.example: DEBUG
  file:
    name: app.log
```

## Configuration Properties

### Type-Safe Configuration

```java
@ConfigurationProperties(prefix = "app")
@Component
public class AppProperties {
    private String name;
    private String version;
    private int maxConnections;
    
    // Getters and setters
}
```

```yaml
app:
  name: My Application
  version: 1.0.0
  max-connections: 100
```

### Using Configuration

```java
@Service
public class AppService {
    private final AppProperties appProperties;
    
    public AppService(AppProperties appProperties) {
        this.appProperties = appProperties;
    }
    
    public String getAppInfo() {
        return appProperties.getName() + " v" + appProperties.getVersion();
    }
}
```

## Profiles

Profiles allow environment-specific configuration.

### Defining Profiles

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

### Activating Profiles

```properties
spring.profiles.active=dev
```

Or via command line:
```bash
java -jar app.jar --spring.profiles.active=prod
```

### Profile-Specific Beans

```java
@Configuration
@Profile("dev")
public class DevConfig {
    @Bean
    public DataSource dataSource() {
        return new H2DataSource();
    }
}

@Configuration
@Profile("prod")
public class ProdConfig {
    @Bean
    public DataSource dataSource() {
        return new MySQLDataSource();
    }
}
```

## Dependency Injection

### Constructor Injection (Recommended)

```java
@Service
public class UserService {
    private final UserRepository userRepository;
    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
```

### Using Lombok

```java
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final EmailService emailService;
}
```

## Component Scanning

### Default Scanning

Spring Boot scans the package of the main class and all sub-packages.

```
com.example.app
├── Application.java (main class)
├── controller/
├── service/
└── repository/
```

### Custom Scanning

```java
@SpringBootApplication
@ComponentScan(basePackages = {"com.example.app", "com.example.shared"})
public class Application {
}
```

## Bean Lifecycle

### Initialization

```java
@Component
public class DatabaseConnection {
    
    @PostConstruct
    public void init() {
        System.out.println("Initializing connection");
    }
    
    @PreDestroy
    public void cleanup() {
        System.out.println("Closing connection");
    }
}
```

### Bean Configuration

```java
@Configuration
public class AppConfig {
    
    @Bean(initMethod = "connect", destroyMethod = "disconnect")
    public DatabaseService databaseService() {
        return new DatabaseService();
    }
}
```

## Conditional Configuration

```java
@Configuration
public class DatabaseConfig {
    
    @Bean
    @ConditionalOnProperty(name = "database.type", havingValue = "mysql")
    public DataSource mysqlDataSource() {
        return new MySQLDataSource();
    }
    
    @Bean
    @ConditionalOnMissingBean(DataSource.class)
    public DataSource defaultDataSource() {
        return new H2DataSource();
    }
}
```

## Spring Boot Starters

Common starters:
- `spring-boot-starter-web`: Web applications with REST
- `spring-boot-starter-data-jpa`: JPA with Hibernate
- `spring-boot-starter-security`: Security features
- `spring-boot-starter-test`: Testing dependencies
- `spring-boot-starter-validation`: Bean validation

## Running Spring Boot Application

### Maven

```bash
mvn spring-boot:run
```

### Gradle

```bash
gradle bootRun
```

### JAR

```bash
mvn clean package
java -jar target/app.jar
```

## Best Practices

1. Use constructor injection over field injection
2. Keep configuration in application.yml/properties
3. Use profiles for environment-specific config
4. Leverage auto-configuration when possible
5. Use starter dependencies for common features
6. Keep main class in root package for proper scanning
