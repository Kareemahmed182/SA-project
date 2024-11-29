import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class ConferenceGUI {
    private JFrame frame;
    private JTabbedPane tabbedPane;
    private List<Session> sessionList;
    private List<Attendee> attendeeList;

    public ConferenceGUI() {
        // Initialize JFrame
        frame = new JFrame("GAF-AI 2025 Conference");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize session and attendee lists
        sessionList = new ArrayList<>();
        attendeeList = new ArrayList<>();

        // Create Tabbed Pane for different panels
        tabbedPane = new JTabbedPane();

        // Add panels for each tab
        tabbedPane.addTab("Home", createHomePanel());
        tabbedPane.addTab("Registration", createRegistrationPanel());
        tabbedPane.addTab("Sessions", createSessionManagementPanel());
        tabbedPane.addTab("Feedback", createFeedbackPanel());

        // Add TabbedPane to the frame
        frame.add(tabbedPane);
        frame.setVisible(true);
    }

    private JPanel createHomePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JButton btnRegister = new JButton("Register for Conference");
        JButton btnSessions = new JButton("Manage Sessions");
        JButton btnExit = new JButton("Exit");

        btnRegister.addActionListener(e -> tabbedPane.setSelectedIndex(1)); // Switch to Registration Tab
        btnSessions.addActionListener(e -> tabbedPane.setSelectedIndex(2)); // Switch to Session Management Tab
        btnExit.addActionListener(e -> System.exit(0));

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(btnRegister);
        buttonPanel.add(btnSessions);
        buttonPanel.add(btnExit);

        panel.add(buttonPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createRegistrationPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2));

        JLabel lblName = new JLabel("Name:");
        JTextField txtName = new JTextField();
        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField();
        JButton btnSubmit = new JButton("Submit Registration");

        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = txtName.getText();
                String email = txtEmail.getText();
                if (!name.isEmpty() && !email.isEmpty()) {
                    // Create a new attendee and add to the list
                    Attendee attendee = new Attendee(String.valueOf(System.currentTimeMillis()), name, email);
                    attendeeList.add(attendee);
                    JOptionPane.showMessageDialog(frame, "Registration Successful!");
                    tabbedPane.setSelectedIndex(0); // Go back to Home Tab
                } else {
                    JOptionPane.showMessageDialog(frame, "Please fill in all fields.");
                }
            }
        });

        panel.add(lblName);
        panel.add(txtName);
        panel.add(lblEmail);
        panel.add(txtEmail);
        panel.add(new JLabel());
        panel.add(btnSubmit);

        return panel;
    }

    private JPanel createSessionManagementPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        String[] columns = {"ID", "Name", "Speaker", "Date", "Time", "Location"};
        String[][] data = {
                {"101", "Introduction to AI", "Dr. Smith", "2024-12-01", "10:00 AM", "Room A"},
                {"102", "Advanced AI", "Dr. Johnson", "2024-12-02", "2:00 PM", "Room B"}
        };

        JTable sessionTable = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(sessionTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Add buttons for managing sessions
        JPanel buttonsPanel = new JPanel();
        JButton btnAddSession = new JButton("Add Session");
        JButton btnViewSession = new JButton("View Session");

        btnAddSession.addActionListener(e -> {
            // Example: You can add more logic here for adding a session
            JOptionPane.showMessageDialog(frame, "Add Session functionality here.");
        });

        btnViewSession.addActionListener(e -> {
            // View session details (placeholder for now)
            int selectedRow = sessionTable.getSelectedRow();
            if (selectedRow != -1) {
                String sessionId = (String) sessionTable.getValueAt(selectedRow, 0);
                JOptionPane.showMessageDialog(frame, "Viewing Session: " + sessionId);
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a session.");
            }
        });

        buttonsPanel.add(btnAddSession);
        buttonsPanel.add(btnViewSession);
        panel.add(buttonsPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createFeedbackPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Simple feedback submission interface
        JTextArea feedbackArea = new JTextArea(5, 20);
        JScrollPane scrollPane = new JScrollPane(feedbackArea);
        JButton submitFeedbackButton = new JButton("Submit Feedback");

        submitFeedbackButton.addActionListener(e -> {
            String feedback = feedbackArea.getText();
            if (!feedback.isEmpty()) {
                // Store feedback (this could be saved to a file or list)
                JOptionPane.showMessageDialog(frame, "Feedback submitted: " + feedback);
                feedbackArea.setText(""); // Clear the feedback area
            } else {
                JOptionPane.showMessageDialog(frame, "Please write some feedback.");
            }
        });

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(submitFeedbackButton, BorderLayout.SOUTH);

        return panel;
    }

    public static void main(String[] args) {
        new ConferenceGUI();
    }
}
