package Evaluation;

public class NotificationFactory {
    public static NotificationService getBin(String type) {
        type = type.toLowerCase();
        return switch (type) {
            case "email" -> new EmailNotification();
            case "sms"-> new SmsNotification();
            default -> null;
        };
    }
}
