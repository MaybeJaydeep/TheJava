
Before Java 5, all collections stored data as **Object type**, since `Object` is the parent of all classes.

----------

## Why Collections Used Object

In Java:

`Every  class  extends  Object` 

So collections were designed to store `Object` so they could hold **any type**.

Simplified idea:

`class  ArrayList {
    Object[] data; void  add(Object obj) { ... }
    Object get(int index) { ... }
}` 

Everything becomes Object.

----------

## Example: Collection Without Generics

### Storing Different Types

`import java.util.*; public  class  Main { public  static  void  main(String[] args) { List  list  =  new  ArrayList();

        list.add("Jaydeep");
        list.add(101);
        list.add(99.5);

        System.out.println(list);
    }
}` 

Output:

`[Jaydeep, 101, 99.5]` 

Everything is stored as `Object`.

----------

## What Actually Happens Internally

When inserting:

`list.add("Jaydeep");` 

It becomes:

`Object  obj  =  "Jaydeep";` 

When retrieving:

`Object  obj  = list.get(0);` 

So data always comes back as `Object`.

----------

## Problem 1 — Manual Casting Required

Since return type is Object:

`Object  obj  = list.get(0); String  name  = (String) obj;` 

Or directly:

`String  name  = (String) list.get(0);` 

Casting everywhere makes code messy.

----------

## Problem 2 — Runtime Errors

Example:

`List  list  =  new  ArrayList();

list.add("Jaydeep");
list.add(101); // mistake  String  name  = (String) list.get(1); // Runtime crash` 

Runtime error:

`ClassCastException` 

Compiler doesn't help here.

----------

## Problem 3 — No Type Safety

Anyone can add anything:

`void  addData(List list) {
    list.add(500);
}` 

Now your String list contains integers.

----------

## Problem 4 — Hard to Maintain in Large Codebases

In real projects:

`List  employees  = getEmployees();` 

You must guess:

-   Are these Strings?
    
-   Employee objects?
    
-   IDs?
    

No clarity.

----------

## Problem 5 — Late Error Detection

Errors appear:

-   During runtime
    
-   In production
    
-   Harder to debug
    

----------

## Example Showing Real Pain

### Without Generics

`List  employees  =  new  ArrayList();

employees.add("Jay");
employees.add("Raj");
employees.add(1001); // accidental  for (Object obj : employees) { String  name  = (String) obj; // crash occurs here System.out.println(name);
}` 

Error occurs far away from insertion.

----------

## How Generics Fix This

### With Generics

`List<String> employees = new  ArrayList<>();

employees.add("Jay");
employees.add("Raj"); // employees.add(1001); // Compile-time error` 

Compiler protects you.

----------

## Important Insight

Before generics:

`Collection stores Object  Programmer handles type safety` 

After generics:

`Collection enforces type safety
Compiler handles safety` 

----------

## Why Object-Based Collections Were Dangerous

Because:

`Object can reference ANYTHING` 

So collections allowed mixing:

`String + Integer + Double + Custom Objects` 

And errors appeared only at runtime.

----------

## Interview-Level Explanation

Before generics, collections stored elements as Object, requiring manual casting during retrieval. This caused runtime ClassCastException, lack of type safety, and maintenance issues. Generics shifted type checking to compile time, eliminating these problems.
