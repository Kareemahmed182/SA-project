import java.util.List;
import java.util.ArrayList;

public class Speaker {
    private String name;
    private String bio;
    private List<Session> sessions;

    public Speaker(String name, String bio) {
        this.name = name;
        this.bio = bio;
        this.sessions = new ArrayList<>();
    }

    public void addSession(Session session) {
        if (session != null) {
            sessions.add(session);
        } else {
            System.out.println("Cannot add a null session.");
        }
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public String getName() {
        return name;
    }

    public String getBio() {
        return bio;
    }

    @Override
    public String toString() {
        return "Speaker{name='" + name + "', bio='" + bio + "', sessions=" + sessions.size() + "}";
    }
}
