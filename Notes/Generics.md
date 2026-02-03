
## Generics in Java — Why Needed, Advantages & Disadvantages

### What are Generics in Java?

**Generics** allow you to write **classes, interfaces, and methods with a placeholder type**, so the **actual data type is specified later**, at compile time.

They enable **type-safe reusable code**.

Example:

`class  Box<T> { private T value; public  void  set(T value) { this.value = value;
    } public T get() { return value;
    }
}` 

Usage:

`Box<Integer> intBox = new  Box<>();
intBox.set(10);

Box<String> strBox = new  Box<>();
strBox.set("Hello");` 

----------

## Why Were Generics Needed?

Before Java 5, collections stored objects as `Object`.

### Problem before Generics

`List  list  =  new  ArrayList();
list.add("Hello"); Integer  num  = (Integer) list.get(0); // Runtime error` 

Issues:

-   Manual type casting required
    
-   Runtime `ClassCastException`
    
-   No compile-time type safety
    

Generics solved this:

`List<String> list = new  ArrayList<>();
list.add("Hello"); String  s  = list.get(0); // No casting needed` 

Now errors are caught **at compile time**, not runtime.

----------

## Advantages of Generics

### 1) Compile-time Type Safety

Errors caught early.

`List<String> list = new  ArrayList<>();
list.add(10); // Compile-time error` 

----------

### 2) No Need for Type Casting

Cleaner code.

`String  s  = list.get(0);` 

----------

### 3) Code Reusability

Same code works for multiple types.

`Box<Integer>
Box<String>
Box<Double>` 

----------

### 4) Better Readability & Maintenance

Code clearly shows intended data type.

----------

### 5) Works Well with Collections Framework

Used extensively in:

-   `List<T>`
    
-   `Set<T>`
    
-   `Map<K,V>`
    
-   Streams & Lambdas
    

----------

### 6) Generic Methods

Methods can be generic too.

`public  static <T> void  print(T data) {
    System.out.println(data);
}` 

----------

## Disadvantages / Limitations of Generics

### 1) Type Erasure

Generic type information is removed at runtime.

Example:

`List<String> list1 = new  ArrayList<>();
List<Integer> list2 = new  ArrayList<>();

System.out.println(list1.getClass() == list2.getClass()); // true` 

Both become `ArrayList` at runtime.

----------

### 2) Cannot Use Primitive Types

Only objects allowed.

❌ Invalid:

`List<int>` 

✅ Use wrapper:

`List<Integer>` 

----------

### 3) Cannot Create Generic Arrays

`T[] arr = new  T[10]; // Not allowed` 

----------

### 4) Static Members Cannot Use Type Parameter

`class  Box<T> { static T value; // Not allowed }` 

----------

### 5) Complex Syntax

Wildcards and bounds can become confusing.

Example:

`List<? extends  Number>
List<? super Integer>` 

----------


## Summary Table

| Feature         | Without Generics | With Generics |
|-----------------|------------------|---------------|
| Type Safety     | Runtime          | Compile-time  |
| Casting Needed  | Yes              | No            |
| Readability     | Lower            | Higher        |
| Reusability     | Limited          | High          |


----------

## Quick Interview Answer (Short)

**Generics provide compile-time type safety, eliminate casting, improve code reuse, and make collections safer and cleaner, though they introduce complexity and suffer from type erasure limitations.**
