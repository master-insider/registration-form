import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class RegistrationForm extends JFrame implements ActionListener {
    // Create form components
    JTextField nameField, emailField;
    JButton submitBtn, resetBtn, closeBtn;

    public RegistrationForm() {
        setTitle("Registration Form");
        setLayout(null);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 20, 80, 20);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(100, 20, 150, 20);
        add(nameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(20, 50, 80, 20);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(100, 50, 150, 20);
        add(emailField);

        submitBtn = new JButton("Submit");
        submitBtn.setBounds(20, 80, 80, 20);
        add(submitBtn);
        submitBtn.addActionListener(this);

        resetBtn = new JButton("Reset");
        resetBtn.setBounds(110, 80, 80, 20);
        add(resetBtn);
        resetBtn.addActionListener(this);

        closeBtn = new JButton("Close");
        closeBtn.setBounds(200, 80, 80, 20);
        add(closeBtn);
        closeBtn.addActionListener(this);

        setSize(320, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitBtn) {
            String name = nameField.getText();
            String email = emailField.getText();
            
            // Database connection and insertion logic
            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cat2", "desk2002", "ssd2121");
                PreparedStatement ps = con.prepareStatement("INSERT INTO registration (name, email) VALUES (?, ?)");
                ps.setString(1, name);
                ps.setString(2, email);
                int i = ps.executeUpdate();
                if (i > 0) {
                    JOptionPane.showMessageDialog(this, "Registered successfully!");
                }
                con.close();
            } catch (Exception ex) {
                System.out.println(ex);
            }
        } else if (e.getSource() == resetBtn) {
            nameField.setText("");
            emailField.setText("");
        } else if (e.getSource() == closeBtn) {
            dispose(); // Close the window
        }
    }

    public static void main(String[] args) {
        new RegistrationForm();
    }
}
