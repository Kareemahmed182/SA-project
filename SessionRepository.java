import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SessionRepository implements Repository<Session> {
    private static final String FILE_NAME = "sessions.txt";

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

    @Override
    public void save(Session session) {
        ensureFileExists();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(session.getId() + "," + session.getName() + "," + session.getSpeaker() + "," +
                    session.getDate() + "," + session.getTime() + "," + session.getLocation());
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
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    sessions.add(new Session(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]));
                } else {
                    System.err.println("Skipping malformed line: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading sessions: " + e.getMessage());
        }
        return sessions;
    }

    @Override
    public Session findById(String id) {
        return findAll().stream().filter(s -> s.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public void update(Session session) {
        List<Session> sessions = findAll();
        File tempFile = new File("temp_sessions.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            for (Session s : sessions) {
                if (s.getId().equals(session.getId())) {
                    writer.write(session.getId() + "," + session.getName() + "," + session.getSpeaker() + "," +
                            session.getDate() + "," + session.getTime() + "," + session.getLocation());
                } else {
                    writer.write(s.getId() + "," + s.getName() + "," + s.getSpeaker() + "," +
                            s.getDate() + "," + s.getTime() + "," + s.getLocation());
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
            for (Session s : sessions) {
                if (!s.getId().equals(id)) {
                    writer.write(s.getId() + "," + s.getName() + "," + s.getSpeaker() + "," +
                            s.getDate() + "," + s.getTime() + "," + s.getLocation());
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
