/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotel.model;

import model.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author sandu
 */
public class MYSQL2 {

    private static Connection connection;

//    public static void createConnection() throws Exception {
//        if (connection == null) {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            connection = DriverManager.getConnection("jdbc:mysql://grandhaven-hotelmanagement.cfsiiq2mmc40.eu-north-1.rds.amazonaws.com:3306/grandhavenhotelmanagementsystemdb", "superadmin", "12345678");
//        }
//        
//    }
    // Get the connection object
//    public static Connection getConnection() throws SQLException, Exception {
//        createConnection();  // Ensure the connection is created
//        return connection;    // Return the connection
//    }
//
//    public static ResultSet executeSearch(String query) throws Exception {
//        createConnection();
//        return connection.createStatement().executeQuery(query);
//    }
//
//    public static Integer executeIUD(String query) throws Exception {
//        createConnection();
//        return connection.createStatement().executeUpdate(query);
//    }
    private static Statement createConnection() throws Exception {
        if (connection == null) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://grandhaven-hotelmanagement.cfsiiq2mmc40.eu-north-1.rds.amazonaws.com:3306/grandhavenhotelmanagementsystemdb", "superadmin", "12345678");
        }
        return connection.createStatement();
    }

    public static void executeIUD(String query) {
        try {
            createConnection().executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static ResultSet executeSearch(String query) throws Exception {
        return createConnection().executeQuery(query);
    }

    public static Connection getConnection() {
        try {
            if (connection == null) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://grandhaven-hotelmanagement.cfsiiq2mmc40.eu-north-1.rds.amazonaws.com:3306/grandhavenhotelmanagementsystemdb", "superadmin", "12345678");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

}
