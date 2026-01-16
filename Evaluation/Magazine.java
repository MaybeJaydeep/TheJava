package Evaluation;

class Magazine extends LibraryItem {

    public Magazine(String itemId, String title) {
        this.itemId = itemId;
        this.title = title;
    }

    @Override
    public void displayDetails() {
        System.out.println("Magazine ID: " + itemId);
        System.out.println("Title: " + title);
    }

    @Override
    public int getLoanPeriod() {
        return 7; 
    }
}