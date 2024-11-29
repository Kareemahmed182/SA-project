import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AttendeeRepository implements Repository<Attendee> {
    private static final String FILE_NAME = "attendees.txt";

    @Override
    public void save(Attendee attendee) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(attendee.getId() + "," + attendee.getName() + "," + attendee.getEmail());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error saving attendee: " + e.getMessage());
        }
    }

    @Override
    public List<Attendee> findAll() {
        List<Attendee> attendees = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                attendees.add(new Attendee(parts[0], parts[1], parts[2]));
            }
        } catch (IOException e) {
            System.err.println("Error reading attendees: " + e.getMessage());
        }
        return attendees;
    }

    @Override
    public Attendee findById(String id) {
        return findAll().stream().filter(a -> a.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public void update(Attendee attendee) {
        List<Attendee> attendees = findAll();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Attendee a : attendees) {
                if (a.getId().equals(attendee.getId())) {
                    writer.write(attendee.getId() + "," + attendee.getName() + "," + attendee.getEmail());
                } else {
                    writer.write(a.getId() + "," + a.getName() + "," + a.getEmail());
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error updating attendee: " + e.getMessage());
        }
    }

    @Override
    public void delete(String id) {
        List<Attendee> attendees = findAll();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Attendee a : attendees) {
                if (!a.getId().equals(id)) {
                    writer.write(a.getId() + "," + a.getName() + "," + a.getEmail());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.err.println("Error deleting attendee: " + e.getMessage());
        }
    }
}
