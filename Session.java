import java.util.ArrayList;
import java.util.List;

public class Session {
    private String id;
    private String name;
    private String speaker;
    private String date;
    private String time;
    private String location;
    private List<Attendee> attendees;

    public Session(String id, String name, String speaker, String date, String time, String location) {
        this.id = id;
        this.name = name;
        this.speaker = speaker;
        this.date = date;
        this.time = time;
        this.location = location;
        this.attendees = new ArrayList<>();
    }

    // Register an attendee for the session
    public void registerAttendee(Attendee attendee) {
        attendees.add(attendee);
    }

    public List<Attendee> getAttendees() {
        return attendees;
    }

    // Getters
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getSpeaker() {
        return speaker;
    }
    public String getDate() {
        return date;
    }
    public String getTime() {
        return time;
    }
    public String getLocation() {
        return location;
    }
}
