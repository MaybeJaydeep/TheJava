Date: 13/01/2026

String s1 = new String("Hello World");
String s2 = new String("Hello World");
String s3 = new String("Hello");

✅ s1 and s2 points to same memory location at Heap in SCP(String Constant Pool)
❌ s3 doesn't points to same memory location as s1 and s2

Why?

✅Security (Name and passwords are )
✅Thread Safe 
✅Memory Optimization using SCP

📝String Methods:


#           |           String          |                     String Buffer                     |       StringBuilder
                                               
Thread      |           Not safe ❌     |                     Safe ✅                           |         Not Safe ❌

Performance |           Slow ❌         |                     Fast ✅                           |          Medium ⚠️

Mutuable    |   String are immutable    |       StringBuilder are mutable(modifies in place)     |       StringBuffer are mutable   
            |  (creates new objects on  |                                                        |          (modifies in place)
            |        modification)      |                                                        |

Use Case    | Fixed, unchanging strings |       Multi-threaded string manipulation               |   Single-threaded string manipulation