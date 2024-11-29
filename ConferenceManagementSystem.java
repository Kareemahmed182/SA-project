import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConferenceManagementSystem {
    private List<Session> sessions;
    private List<Attendee> attendees;
    private NotificationCenter notificationCenter;

    public ConferenceManagementSystem() {
        this.sessions = new ArrayList<>();
        this.attendees = new ArrayList<>();
        this.notificationCenter = new NotificationCenter();
    }

    // Add a new session
    public void addSession(Session session) {
        if (sessions.stream().anyMatch(s -> s.getName().equalsIgnoreCase(session.getName()))) {
            System.out.println("Session with the name '" + session.getName() + "' already exists.");
        } else {
            sessions.add(session);
            System.out.println("Session '" + session.getName() + "' has been added.");
        }
    }

    // Get all sessions
    public List<Session> getSessions() {
        return sessions;
    }

    // Search for a session by name using Optional
    public Optional<Session> findSessionByName(String name) {
        return sessions.stream().filter(s -> s.getName().equalsIgnoreCase(name)).findFirst();
    }

    // Open a session for attendees
    public void openSession(String sessionName) {
        Optional<Session> session = findSessionByName(sessionName);
        if (session.isPresent()) {
            session.get().setOpen(true);
            notificationCenter.sendNotification("Session '" + sessionName + "' is now open for registration.");
            System.out.println("Session '" + sessionName + "' is now open.");
        } else {
            System.out.println("Session '" + sessionName + "' not found.");
        }
    }

    // Close a session for attendees
    public void closeSession(String sessionName) {
        Optional<Session> session = findSessionByName(sessionName);
        if (session.isPresent()) {
            session.get().setOpen(false);
            notificationCenter.sendNotification("Session '" + sessionName + "' is now closed.");
            System.out.println("Session '" + sessionName + "' is now closed.");
        } else {
            System.out.println("Session '" + sessionName + "' not found.");
        }
    }

    // Register a new attendee
    public void registerAttendee(Attendee attendee) {
        if (attendees.stream().anyMatch(a -> a.getEmail().equalsIgnoreCase(attendee.getEmail()))) {
            System.out.println("Attendee with the email '" + attendee.getEmail() + "' is already registered.");
        } else {
            attendees.add(attendee);
            notificationCenter.sendNotification("Welcome " + attendee.getName() + " to the conference!");
            System.out.println("Attendee '" + attendee.getName() + "' has been registered.");
        }
    }

    // Notify all attendees
    public void notifyAllAttendees(String message) {
        attendees.forEach(attendee -> notificationCenter.sendNotification("To " + attendee.getName() + ": " + message));
        System.out.println("Notification sent to all attendees: " + message);
    }
}
