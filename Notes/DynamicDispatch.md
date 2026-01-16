# Dynamic Dispatch (Late Binding)

## Definition
**Dynamic Dispatch** is the process of deciding **which method to call at RUNTIME** based on the **actual object type**, not the reference type. Also called **Late Binding** or **Runtime Polymorphism**.

---

## Key Characteristics

| Feature | Detail |
|---------|--------|
| **Binding Time** | RUNTIME (execution time) |
| **Decision Based On** | Actual object type |
| **Applicable To** | Instance methods (overridden methods) |
| **NOT Applicable To** | Static methods, private methods, final methods |

---

## How It Works

```
Reference Type → Compile-time check (what methods are available?)
                 ↓
Object Type → Runtime execution (which actual method to execute?)
```

### Example
```java
Vehicle vehicleRef = new Car();  // Reference type: Vehicle, Object type: Car

vehicleRef.drive();  // Compiler sees: Vehicle class has drive() method ✓
                     // Runtime executes: Car's drive() method
```

---

## Dynamic Dispatch vs Static Binding

### Dynamic Dispatch (Instance Methods)
```java
class Vehicle {
    void drive() {
        System.out.println("Vehicle driving");
    }
}

class Car extends Vehicle {
    @Override
    void drive() {
        System.out.println("Car driving smoothly");
    }
}

Vehicle v = new Car();
v.drive();  // Calls Car's drive() at RUNTIME ✓ DYNAMIC DISPATCH
```

**Output:** `Car driving smoothly`

### Static Binding (Static Methods)
```java
class Vehicle {
    static void getMaxSpeed() {
        System.out.println("Vehicle max speed");
    }
}

class Car extends Vehicle {
    static void getMaxSpeed() {
        System.out.println("Car max speed");
    }
}

Vehicle v = new Car();
v.getMaxSpeed();  // Calls Vehicle's getMaxSpeed() at COMPILE-TIME ✗ NOT DYNAMIC
```

**Output:** `Vehicle max speed` (decided at compile-time based on reference type)

---

## Method Resolution Process

### At COMPILE TIME:
1. Compiler checks if method exists in **reference type's class**
2. If found → Compilation succeeds
3. If not found → Compilation error

### At RUNTIME:
1. JVM looks at **actual object's class**
2. Executes that class's version of method
3. If not overridden, calls parent's version

---

## Practical Example

```java
Animal[] animals = {
    new Dog(),
    new Cat(),
    new Bird()
};

for (Animal animal : animals) {
    animal.makeSound();  // DYNAMIC DISPATCH
}
```

**Output:**
```
Dog: Woof! Woof!
Cat: Meow! Meow!
Bird: Tweet! Tweet!
```

**How it works:**
- All references are `Animal` type (compile-time check)
- Each actual object type's `makeSound()` is called (runtime decision)

---

## Methods That Use Dynamic Dispatch

✅ **Instance Methods** (if overridden)
```java
@Override
void instanceMethod() { }
```

---

## Methods That DON'T Use Dynamic Dispatch

❌ **Static Methods** (resolved at compile-time)
```java
static void staticMethod() { }
```

❌ **Private Methods** (not accessible in subclass)
```java
private void privateMethod() { }
```

❌ **Final Methods** (cannot be overridden)
```java
final void finalMethod() { }
```

---

## Advantages of Dynamic Dispatch

1. **Code Reusability** - Write code for parent type, works with all subtypes
2. **Flexibility** - Easy to add new types without changing existing code
3. **Loose Coupling** - Code doesn't depend on specific implementations
4. **Collections** - Store different types in single collection

---

## Quick Revision Checklist

- [ ] Dynamic Dispatch = Method call decision at RUNTIME
- [ ] Based on ACTUAL OBJECT TYPE
- [ ] Only for instance methods
- [ ] Static methods use compile-time binding
- [ ] Must be method OVERRIDING (@Override)
- [ ] Enables polymorphism in collections
- [ ] Compiler checks reference type
- [ ] JVM executes actual object's method

---

## Real-World Analogy

**Restaurant Order System:**
```
Reference: "Cook" (interface/parent)
Objects: "Chef at Indian restaurant", "Chef at Italian restaurant"

When order comes in:
- Compiler checks: "Cook interface has prepare() method?" ✓
- Runtime executes: "Which actual cook? What's their specialty?"
  - Indian Chef → Cooks Indian food
  - Italian Chef → Cooks Italian food
```

Same interface, different implementations executed at runtime!
