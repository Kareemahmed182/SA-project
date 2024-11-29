import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConferenceGUI {
    private JFrame frame;

    public ConferenceGUI() {
        frame = new JFrame("Conference Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Main Panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));

        // Buttons for Operations
        JButton btnRegister = new JButton("Register Attendee");
        JButton btnManageSessions = new JButton("Manage Sessions");
        JButton btnViewSpeakers = new JButton("View Speakers");
        JButton btnSubmitFeedback = new JButton("Submit Feedback");
        JButton btnExit = new JButton("Exit");

        // Add buttons to panel
        panel.add(btnRegister);
        panel.add(btnManageSessions);
        panel.add(btnViewSpeakers);
        panel.add(btnSubmitFeedback);
        panel.add(btnExit);

        // Add actions
        btnRegister.addActionListener(e -> openRegistrationForm());
        btnManageSessions.addActionListener(e -> openSessionManagement());
        btnViewSpeakers.addActionListener(e -> viewSpeakers());
        btnSubmitFeedback.addActionListener(e -> submitFeedback());
        btnExit.addActionListener(e -> System.exit(0));

        // Add panel to frame
        frame.add(panel);
        frame.setVisible(true);
    }

    private void openRegistrationForm() {
        new RegistrationForm();
    }

    private void openSessionManagement() {
        new SessionManagement();
    }

    private void viewSpeakers() {
        JOptionPane.showMessageDialog(frame, "Speaker information goes here.");
    }

    private void submitFeedback() {
        JOptionPane.showMessageDialog(frame, "Feedback form goes here.");
    }

    public static void main(String[] args) {
        new ConferenceGUI();
    }

}
