import java.util.List;

public class ConferenceManager implements UserInterface {
    private String id;
    private String name;
    private String email;

    public ConferenceManager(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    @Override
    public void register() {
        System.out.println("Conference Manager registered: " + this.name);
    }

    @Override
    public void login() {
        System.out.println("Conference Manager logged in: " + this.name);
    }

    // Method to create a new session
    public void createSession(Session session, List<Session> sessionList) {
        sessionList.add(session);
        System.out.println("Session created: " + session.getName());
    }

    // Method to manage session details
    public void manageSession(Session session) {
        System.out.println("Managing session: " + session.getName());
    }
}
