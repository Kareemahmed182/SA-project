import java.util.Objects;

public class Session {
    private String id;        // Unique identifier for the session
    private String name;      // Name of the session
    private String speaker;   // Name of the speaker
    private String date;      // Date of the session (e.g., "2024-12-01")
    private String time;      // Time of the session (e.g., "10:00 AM")
    private String location;  // Location of the session (e.g., "Room A")

    // Constructor
    public Session(String id, String name, String speaker, String date, String time, String location) {
        this.id = id;
        this.name = name;
        this.speaker = speaker;
        this.date = date;
        this.time = time;
        this.location = location;
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

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    // toString Method (for debugging and display)
    @Override
    public String toString() {
        return "Session{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", speaker='" + speaker + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    // Equals and HashCode (for comparison in lists or sets)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(id, session.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
