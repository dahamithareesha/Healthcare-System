/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.healthcare.views;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author thare
 */
public class DBConnection {

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Correct format
//            String url = "jdbc:sqlserver://localhost:1433;databaseName=Healthcare.db;encrypt=true;trustServerCertificate=true";
//            String user = "DESKTOP-272IJAU"; // or your SQL Server username
//            String password = ""; 
            String url = "jdbc:mysql://localhost:3307/healthcareDB";
            String user = "root"; // Or your MySQL username
            String password = "";

            Connection conn = DriverManager.getConnection(url, user, password);
            return conn;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
