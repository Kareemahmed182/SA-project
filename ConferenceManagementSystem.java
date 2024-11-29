import java.util.ArrayList;
import java.util.List;

public class ConferenceManagementSystem {
    private String conferenceName;
    private String startDate;
    private String endDate;
    private List<Session> sessions;
    private List<Attendee> attendees;

    public ConferenceManagementSystem(String name, String startDate, String endDate) {
        this.conferenceName = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.sessions = new ArrayList<>();
        this.attendees = new ArrayList<>();
    }

    public void registerAttendee(Attendee attendee) {
        attendees.add(attendee);
    }

    public void addSession(Session session) {
        sessions.add(session);
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public List<Attendee> getAttendees() {
        return attendees;
    }
}
