package com.healthcare.views;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

public class MedicalRecordUI extends JFrame {

    public MedicalRecordUI() {
        setTitle("🩺 Medical Records");
        setSize(550, 480); // Extra height 
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(250, 252, 255));

        // Fonts & Colors
        Font labelFont = new Font("Segoe UI", Font.PLAIN, 14);
        Font titleFont = new Font("Segoe UI", Font.BOLD, 20);
        Font inputFont = new Font("Segoe UI", Font.PLAIN, 13);
        Color primaryColor = new Color(33, 150, 243);
        Color darkText = new Color(33, 33, 33);
        Color buttonColor = new Color(25, 118, 210);

        // Title
        JLabel lblTitle = new JLabel("Medical Records");
        lblTitle.setFont(titleFont);
        lblTitle.setForeground(primaryColor);
        lblTitle.setBounds(180, 10, 250, 30);

        // Patient ID
        JLabel lblId = new JLabel("Patient ID:");
        lblId.setFont(labelFont);
        lblId.setForeground(darkText);
        JTextField txtId = new JTextField();
        txtId.setFont(inputFont);
        txtId.setBorder(BorderFactory.createLineBorder(primaryColor, 1));
        JButton btnSearch = new JButton("Search");
        btnSearch.setFont(labelFont);
        btnSearch.setBackground(buttonColor);
        btnSearch.setForeground(Color.WHITE);
        btnSearch.setFocusPainted(false);
        btnSearch.setBorder(BorderFactory.createEmptyBorder());

        // Diagnosis
        JLabel lblDiagnosis = new JLabel("Diagnosis:");
        lblDiagnosis.setFont(labelFont);
        lblDiagnosis.setForeground(darkText);
        JComboBox<String> comboDiagnosis = new JComboBox<>(new String[]{"Flu", "Cold", "Diabetes", "COVID-19"});
        comboDiagnosis.setFont(inputFont);

        // Prescription
        JLabel lblPrescription = new JLabel("Prescription:");
        lblPrescription.setFont(labelFont);
        lblPrescription.setForeground(darkText);
        JComboBox<String> comboPrescription = new JComboBox<>(new String[]{"Paracetamol", "Ibuprofen", "Insulin", "Vitamin C"});
        comboPrescription.setFont(inputFont);

        // Text Area
        JTextArea txtArea = new JTextArea();
        txtArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        txtArea.setEditable(false);
        txtArea.setBorder(BorderFactory.createLineBorder(primaryColor, 1));
        JScrollPane scrollPane = new JScrollPane(txtArea);

        // Generate Report Button
        JButton btnReport = new JButton("Generate Report");
        btnReport.setFont(labelFont);
        btnReport.setBackground(new Color(76, 175, 80)); // Green
        btnReport.setForeground(Color.WHITE);
        btnReport.setFocusPainted(false);

        // Positioning
        lblId.setBounds(30, 60, 100, 25);
        txtId.setBounds(130, 60, 180, 25);
        btnSearch.setBounds(320, 60, 100, 25);

        lblDiagnosis.setBounds(30, 100, 100, 25);
        comboDiagnosis.setBounds(130, 100, 180, 25);

        lblPrescription.setBounds(30, 140, 100, 25);
        comboPrescription.setBounds(130, 140, 180, 25);

        scrollPane.setBounds(30, 190, 470, 170);
        btnReport.setBounds(180, 370, 180, 30);

        // Add components
        add(lblTitle);
        add(lblId); add(txtId); add(btnSearch);
        add(lblDiagnosis); add(comboDiagnosis);
        add(lblPrescription); add(comboPrescription);
        add(scrollPane);
        add(btnReport);

        // Search Button Action
        btnSearch.addActionListener(e -> {
            String id = txtId.getText().trim();
            String diagnosis = (String) comboDiagnosis.getSelectedItem();
            String prescription = (String) comboPrescription.getSelectedItem();

            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter Patient ID.", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            txtArea.setText("📋 Record for Patient ID: " + id +
                    "\n\n• Diagnosis    : " + diagnosis +
                    "\n• Prescription : " + prescription);
        });

        //  Report Button Action
        btnReport.addActionListener(e -> {
            try {
                String reportPath = "C:\\Users\\thare\\JaspersoftWorkspace\\MyReports\\MedicalReport.jrxml";
                JasperReport report = JasperCompileManager.compileReport(reportPath);

                //  Connect to database
                Connection conn = DBConnection.getConnection(); //  DB connection method

                //  Parameters to pass to the report
                Map<String, Object> params = new HashMap<>();
                params.put("PatientID", txtId.getText().trim()); 

                //  Fill the report with data
                JasperPrint filledReport = JasperFillManager.fillReport(report, params, conn);

                //  Show report
                JasperViewer.viewReport(filledReport, false);

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error generating report: " + ex.getMessage());
            }
        });
    }
}
