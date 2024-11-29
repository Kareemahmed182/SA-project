import javax.swing.*;
import java.awt.*;

class SessionManagement {
    private JFrame frame;

    public SessionManagement() {
        frame = new JFrame("Manage Sessions");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);

        // Panel for Form
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));

        // Form Fields
        JLabel lblId = new JLabel("Session ID:");
        JTextField txtId = new JTextField();
        JLabel lblName = new JLabel("Session Name:");
        JTextField txtName = new JTextField();
        JLabel lblSpeaker = new JLabel("Speaker:");
        JTextField txtSpeaker = new JTextField();

        JButton btnAdd = new JButton("Add Session");
        JButton btnCancel = new JButton("Cancel");

        // Add components to panel
        panel.add(lblId);
        panel.add(txtId);
        panel.add(lblName);
        panel.add(txtName);
        panel.add(lblSpeaker);
        panel.add(txtSpeaker);
        panel.add(btnAdd);
        panel.add(btnCancel);

        // Add panel to frame
        frame.add(panel);
        frame.setVisible(true);

        // Button Actions
        btnAdd.addActionListener(e -> {
            String id = txtId.getText();
            String name = txtName.getText();
            String speaker = txtSpeaker.getText();
            if (!id.isEmpty() && !name.isEmpty() && !speaker.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Session Added: " + name);
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(frame, "Please fill all fields.");
            }
        });

        btnCancel.addActionListener(e -> frame.dispose());
    }
}
