import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
// Make sure you're using the correct import for List
import java.util.List;
import java.util.ArrayList;
import java.io.*;
import com.google.gson.*;


public class ConferenceGUI {
    private JFrame frame;
    private JTabbedPane tabbedPane;
    private ConferenceManagementSystem cms;
    private UserInterface currentUser;  // currentUser is of type UserInterface (could be Attendee or ConferenceManager)
    private List<UserInterface> registeredUsers = new ArrayList<UserInterface>();  // List to hold registered users

    public ConferenceGUI() {
        cms = new ConferenceManagementSystem(); // Initialize Conference Management System

        // Setup the JFrame
        frame = new JFrame("Conference Management System");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add tabs
        tabbedPane = new JTabbedPane();

        // Add login panel
        tabbedPane.addTab("Login", createLoginPanel());

        // Add tabbedPane to JFrame
        frame.add(tabbedPane);
        frame.setVisible(true);

        // Load the registered users from the JSON file
        loadUserCredentialsFromFile("users.json");
    }

    // Create login panel
    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2));

        JLabel lblUsername = new JLabel("Username:");
        JTextField txtUsername = new JTextField();

        JLabel lblPassword = new JLabel("Password:");
        JPasswordField txtPassword = new JPasswordField();

        JButton btnLogin = new JButton("Login");
        JButton btnRegister = new JButton("Register");

        // Login action
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText();
                String password = new String(txtPassword.getPassword());

                // Flag to indicate if login is successful
                boolean loggedIn = false;

                // Check if the entered credentials match any of the registered users
                for (UserInterface user : registeredUsers) {
                    if (user instanceof Attendee) {
                        Attendee attendee = (Attendee) user;
                        if (attendee.getName().equals(username) && attendee.getEmail().equals(password)) {
                            currentUser = attendee;  // Set currentUser to the logged-in attendee
                            loggedIn = true;
                            break;  // Stop checking once logged in
                        }
                    } else if (user instanceof ConferenceManager) {
                        ConferenceManager manager = (ConferenceManager) user;
                        if (username.equals("manager") && password.equals("manager123")) {
                            currentUser = manager;  // Set currentUser to the logged-in manager
                            loggedIn = true;
                            break;  // Stop checking once logged in
                        }
                    }
                }

                // If login is successful, navigate to the appropriate home screen
                if (loggedIn) {
                    JOptionPane.showMessageDialog(frame, "Welcome, " + username + "!");
                    if (currentUser instanceof ConferenceManager) {
                        navigateToManagerHome();
                    } else if (currentUser instanceof Attendee) {
                        navigateToAttendeeHome();
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid credentials! Please try again.");
                }
            }
        });

        // Register action (now actually registers a user)
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText();
                String password = new String(txtPassword.getPassword());

                // Check if the user already exists in the JSON data
                boolean userExists = false;
                for (UserInterface user : registeredUsers) {
                    if (user instanceof Attendee && ((Attendee) user).getName().equals(username)) {
                        userExists = true;
                        break;
                    }
                }

                if (!userExists) {
                    // Register a new attendee (mocked)
                    Attendee newAttendee = new Attendee("1", username, password);
                    registeredUsers.add(newAttendee);  // Add Attendee to the list
                    JOptionPane.showMessageDialog(frame, "Registration successful! Please log in.");
                } else {
                    JOptionPane.showMessageDialog(frame, "User already registered.");
                }
            }
        });

        panel.add(lblUsername);
        panel.add(txtUsername);
        panel.add(lblPassword);
        panel.add(txtPassword);
        panel.add(btnLogin);
        panel.add(btnRegister);

        return panel;
    }

    // Navigate to Manager Home
    private void navigateToManagerHome() {
        tabbedPane.removeAll();
        tabbedPane.addTab("Manager Home", createManagerHomePanel());
    }

    // Navigate to Attendee Home
    private void navigateToAttendeeHome() {
        tabbedPane.removeAll();
        tabbedPane.addTab("Attendee Home", createAttendeeHomePanel());
    }

    // Manager Home Panel
    private JPanel createManagerHomePanel() {
        JPanel panel = new JPanel(new GridLayout(4, 1));

        JButton btnAddSession = new JButton("Add Session");
        JButton btnManageSessions = new JButton("Manage Sessions");
        JButton btnNotifyAttendees = new JButton("Send Notifications");
        JButton btnLogout = new JButton("Logout");

        // Add session action
        btnAddSession.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sessionId = JOptionPane.showInputDialog(frame, "Enter Session ID:");
                String sessionName = JOptionPane.showInputDialog(frame, "Enter Session Name:");
                String date = JOptionPane.showInputDialog(frame, "Enter Session Date:");
                String time = JOptionPane.showInputDialog(frame, "Enter Session Time:");
                String location = JOptionPane.showInputDialog(frame, "Enter Session Location:");
                Speaker speaker = new Speaker(JOptionPane.showInputDialog(frame, "Enter Speaker Name:"),
                        JOptionPane.showInputDialog(frame, "Enter Speaker Bio:"));

                Session session = new Session(sessionId, sessionName, date, time, location, speaker);
                cms.addSession(session);
                JOptionPane.showMessageDialog(frame, "Session added successfully!");
            }
        });

        // Manage sessions action
        btnManageSessions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sessionName = JOptionPane.showInputDialog(frame, "Enter Session Name to Manage:");
                Optional<Session> session = cms.findSessionByName(sessionName);

                if (session.isPresent()) {
                    String action = JOptionPane.showInputDialog(frame, "Enter 'open' to open or 'close' to close the session:");
                    if (action.equalsIgnoreCase("open")) {
                        cms.openSession(sessionName);
                    } else if (action.equalsIgnoreCase("close")) {
                        cms.closeSession(sessionName);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Invalid action!");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Session not found!");
                }
            }
        });

        // Notify attendees action
        btnNotifyAttendees.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = JOptionPane.showInputDialog(frame, "Enter notification message:");
                cms.notifyAllAttendees(message);
                JOptionPane.showMessageDialog(frame, "Notification sent!");
            }
        });

        // Logout action
        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentUser = null;
                tabbedPane.removeAll();
                tabbedPane.addTab("Login", createLoginPanel());
            }
        });

        panel.add(btnAddSession);
        panel.add(btnManageSessions);
        panel.add(btnNotifyAttendees);
        panel.add(btnLogout);

        return panel;
    }

    // Attendee Home Panel
    private JPanel createAttendeeHomePanel() {
        JPanel panel = new JPanel(new GridLayout(3, 1));

        JButton btnViewSessions = new JButton("View Sessions");
        JButton btnRegisterSession = new JButton("Register for a Session");
        JButton btnLogout = new JButton("Logout");

        // View sessions action
        btnViewSessions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder sessionDetails = new StringBuilder("Available Sessions:\n");
                for (Session session : cms.getSessions()) {
                    sessionDetails.append(session.getName())
                            .append(" by ")
                            .append(session.getSpeaker().getName())
                            .append("\n");
                }
                JOptionPane.showMessageDialog(frame, sessionDetails.toString());
            }
        });

        // Register for a session action
        btnRegisterSession.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sessionName = JOptionPane.showInputDialog(frame, "Enter Session Name to Register:");
                Optional<Session> session = cms.findSessionByName(sessionName);

                // Safe casting to Attendee
                if (session.isPresent() && currentUser instanceof Attendee) {
                    Attendee attendee = (Attendee) currentUser; // Safe cast
                    attendee.addToSchedule(session.get());
                    session.get().registerAttendee(attendee);
                    JOptionPane.showMessageDialog(frame, "Registered for session successfully!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Session not found or invalid user!");
                }
            }
        });

        // Logout action
        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentUser = null;
                tabbedPane.removeAll();
                tabbedPane.addTab("Login", createLoginPanel());
            }
        });

        panel.add(btnViewSessions);
        panel.add(btnRegisterSession);
        panel.add(btnLogout);

        return panel;
    }

    // Method to load user credentials from a JSON file
    private void loadUserCredentialsFromFile(String fileName) {
        try {
            File file = new File(fileName);

            // Check if the file exists and is not empty
            if (!file.exists() || file.length() == 0) {
                System.out.println("Error: The file is either missing or empty.");
                return;  // Return early if the file is not found or is empty
            }

            // Read the JSON file
            FileReader reader = new FileReader(fileName);
            JsonElement jsonElement = JsonParser.parseReader(reader);

            // Log the content of the JSON file
            System.out.println("Raw JSON data: " + jsonElement.toString());

            // Check if the JSON is indeed an array
            if (jsonElement.isJsonArray()) {
                JsonArray usersArray = jsonElement.getAsJsonArray();  // Parse the JSON array directly

                // Loop through the array and add users to the registeredUsers list
                for (JsonElement userElement : usersArray) {
                    JsonObject userObject = userElement.getAsJsonObject();  // Each element is a JSON object

                    String username = userObject.get("username").getAsString();
                    String password = userObject.get("password").getAsString();
                    String role = userObject.get("role").getAsString();

                    // Log the loaded user details
                    System.out.println("Loaded user: " + username + ", Role: " + role);

                    // Add the user based on their role
                    if (role.equalsIgnoreCase("manager")) {
                        registeredUsers.add(new ConferenceManager(cms));  // Add Manager
                    } else if (role.equalsIgnoreCase("attendee")) {
                        registeredUsers.add(new Attendee("1", username, password));  // Add Attendee
                    }
                }
            } else {
                // Log an error if the JSON is not an array
                System.out.println("Error: Expected a JSON array but found something else.");
            }

        } catch (IOException e) {
            e.printStackTrace();  // Handle file I/O errors (e.g., file not found)
        } catch (JsonParseException e) {
            System.out.println("Error: Failed to parse JSON.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ConferenceGUI();
    }
}
