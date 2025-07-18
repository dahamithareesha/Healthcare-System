package com.healthcare.views;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class LoginUI extends JFrame {

    public LoginUI() {
        setTitle("🔐 Login");
        setSize(350, 220);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(245, 250, 255));

        Font labelFont = new Font("Segoe UI", Font.PLAIN, 14);
        Font inputFont = new Font("Segoe UI", Font.PLAIN, 14);
        Font btnFont = new Font("Segoe UI", Font.BOLD, 14);
        Color btnColor = new Color(33, 150, 243);

        JLabel lblUser = new JLabel("Username:");
        lblUser.setFont(labelFont);
        lblUser.setForeground(Color.DARK_GRAY);
        JTextField txtUser = new JTextField();
        txtUser.setFont(inputFont);
        txtUser.setBorder(BorderFactory.createLineBorder(btnColor));

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(labelFont);
        lblPassword.setForeground(Color.DARK_GRAY);
        JPasswordField txtPassword = new JPasswordField();
        txtPassword.setFont(inputFont);
        txtPassword.setBorder(BorderFactory.createLineBorder(btnColor));

        JButton btnLogin = new JButton("Login");
        btnLogin.setFont(btnFont);
        btnLogin.setBackground(btnColor);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setBorder(BorderFactory.createEmptyBorder());

        lblUser.setBounds(30, 30, 90, 25);
        txtUser.setBounds(130, 30, 170, 25);

        lblPassword.setBounds(30, 75, 90, 25);
        txtPassword.setBounds(130, 75, 170, 25);

        btnLogin.setBounds(130, 120, 100, 35);

        add(lblUser);
        add(txtUser);
        add(lblPassword);
        add(txtPassword);
        add(btnLogin);

        btnLogin.addActionListener(e -> {
            String username = txtUser.getText().trim();
            String password = new String(txtPassword.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter both username and password.", "Input Required", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try (Connection conn = DBConnection.getConnection()) {
                if (conn != null) {
                    String query = "SELECT * FROM users WHERE username = ? AND password = ?";
                    try (PreparedStatement stmt = conn.prepareStatement(query)) {
                        stmt.setString(1, username);
                        stmt.setString(2, password);
                        try (ResultSet rs = stmt.executeQuery()) {
                            if (rs.next()) {
                                JOptionPane.showMessageDialog(this, "Login Successful!", "Welcome", JOptionPane.INFORMATION_MESSAGE);
                                new DashboardUI().setVisible(true);
                                dispose();
                            } else {
                                JOptionPane.showMessageDialog(this, "Invalid username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Database connection failed", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "An error occurred", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginUI().setVisible(true));
    }
}
