import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SessionRepository implements Repository<Session> {
    private static final String FILE_NAME = "sessions.txt";

    // Ensure the file exists
    private void ensureFileExists() {
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.err.println("Error ensuring file exists: " + e.getMessage());
        }
    }

    // Serialize a session into a CSV line
    private String serializeSession(Session session) {
        String speakerName = session.getSpeaker() != null ? session.getSpeaker().getName() : "None";
        return String.join(",",
                session.getId(),
                session.getName(),
                speakerName,
                session.getDate(),
                session.getTime(),
                session.getLocation()
        );
    }

    // Deserialize a CSV line into a Session object
    private Session deserializeSession(String line) {
        String[] parts = line.split(",");
        if (parts.length == 6) {
            Speaker speaker = parts[2].equals("None") ? null : new Speaker(parts[2], "No bio available");
            return new Session(parts[0], parts[1], parts[3], parts[4], parts[5], speaker);
        }
        System.err.println("Skipping malformed line: " + line);
        return null;
    }

    @Override
    public void save(Session session) {
        ensureFileExists();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(serializeSession(session));
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error saving session: " + e.getMessage());
        }
    }

    @Override
    public List<Session> findAll() {
        List<Session> sessions = new ArrayList<>();
        ensureFileExists();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Session session = deserializeSession(line);
                if (session != null) {
                    sessions.add(session);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading sessions: " + e.getMessage());
        }
        return sessions;
    }

    @Override
    public Session findById(String id) {
        return findAll().stream()
                .filter(session -> session.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void update(Session session) {
        List<Session> sessions = findAll();
        File tempFile = new File("temp_sessions.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            for (Session s : sessions) {
                if (s.getId().equals(session.getId())) {
                    writer.write(serializeSession(session));
                } else {
                    writer.write(serializeSession(s));
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error updating session: " + e.getMessage());
        }

        if (!tempFile.renameTo(new File(FILE_NAME))) {
            System.err.println("Error replacing the original file.");
        }
    }

    @Override
    public void delete(String id) {
        List<Session> sessions = findAll();
        File tempFile = new File("temp_sessions.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            for (Session session : sessions) {
                if (!session.getId().equals(id)) {
                    writer.write(serializeSession(session));
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.err.println("Error deleting session: " + e.getMessage());
        }

        if (!tempFile.renameTo(new File(FILE_NAME))) {
            System.err.println("Error replacing the original file.");
        }
    }
}
