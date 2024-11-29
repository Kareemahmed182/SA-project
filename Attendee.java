import java.util.ArrayList;
import java.util.List;

public class Attendee implements UserInterface {
    private String id;
    private String name;
    private String email;
    private List<Session> schedule;

    public Attendee(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.schedule = new ArrayList<>();
    }

    // Implementing register method from the User interface
    @Override
    public void register() {
        System.out.println("Attendee registered: " + this.name);
    }

    @Override
    public void login() {
        System.out.println("Attendee logged in: " + this.name);
    }

    // Methods to manage the attendee's session schedule
    public void addToSchedule(Session session) {
        schedule.add(session);
    }

    public List<Session> getSchedule() {
        return schedule;
    }

    // Getters
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
}
