package Sealed_class;

public sealed interface Human permits AbstractHuman {

    // Every human must be able to display basic info
    void displayInfo();

    // Biological behavior common to all humans
    double testosteroneLevel();

    // Default hospital utility
    default boolean isAdult() {
        return getAge() >= 18;
    }

    // Must be implemented but hidden from outside usage
    int getAge();
}

/* public sealed interface Human permits Male, Female, Boy, Girl, Employee, Patient, AbstractHuman 

    This is not allowed because

    |--------------------------------|
    | Human (sealed interface)       |
    |        ↑                       |
    | AbstractHuman (abstract class) |
    |        ↑                       |
    | Patient (class)                |
    |--------------------------------|


    ❌ Problem

    Patient does NOT directly implement Human

    It implements it indirectly via AbstractHuman.

    But sealed rules are strict:

    🔐 Every class listed in permits must directly extend or implement the sealed type

    🧠 Sealed Rule (VERY IMPORTANT)

    For a sealed interface:

    ✅ Allowed in permits

    final class X implements Human
    sealed class Y implements Human
    non-sealed class Z implements Human


    ❌ NOT allowed

    class Child extends SomeClass implements Human   // indirect

    ❌ Why Patient Is Illegal in permits
    public final class Patient extends AbstractHuman { }


    Patient → extends AbstractHuman

    AbstractHuman → implements Human

    So Java says:

    ❌ “Patient is NOT my direct child. I don’t allow grandchildren in permits.”

*/