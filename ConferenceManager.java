public class ConferenceManager extends User {
    public ConferenceManager(String id, String name, String email) {
        super(id, name, email);
    }

    public void createSession(Session session, ConferenceManagementSystem cms) {
        cms.addSession(session);
    }

    public void generateCertificate(Attendee attendee) {
        Certificate cert = new Certificate(attendee.getName(), attendee.getId());
        cert.generate();
    }
}
