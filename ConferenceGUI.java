import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConferenceGUI {
    private JFrame frame;
    private JTabbedPane tabbedPane;
    private LoginManager loginManager;
    private User currentUser;

    public ConferenceGUI() {
        // Initialize LoginManager
        loginManager = new LoginManager();

        // Initialize JFrame and TabbedPane
        frame = new JFrame("GAF-AI 2025 Conference");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tabbedPane = new JTabbedPane();

        // Add login panel as the first tab
        JPanel loginPanel = createLoginPanel();
        tabbedPane.addTab("Login", loginPanel);

        // Add TabbedPane to the frame
        frame.add(tabbedPane);
        frame.setVisible(true);
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2));

        JLabel lblUsername = new JLabel("Username:");
        JTextField txtUsername = new JTextField();
        JLabel lblPassword = new JLabel("Password:");
        JPasswordField txtPassword = new JPasswordField();

        JButton btnLogin = new JButton("Login");
        JButton btnRegister = new JButton("Register");

        // Action listener for login
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText();
                String password = new String(txtPassword.getPassword());

                // Check login credentials
                User user = loginManager.login(username, password);

                if (user != null) {
                    currentUser = user;
                    JOptionPane.showMessageDialog(frame, "Login successful!");

                    // Navigate to appropriate home page based on the role
                    goToHomePage();
                } else {
                    // Show error message for failed login
                    JOptionPane.showMessageDialog(frame, "Invalid credentials, try again.");

                    // Reset the login fields after failed attempt
                    txtUsername.setText("");  // Clear the username field
                    txtPassword.setText("");  // Clear the password field
                    txtUsername.requestFocus();  // Focus back to username field
                }
            }
        });

        // Action listener for register
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText();
                String password = new String(txtPassword.getPassword());
                String role = JOptionPane.showInputDialog(frame, "Enter role (Manager/Attendee):");

                // Register new user
                loginManager.register(username, password, role);
                JOptionPane.showMessageDialog(frame, "Registration successful!");
            }
        });

        // Add components to the panel
        panel.add(lblUsername);
        panel.add(txtUsername);
        panel.add(lblPassword);
        panel.add(txtPassword);
        panel.add(btnLogin);
        panel.add(btnRegister);

        return panel;
    }

    private void goToHomePage() {
        tabbedPane.removeAll(); // Remove the login panel

        // After login, show the appropriate home page based on the user's role
        if (currentUser.getRole().equals("Manager")) {
            tabbedPane.addTab("Manager Home", createManagerHomePanel());
        } else {
            tabbedPane.addTab("Attendee Home", createAttendeeHomePanel());
        }
    }

    private JPanel createManagerHomePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JButton btnManageSessions = new JButton("Manage Sessions");
        JButton btnLogout = new JButton("Logout");

        btnManageSessions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Manager session management functionality
                JOptionPane.showMessageDialog(frame, "Managing sessions...");
            }
        });

        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentUser = null;
                JOptionPane.showMessageDialog(frame, "Logged out successfully!");
                tabbedPane.setSelectedIndex(0); // Go back to login
            }
        });

        panel.add(btnManageSessions, BorderLayout.CENTER);
        panel.add(btnLogout, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createAttendeeHomePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JButton btnSubmitFeedback = new JButton("Submit Feedback");
        JButton btnLogout = new JButton("Logout");

        btnSubmitFeedback.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Attendee feedback submission functionality
                JOptionPane.showMessageDialog(frame, "Submitting feedback...");
            }
        });

        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentUser = null;
                JOptionPane.showMessageDialog(frame, "Logged out successfully!");
                tabbedPane.setSelectedIndex(0); // Go back to login
            }
        });

        panel.add(btnSubmitFeedback, BorderLayout.CENTER);
        panel.add(btnLogout, BorderLayout.SOUTH);

        return panel;
    }

    public static void main(String[] args) {
        new ConferenceGUI();
    }
}
