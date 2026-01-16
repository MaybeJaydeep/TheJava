package Evaluation;

public class Notification {
    public static void main(String[] args) {
    NotificationService ES1 = new EmailNotification();
    NotificationService Sm1 = new SmsNotification();
    ES1.sendNotification("Your book is due soon.");
    Sm1.sendNotification("Your book is overdue.");

    NotificationService ES2 = NotificationFactory.getBin("email");
    NotificationService Sm2 = NotificationFactory.getBin("sms");
    ES2.sendNotification("New arrivals in the library.");
    Sm2.sendNotification("Library will be closed tomorrow.");
    }

}
