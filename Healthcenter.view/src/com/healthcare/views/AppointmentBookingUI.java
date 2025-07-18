/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.healthcare.views;

import javax.swing.*;
import java.awt.*;

public class AppointmentBookingUI extends JFrame {

    public AppointmentBookingUI() {
        setTitle("📅 Book Appointment");
        setSize(500, 350);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Set background color
        getContentPane().setBackground(new Color(245, 250, 255));

        // Font
        Font labelFont = new Font("Segoe UI", Font.PLAIN, 14);
        Font inputFont = new Font("Segoe UI", Font.PLAIN, 13);
        Font buttonFont = new Font("Segoe UI", Font.BOLD, 14);

        // Colors
        Color primaryColor = new Color(33, 150, 243); // Blue
        Color buttonColor = new Color(25, 118, 210); // Darker blue
        Color textColor = new Color(33, 33, 33);

        // Labels
        JLabel lblTitle = new JLabel("Appointment Booking");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setForeground(primaryColor);
        lblTitle.setBounds(140, 10, 250, 30);

        JLabel lblPatient = new JLabel("Patient Name:");
        JLabel lblDoctor = new JLabel("Doctor:");
        JLabel lblDate = new JLabel("Date:");
        JLabel lblTime = new JLabel("Time:");

        JLabel[] labels = {lblPatient, lblDoctor, lblDate, lblTime};
        for (JLabel lbl : labels) {
            lbl.setFont(labelFont);
            lbl.setForeground(textColor);
        }

        // Inputs
        JTextField txtPatient = new JTextField();
        JComboBox<String> cmbDoctor = new JComboBox<>(new String[]{"Dr. Nimali", "Dr. Ruwan", "Dr. Ruwani"});
        JTextField txtDate = new JTextField("2025-07-15");
        JComboBox<String> cmbTime = new JComboBox<>(new String[]{"08:00 AM", "08:30 AM", "09:00 AM", "09:30 PM"});

        JTextField[] textFields = {txtPatient, txtDate};
        for (JTextField txt : textFields) {
            txt.setFont(inputFont);
            txt.setBorder(BorderFactory.createLineBorder(primaryColor, 1));
        }
        cmbDoctor.setFont(inputFont);
        cmbTime.setFont(inputFont);

        JButton btnBook = new JButton("Book Appointment");
        btnBook.setFont(buttonFont);
        btnBook.setBackground(buttonColor);
        btnBook.setForeground(Color.WHITE);
        btnBook.setFocusPainted(false);
        btnBook.setBorder(BorderFactory.createEmptyBorder());

        // Positioning
        lblPatient.setBounds(50, 60, 120, 25);
        txtPatient.setBounds(180, 60, 240, 25);

        lblDoctor.setBounds(50, 100, 120, 25);
        cmbDoctor.setBounds(180, 100, 240, 25);

        lblDate.setBounds(50, 140, 120, 25);
        txtDate.setBounds(180, 140, 240, 25);

        lblTime.setBounds(50, 180, 120, 25);
        cmbTime.setBounds(180, 180, 240, 25);

        btnBook.setBounds(180, 230, 180, 35);

        // Add components
        add(lblTitle);
        add(lblPatient); add(txtPatient);
        add(lblDoctor); add(cmbDoctor);
        add(lblDate); add(txtDate);
        add(lblTime); add(cmbTime);
        add(btnBook);

        // Action
        btnBook.addActionListener(e -> {
            String patient = txtPatient.getText().trim();
            String doctor = cmbDoctor.getSelectedItem().toString();
            String date = txtDate.getText().trim();
            String time = cmbTime.getSelectedItem().toString();

            if (patient.isEmpty() || date.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(this,
                    "✅ Appointment booked for:\n" +
                    patient + " with " + doctor + "\non " + date + " at " + time,
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        });
    }
}

    
