/*
===== PREVIOUS VERSION (COMMENTED FOR REVISION) =====
WHY IT WAS CONFUSING:
- Parent and Child shared the SAME fields (protected)
- When child modified values, parent's values also changed
- super.display() showed child's modified values, NOT parent's original values

// Parent Class
public class DynamicDispatcher {
    protected String name = "Laptop";
    protected double price = 20.5;
    protected int quantity = 5;

    void display() {
        System.out.println("PARENT CLASS DATA:");
        System.out.println("Name: " + name);
        System.out.println("Price: " + price);
        System.out.println("Quantity: " + quantity);
    }
}

class Derived extends DynamicDispatcher {
    Derived() {
        this.name = "Smartphone";  // PROBLEM: Modifies parent's field!
        this.price = 25.0;
        this.quantity = 10;
    }

    @Override
    void display() {
        System.out.println("CHILD CLASS DATA:");
        System.out.println("Name: " + name);
        System.out.println("Price: " + price);
        System.out.println("Quantity: " + quantity);
    }
}
===== END PREVIOUS VERSION =====
*/

// Parent Class - stores PARENT data
public class DynamicDispatcher {
    protected String name = "Laptop";
    protected double price = 20.5;
    protected int quantity = 5;

    void display() {
        System.out.println(">>> PARENT CLASS DATA (via super) <<<");
        System.out.println("Name: " + name);
        System.out.println("Price: " + price);
        System.out.println("Quantity: " + quantity);
    }
}

// Child Class - stores OWN separate data
class Derived extends DynamicDispatcher {
    // IMPORTANT: Using PRIVATE fields so child has its OWN separate data
    // This prevents child from modifying parent's fields
    private String childName = "Smartphone";
    private double childPrice = 25.0;
    private int childQuantity = 10;

    // Override parent's display method
    @Override
    void display() {
        System.out.println(">>> CHILD CLASS DATA (via this) <<<");
        System.out.println("Name: " + childName);
        System.out.println("Price: " + childPrice);
        System.out.println("Quantity: " + childQuantity);
    }

    // Compare parent vs child data
    void compareParentVsChild() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("COMPARISON: Parent vs Child\n");
        
        System.out.println("1. Calling super.display() (Parent's ORIGINAL values):");
        super.display();
        
        System.out.println("\n2. Calling this.display() (Child's OWN values):");
        this.display();
        
        System.out.println("\n" + "=".repeat(50));
    }

    public static void main(String[] args) {
        
        System.out.println("===== TEST 1: Dynamic Dispatch =====");
        System.out.println("Reference Type: DynamicDispatcher");
        System.out.println("Actual Object: Derived\n");
        DynamicDispatcher obj = new Derived();
        obj.display();  // Calls child's display (Dynamic Dispatch)
        
        System.out.println("\n===== TEST 2: Parent vs Child Comparison =====");
        Derived obj1 = new Derived();
        obj1.compareParentVsChild();
    }
}
