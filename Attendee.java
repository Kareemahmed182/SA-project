import java.util.List;
import java.util.ArrayList;

public class Attendee extends User {
    private List<Session> schedule;

    public Attendee(String id, String name, String email) {
        super(id, name, email); // Ensure User class has this constructor
        this.schedule = new ArrayList<>();
    }

    public void addToSchedule(Session session) {
        if (session != null) {
            schedule.add(session);
        } else {
            System.out.println("Cannot add a null session to the schedule.");
        }
    }

    public List<Session> getSchedule() {
        return schedule;
    }
}
