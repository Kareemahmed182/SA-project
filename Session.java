import java.util.ArrayList;
import java.util.List;

public class Session {
    private String id;
    private String name;
    private String date;
    private String time;
    private String location;
    private Speaker speaker;
    private boolean isOpen; // Indicates whether the session is open for registration
    private List<Attendee> attendees;

    // Constructor
    public Session(String id, String name, String date, String time, String location, Speaker speaker) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
        this.location = location;
        this.speaker = speaker;
        this.isOpen = false; // Default to closed
        this.attendees = new ArrayList<>();
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public Speaker getSpeaker() {
        return speaker;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public List<Attendee> getAttendees() {
        return new ArrayList<>(attendees); // Return a copy to prevent external modification
    }

    // Setters
    public void setOpen(boolean open) {
        this.isOpen = open;
    }

    public void setSpeaker(Speaker speaker) {
        this.speaker = speaker;
    }

    // Method to register an attendee
    public void registerAttendee(Attendee attendee) {
        if (isOpen) {
            attendees.add(attendee);
            System.out.println("Attendee '" + attendee.getName() + "' registered for session '" + name + "'.");
        } else {
            System.out.println("Cannot register attendee. Session '" + name + "' is closed.");
        }
    }
}
