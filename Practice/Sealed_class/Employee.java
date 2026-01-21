package Sealed_class;

public final class Employee extends AbstractHuman {

    private final String role;

    public Employee(String name, int age, String role) {
        super(name, age);
        this.role = role;
    }

    @Override
    public double testosteroneLevel() {
        return 500.0; // varies, not critical here
    }

    @Override
    public void displayInfo() {
        System.out.println("Employee: " + name + ", Role: " + role);
    }
    /*
    
    @Override
    public void displayInfo();    

    Note:
    * Doesn't work because visibility limits is from Human to Employee, thats why it needs implementation.
    * Even though you made the class abstract doesnt mean you dont have to implement displayInfo().
    * If you dont provide the implementation here, then the scope of this displayInfo cannot be overriden by child class.
    */
    



    /* 
    @Override
    void displayInfo(){

    }

    Note:
    *This also doesnt works because you cannot restrict the visibility of the displayInfo() from public(Human) to protected(Employee).
    

    */

    
}
