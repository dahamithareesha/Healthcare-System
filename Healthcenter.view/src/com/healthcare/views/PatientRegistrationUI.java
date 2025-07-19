package com.healthcare.views;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class PatientRegistrationUI extends JFrame {

    public PatientRegistrationUI() {
        setTitle("📝 Patient Registration");
        setSize(400, 330);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(245, 250, 255));

        JLabel lblId = new JLabel("Patient ID:");
        JLabel lblName = new JLabel("Name:");
        JLabel lblAge = new JLabel("Age:");
        JLabel lblGender = new JLabel("Gender:");
        JLabel lblContact = new JLabel("Contact:");

        JTextField txtId = new JTextField();
        txtId.setEditable(false);
        JTextField txtName = new JTextField();
        JTextField txtAge = new JTextField();
        JComboBox<String> cmbGender = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        JTextField txtContact = new JTextField();

        JButton btnSave = new JButton("Save");

        // Positioning
        lblId.setBounds(30, 20, 100, 25);
        txtId.setBounds(140, 20, 200, 25);

        lblName.setBounds(30, 60, 100, 25);
        txtName.setBounds(140, 60, 200, 25);

        lblAge.setBounds(30, 100, 100, 25);
        txtAge.setBounds(140, 100, 200, 25);

        lblGender.setBounds(30, 140, 100, 25);
        cmbGender.setBounds(140, 140, 200, 25);

        lblContact.setBounds(30, 180, 100, 25);
        txtContact.setBounds(140, 180, 200, 25);

        btnSave.setBounds(140, 230, 120, 35);

        add(lblId);
        add(txtId);
        add(lblName);
        add(txtName);
        add(lblAge);
        add(txtAge);
        add(lblGender);
        add(cmbGender);
        add(lblContact);
        add(txtContact);
        add(btnSave);

        // Load last ID + 1 on form open
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT MAX(id) FROM patient");
            if (rs.next()) {
                int lastId = rs.getInt(1);
                txtId.setText(String.valueOf(lastId + 1));
            } else {
                txtId.setText("1"); // If no record exists
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            txtId.setText("1");
        }

        btnSave.addActionListener(e -> {
            try {
                String name = txtName.getText().trim();
                String ageText = txtAge.getText().trim();
                String contact = txtContact.getText().trim();

                if (name.isEmpty() || ageText.isEmpty() || contact.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Fill all fields!");
                    return;
                }

                if (name.length() > 20) {
                    JOptionPane.showMessageDialog(this, "Name too long (max 20)");
                    return;
                }

                int age = Integer.parseInt(ageText);
                if (age >= 100) {
                    JOptionPane.showMessageDialog(this, "Age must be below 100");
                    return;
                }

                if (!contact.matches("07\\d{8}")) {
                    JOptionPane.showMessageDialog(this, "Contact must start with 07 and be 10 digits");
                    return;
                }

                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO patient (name, age, gender, contact) VALUES (?, ?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS
                );
                ps.setString(1, name);
                ps.setInt(2, age);
                ps.setString(3, cmbGender.getSelectedItem().toString());
                ps.setString(4, contact);
                ps.executeUpdate();

                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int generatedId = rs.getInt(1);
                    JOptionPane.showMessageDialog(this, " Patient added. ID: " + generatedId);
                    txtId.setText(String.valueOf(generatedId + 1)); // show next ID
                }

                txtName.setText("");
                txtAge.setText("");
                txtContact.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Enter valid numbers for age");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, " Error adding patient");
            }
        });
    }
}
