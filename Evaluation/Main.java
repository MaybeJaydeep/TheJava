package Evaluation;

class Main {
    public static void main(String[] args) {
        LibraryItem book = new Book("B001", "The Great Empire");
        LibraryItem magazine = new Magazine("M001", "National Geographic");

        book.displayDetails();
        System.out.println("Loan Period: " + book.getLoanPeriod() + " days\n");

        magazine.displayDetails();
        System.out.println("Loan Period: " + magazine.getLoanPeriod() + " days");
    }
}
