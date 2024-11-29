import java.util.ArrayList;
import java.util.List;

public class NotificationCenter {
    private List<String> notifications;

    public NotificationCenter() {
        this.notifications = new ArrayList<>();
    }

    public void sendNotification(String message) {
        notifications.add(message);
    }

    public List<String> getNotifications() {
        return notifications;
    }
}
