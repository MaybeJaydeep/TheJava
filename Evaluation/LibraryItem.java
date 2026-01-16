package Evaluation;

public abstract class LibraryItem {
    protected String itemId;
    protected String title;


    public abstract void displayDetails();
    public abstract int getLoanPeriod();
}

