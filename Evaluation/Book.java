package Evaluation;

class Book extends LibraryItem {

    public Book(String itemId, String title) {
        this.itemId = itemId;
        this.title = title;
    }

    @Override
    public void displayDetails() {
        System.out.println("Book ID: " + itemId);
        System.out.println("Title: " + title);
    }

    @Override
    public int getLoanPeriod() {
        return 21; 
    }
}