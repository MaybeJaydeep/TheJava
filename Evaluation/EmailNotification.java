package Evaluation;

public class EmailNotification implements NotificationService {
    public void sendNotification(String message) {
        System.out.println("Email Notification: " + message);
    }
}
