import java.util.ArrayList;
import java.util.List;

public class NotificationCenter {
    private List<String> notifications;

    public NotificationCenter() {
        this.notifications = new ArrayList<>();
    }

    // Send a notification
    public void sendNotification(String message) {
        notifications.add(message);
        System.out.println("Notification sent: " + message);
    }

    // Get all notifications
    public List<String> getNotifications() {
        return new ArrayList<>(notifications); // Return a copy to avoid external modification
    }

    // Filter notifications containing a specific keyword
    public List<String> filterNotifications(String keyword) {
        List<String> filtered = new ArrayList<>();
        for (String notification : notifications) {
            if (notification.toLowerCase().contains(keyword.toLowerCase())) {
                filtered.add(notification);
            }
        }
        return filtered;
    }

    // Clear all notifications
    public void clearNotifications() {
        notifications.clear();
        System.out.println("All notifications cleared.");
    }

    // Clear specific notifications by keyword
    public void clearNotificationsByKeyword(String keyword) {
        notifications.removeIf(notification -> notification.toLowerCase().contains(keyword.toLowerCase()));
        System.out.println("Notifications containing '" + keyword + "' have been cleared.");
    }
}
