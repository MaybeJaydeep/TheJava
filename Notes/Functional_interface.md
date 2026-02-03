
# Functional Interface in Java — Complete Notes

## 1️⃣ What is a Functional Interface?

A **Functional Interface** in Java is an interface that contains **exactly one abstract method** (SAM — Single Abstract Method).

It can have:

-   ✅ One abstract method (mandatory)
    
-   ✅ Any number of default methods
    
-   ✅ Any number of static methods
    
-   ✅ Methods from `Object` class (equals, hashCode, toString) — do NOT count
    

Functional interfaces are the **foundation of Lambda Expressions** and **functional programming style in Java**.

----------

## 2️⃣ Why Functional Interfaces Were Introduced

Introduced in **Java 8** to support:

-   Lambda expressions
    
-   Method references
    
-   Stream API
    
-   Functional programming style
    
-   More concise code
    
-   Better callback handling
    
-   Behavior passing as parameter
    

Before Java 8 → Anonymous classes  
After Java 8 → Lambdas using functional interfaces

----------

## 3️⃣ Basic Example

`@FunctionalInterface  interface  Calculator { int  operate(int a, int b);
}` 

Lambda usage:

`Calculator  add  = (a, b) -> a + b;
System.out.println(add.operate(5, 3));` 

----------

## 4️⃣ @FunctionalInterface Annotation

This annotation is **optional but recommended**.

### Purpose:

-   Ensures interface has only one abstract method
    
-   Compiler throws error if violated
    
-   Improves readability and design intent
    

Example error case:

`@FunctionalInterface  interface  Test { void  run(); void  stop(); // ❌ compile error }` 

----------

## 5️⃣ Rules of Functional Interface

A functional interface must follow:

### ✔ Must Have:

-   Exactly one abstract method
    

### ✔ Can Have:

-   Default methods
    
-   Static methods
    
-   Private methods (Java 9+)
    
-   Object class methods
    

----------

### Example with default & static methods

`@FunctionalInterface  interface  Printer { void  print(String msg); default  void  log() {
        System.out.println("Logging...");
    } static  void  info() {
        System.out.println("Printer Interface");
    }
}` 

----------

## 6️⃣ Functional Interface vs Normal Interface

Feature

Functional Interface

Normal Interface

Abstract Methods

Only 1

Any number

Lambda Support

Yes

No

Annotation

@FunctionalInterface

Not required

Usage

Behavior passing

Contract design

----------

# 7️⃣ Built-in Functional Interfaces (java.util.function)

Java provides many ready-made functional interfaces.

## 🔹 Core Categories

----------

## 🟢 Supplier — produces data

`T get()` 

No input → returns value

`Supplier<Double> random = () -> Math.random();` 

Use cases:

-   Lazy initialization
    
-   Object factories
    

----------

## 🔵 Consumer — consumes data

`void  accept(T t)` 

Takes input → returns nothing

`Consumer<String> print = s -> System.out.println(s);` 

Use cases:

-   Logging
    
-   Printing
    
-   Database write
    

----------

## 🟣 Predicate — condition tester

`boolean  test(T t)` 

`Predicate<Integer> isEven = n -> n % 2 == 0;` 

Use cases:

-   Filtering
    
-   Validation
    
-   Conditions
    

----------

## 🟠 Function — transform input to output

`R  apply(T t)` 

`Function<String, Integer> length = s -> s.length();` 

Use cases:

-   Mapping
    
-   Conversion
    
-   Transformation
    

----------

## 🔴 UnaryOperator — same type in/out

Extends Function<T,T>

`UnaryOperator<Integer> square = x -> x*x;` 

----------

## 🟤 BinaryOperator — two inputs, same type output

Extends BiFunction<T,T,T>

`BinaryOperator<Integer> add = (a,b) -> a+b;` 

----------

# 8️⃣ Bi-Functional Interfaces

Take two inputs.

Interface

Method

BiConsumer

accept(T,U)

BiFunction

apply(T,U)

BiPredicate

test(T,U)

Example:

`BiFunction<Integer,Integer,Integer> sum = (a,b) -> a+b;` 

----------

# 9️⃣ Primitive Specializations (Performance Optimization)

Avoids boxing/unboxing overhead.

Examples:

`IntPredicate LongSupplier
DoubleConsumer
IntFunction` 

Example:

`IntPredicate  even  = n -> n % 2 == 0;` 

Why important?

-   Faster
    
-   Memory efficient
    
-   Avoids wrapper class overhead
    

----------

# 🔟 Lambda Expressions and Functional Interfaces

Lambda = Implementation of functional interface method.

### Syntax:

`(parameters) ->  expression (parameters) -> { statements }` 

Example:

`Runnable  r  = () -> System.out.println("Running");` 

Behind the scenes:

-   Compiler converts lambda → functional interface instance
    
-   Uses invokedynamic bytecode instruction
    

----------

# 1️⃣1️⃣ Method References

Shortcut syntax for lambdas.

Types:

### Static method reference

`Function<String,Integer> f = Integer::parseInt;` 

### Instance method reference

`Consumer<String> c = System.out::println;` 

### Constructor reference

`Supplier<List> s = ArrayList::new;` 

----------

# 1️⃣2️⃣ Functional Interface Composition

Many functional interfaces support chaining.

## Predicate chaining

`Predicate<Integer> positive = x -> x > 0;
Predicate<Integer> even = x -> x % 2 == 0;

positive.and(even).test(4);` 

Methods:

`and() or() negate()` 

----------

## Function chaining

`Function<Integer,Integer> doubleVal = x -> x * 2;
Function<Integer,Integer> square = x -> x * x;

doubleVal.andThen(square).apply(3);` 

Methods:

`andThen() compose()` 

----------

# 1️⃣3️⃣ Functional Interface & Streams

Streams heavily use functional interfaces.

Example:

`list.stream()
    .filter(x -> x > 10) // Predicate .map(x -> x * 2) // Function .forEach(System.out::println); // Consumer` 

----------

# 1️⃣4️⃣ Custom Functional Interface Design Best Practices

✅ Use @FunctionalInterface  
✅ Keep method generic  
✅ Avoid side effects  
✅ Prefer immutability  
✅ Name behavior clearly  
✅ Use primitive specializations when possible

Example:

`@FunctionalInterface  interface  Validator<T> { boolean  validate(T t);
}` 

----------

# 1️⃣5️⃣ Functional Interface vs Anonymous Class

## Anonymous Class (Old way)

`Runnable  r  =  new  Runnable() { public  void  run() {
        System.out.println("Run");
    }
};` 

## Lambda (New way)

`Runnable  r  = () -> System.out.println("Run");` 

Advantages:

-   Less boilerplate
    
-   More readable
    
-   Better performance (lighter object)
    

----------

# 1️⃣6️⃣ Advanced Concepts (Interview Level)

## 🔹 SAM Conversion

Compiler converts lambda → instance of functional interface.

----------

## 🔹 Target Typing

Lambda type inferred from assignment context.

`Comparator<Integer> c = (a,b) -> a-b;` 

----------

## 🔹 Effectively Final Variables

Lambda can only capture variables that are:

-   final
    
-   effectively final
    

`int  x  =  10; Runnable  r  = () -> System.out.println(x); // ok` 

----------

## 🔹 Checked Exceptions

Functional interfaces do NOT allow checked exceptions unless declared.

Workaround:

-   Wrap exception
    
-   Create custom functional interface
    

----------

# 1️⃣7️⃣ Common Interview Questions

✅ Can functional interface have default methods? → Yes  
✅ Can it extend another interface? → Yes (if still single abstract method total)  
✅ Is Runnable functional? → Yes  
✅ Is Comparator functional? → Yes  
✅ Is Callable functional? → Yes  
✅ Is Marker interface functional? → No (no abstract method)

----------

# 1️⃣8️⃣ Most Important Built-in Functional Interfaces to Remember

`Runnable,  Callable,  Comparator,  Supplier,  Consumer,  Predicate,  Function,  UnaryOperator,  BinaryOperator,  BiFunction,  BiConsumer,  BiPredicate`
