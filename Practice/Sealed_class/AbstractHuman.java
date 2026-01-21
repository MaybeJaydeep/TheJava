package Sealed_class;

public abstract non-sealed class AbstractHuman implements Human {

    protected String name;
    protected int age;

    protected AbstractHuman(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int getAge() {
        return age;
    }
}
 
    

