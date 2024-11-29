import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class RegistrationForm {
    private JFrame frame;

    public RegistrationForm() {
        frame = new JFrame("Attendee Registration");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);

        // Panel for Form
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

        // Form Fields
        JLabel lblName = new JLabel("Name:");
        JTextField txtName = new JTextField();
        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField();

        JButton btnRegister = new JButton("Register");
        JButton btnCancel = new JButton("Cancel");

        // Add components to panel
        panel.add(lblName);
        panel.add(txtName);
        panel.add(lblEmail);
        panel.add(txtEmail);
        panel.add(btnRegister);
        panel.add(btnCancel);

        // Add panel to frame
        frame.add(panel);
        frame.setVisible(true);

        // Button Actions
        btnRegister.addActionListener(e -> {
            String name = txtName.getText();
            String email = txtEmail.getText();
            if (!name.isEmpty() && !email.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Attendee Registered: " + name);
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(frame, "Please fill all fields.");
            }
        });

        btnCancel.addActionListener(e -> frame.dispose());
    }
}
