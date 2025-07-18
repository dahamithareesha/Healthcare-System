/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.healthcare.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DashboardUI extends JFrame {

    public DashboardUI() {
        setTitle("🏥 Healthcare Dashboard");
        setSize(400, 320);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(245, 250, 255));

        Font titleFont = new Font("Segoe UI", Font.BOLD, 22);
        Font btnFont = new Font("Segoe UI", Font.BOLD, 16);
        Color primaryColor = new Color(33, 150, 243);
        Color btnColor = new Color(25, 118, 210);
        Color btnHoverColor = new Color(13, 71, 161);

        JLabel lblTitle = new JLabel("Dashboard");
        lblTitle.setFont(titleFont);
        lblTitle.setForeground(primaryColor);
        lblTitle.setBounds(140, 20, 150, 30);
        add(lblTitle);

        JButton btnPatient = new JButton("Add Patient");
        JButton btnRecord = new JButton("Medical Record");
        JButton btnAppointment = new JButton("Appointment");

        JButton[] buttons = {btnPatient, btnRecord, btnAppointment};
        int yPos = 70;

        for (JButton btn : buttons) {
            btn.setBounds(100, yPos, 200, 40);
            btn.setFont(btnFont);
            btn.setBackground(btnColor);
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createEmptyBorder());
            addHoverEffect(btn, btnColor, btnHoverColor);
            add(btn);
            yPos += 60;
        }

        btnPatient.addActionListener(e -> new PatientRegistrationUI().setVisible(true));
        btnRecord.addActionListener(e -> new MedicalRecordUI().setVisible(true));
        btnAppointment.addActionListener(e -> new AppointmentBookingUI().setVisible(true));
    }

    private void addHoverEffect(JButton button, Color normalColor, Color hoverColor) {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hoverColor);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(normalColor);
            }
        });
    }
}
