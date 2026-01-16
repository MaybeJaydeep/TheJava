/**
 * STATIC KEYWORD DEMONSTRATION
 * 
 * Static members belong to the CLASS, not to individual objects.
 * They are shared across all instances of the class.
 */
public class StaticWorking {
    
    // ========== STATIC VARIABLE ==========
    // Shared across all instances - memory allocated once in class memory area
    static int instanceCount = 0;
    static String companyName = "Bacancy Technology";
    
    // Instance variable - separate copy for each object
    int employeeId;
    String employeeName;
    
    // ========== STATIC BLOCK ==========
    // Executed ONCE when class is loaded into memory (before main, before any object creation)
    // Used for one-time initialization like loading drivers, databases, etc.
    static {
        System.out.println("=== STATIC BLOCK EXECUTED ===");
        System.out.println("Class StaticWorking loaded into memory");
        System.out.println("Company: " + companyName);
    }
    
    // Constructor
    public StaticWorking(int empId, String empName) {
        this.employeeId = empId;
        this.employeeName = empName;
        instanceCount++; // Increment shared static variable
    }
    
    // ========== STATIC METHOD ==========
    // Called on CLASS, not on objects: StaticWorking.getCompany()
    // Can only access static variables and static methods
    // Cannot use 'this' or 'super'
    static void displayCompanyInfo() {
        System.out.println("Company: " + companyName);
        System.out.println("Total Employees: " + instanceCount);
        // System.out.println(employeeId); // ERROR - cannot access instance variable
    }
    
    // Instance method - can access both static and instance members
    void displayEmployeeInfo() {
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Employee Name: " + employeeName);
        System.out.println("Company: " + companyName); // Can access static variable
    }
    
    // Static method that returns static variable
    static int getTotalEmployees() {
        return instanceCount;
    }
    
    // ========== WHY STATIC METHODS CANNOT BE OVERRIDDEN ==========
    // This is METHOD SHADOWING, not OVERRIDING
    // Static methods are resolved at COMPILE TIME (static binding)
    // Instance methods are resolved at RUNTIME (dynamic binding)
    
    static void staticMethod() {
        System.out.println("This is static method in Parent");
    }
    
    void instanceMethod() {
        System.out.println("This is instance method in Parent");
    }
    
    public static void main(String[] args) {
        System.out.println("\n========== STATIC KEYWORD PRACTICAL EXAMPLES ==========\n");
        
        // ========== STATIC VARIABLE USAGE ==========
        System.out.println("--- 1. STATIC VARIABLE USAGE ---");
        System.out.println("Company Name (static): " + StaticWorking.companyName);
        System.out.println("Before creating objects - Instance Count: " + instanceCount);
        
        // Create first employee
        StaticWorking emp1 = new StaticWorking(101, "Rajesh Kumar");
        System.out.println("After emp1 - Instance Count: " + StaticWorking.instanceCount);
        
        // Create second employee
        StaticWorking emp2 = new StaticWorking(102, "Priya Singh");
        System.out.println("After emp2 - Instance Count: " + StaticWorking.instanceCount);
        
        // Create third employee
        StaticWorking emp3 = new StaticWorking(103, "Amit Patel");
        System.out.println("After emp3 - Instance Count: " + StaticWorking.instanceCount);
        
        // All objects share the same static variable
        System.out.println("emp1 sees instanceCount as: " + emp1.instanceCount);
        System.out.println("emp2 sees instanceCount as: " + emp2.instanceCount);
        System.out.println("emp3 sees instanceCount as: " + emp3.instanceCount);
        
        // ========== STATIC METHOD USAGE ==========
        System.out.println("\n--- 2. STATIC METHOD USAGE ---");
        StaticWorking.displayCompanyInfo(); // Called on class, not object
        System.out.println("Total Employees via method: " + StaticWorking.getTotalEmployees());
        
        // ========== INSTANCE METHOD USAGE ==========
        System.out.println("\n--- 3. INSTANCE METHOD USAGE ---");
        emp1.displayEmployeeInfo();
        System.out.println();
        emp2.displayEmployeeInfo();
        
        // ========== STATIC VARIABLE MODIFICATION ==========
        System.out.println("\n--- 4. STATIC VARIABLE MODIFICATION ---");
        StaticWorking.companyName = "Bacancy Tech Solutions"; // Modified via class
        System.out.println("After modification via class: " + StaticWorking.companyName);
        System.out.println("All objects see updated value: " + emp1.companyName);
        
        // ========== DATA HIDING - WHY STATIC METHODS CANNOT BE OVERRIDDEN ==========
        System.out.println("\n--- 5. DATA HIDING - METHOD SHADOWING (Not Overriding) ---");
        demonstrateMethodShadowing();
    }
    
    static void demonstrateMethodShadowing() {
        System.out.println("\n[METHOD SHADOWING vs OVERRIDING]\n");
        
        Parent parent = new Parent();
        Child child = new Child();
        Parent parentRef = new Child(); // Polymorphic reference
        
        System.out.println("--- STATIC METHODS (Compile-Time Binding) ---");
        parent.staticMethod(); // Calls Parent's static method
        child.staticMethod();  // Calls Child's static method (shadowing, not overriding)
        parentRef.staticMethod(); // Calls Parent's static method (compile-time)
        System.out.println("↑ Static method resolution is COMPILE-TIME (compile sees the reference type)");
        
        System.out.println("\n--- INSTANCE METHODS (Runtime Binding) ---");
        parent.instanceMethod(); // Calls Parent's instance method
        child.instanceMethod();  // Calls Child's instance method (overriding)
        parentRef.instanceMethod(); // Calls Child's instance method (runtime)
        System.out.println("↑ Instance method resolution is RUNTIME (execution sees the actual object type)");
        
        System.out.println("\n[WHY THIS IS DATA HIDING]");
        System.out.println("- Static methods CANNOT be overridden (they're bound at compile-time)");
        System.out.println("- Child class method 'shadows' parent's static method, not override");
        System.out.println("- Polymorphism doesn't work with static methods (compiler uses reference type)");
        System.out.println("- This prevents accidental method signature changes in inheritance");
    }
}

// ========== PARENT CLASS ==========
class Parent {
    static void staticMethod() {
        System.out.println("Parent's STATIC method");
    }
    
    void instanceMethod() {
        System.out.println("Parent's INSTANCE method");
    }
}

// ========== CHILD CLASS ==========
class Child extends Parent {
    // This is METHOD SHADOWING (not overriding) - static methods can't be overridden
    static void staticMethod() {
        System.out.println("Child's STATIC method (shadows parent's static method)");
    }
    
    // This is METHOD OVERRIDING (runtime polymorphism)
    @Override
    void instanceMethod() {
        System.out.println("Child's INSTANCE method (overrides parent's instance method)");
    }
}
