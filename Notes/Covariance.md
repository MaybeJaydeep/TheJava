
## Covariance in Java (with Generics Context)

### What is Covariance?

Covariance means a type relationship is preserved in the same direction between parent and child types.

If  
Child extends Parent

then covariance allows something like:  
Container<Child> to be treated as Container<Parent> (with restrictions).

----------

## Covariance in Java Arrays

Java arrays are covariant.

Example:

`String[] strings = new  String[5];
Object[] objects = strings; // Allowed objects[0] = 10; // Runtime error` 

Here:

-   `String[]` is treated as `Object[]`
    
-   But inserting an Integer causes `ArrayStoreException` at runtime.
    

So arrays are covariant but not type-safe.

----------

## Generics Are Not Covariant by Default

Generics are invariant.

Example:

`List<String> strList = new  ArrayList<>();
List<Object> objList = strList; // Compile-time error` 

This restriction prevents runtime errors.

----------

## Covariance Using Wildcards

Generics support covariance using wildcards:

`? extends  Type` 

Example:

`List<? extends  Number> list = new  ArrayList<Integer>();` 

Now the list can refer to:

-   List<Integer>
    
-   List<Double>
    
-   List<Float>
    
-   or any subclass of Number.
    

----------

## Limitation of Covariant Lists

You cannot safely add elements:

`list.add(10); // Not allowed` 

Because the compiler does not know the exact subtype.

But reading is safe:

`Number  n  = list.get(0);` 

----------

## PECS Rule (Interview Favorite)

PECS stands for:

Producer Extends, Consumer Super

Meaning:

-   Use `? extends T` when reading data.
    
-   Use `? super T` when inserting data.
    

Example:

`void  read(List<? extends Number> list) {} void  write(List<? super Integer> list) {}` 

----------

## Advantages of Covariance

-   Flexible APIs
    
-   Better abstraction
    
-   Safe data reading
    
-   Useful in library design
    

----------

## Disadvantages

-   Cannot safely insert elements
    
-   Adds syntax complexity
    
-   Confusing for beginners
    

----------

## Quick Interview Answer

Covariance allows using a subtype where a supertype is expected. Arrays in Java are covariant but unsafe, while generics achieve safe covariance using wildcards like `? extends`.
