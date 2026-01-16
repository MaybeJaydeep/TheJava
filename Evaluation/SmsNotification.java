package Evaluation;

public class SmsNotification implements NotificationService {
    public void sendNotification(String message) {
        System.out.println("SMS Notification: " + message);
    }
}
