public class ConferenceManager implements UserInterface {
    private ConferenceManagementSystem cms;

    // Constructor that takes the ConferenceManagementSystem object
    public ConferenceManager(ConferenceManagementSystem cms) {
        this.cms = cms;
    }

    // Implementing register method from UserInterface
    @Override
    public void register() {
        System.out.println("Conference Manager registered.");
    }

    // Implementing login method from UserInterface
    @Override
    public void login() {
        System.out.println("Conference Manager logged in.");
    }

    // Methods for managing sessions
    public void createSession(String name, String description, String date, String time, String location, Speaker speaker) {
        Session session = new Session(name, description, date, time, location, speaker);
        cms.addSession(session);
    }

    public void openSession(String sessionName) {
        cms.openSession(sessionName);
    }

    public void closeSession(String sessionName) {
        cms.closeSession(sessionName);
    }

    public void notifyAllAttendees(String message) {
        cms.notifyAllAttendees(message);
    }
}
