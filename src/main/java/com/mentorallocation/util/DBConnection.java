package com.mentorallocation.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DBConnection Utility Class
 * Manages database connection to H2 In-Memory Database
 * Provides database connectivity for testing and development
 */
public class DBConnection {
    // Database Configuration
    private static final String DRIVER = "org.h2.Driver";
    private static final String URL = "jdbc:h2:mem:mentor_db;DB_CLOSE_DELAY=-1;MODE=MySQL";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    /**
     * Static block to load the JDBC driver
     */
    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("H2 JDBC Driver not found: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Get database connection
     * @return Connection object
     * @throws SQLException if connection fails
     */
    public static Connection getConnection() throws SQLException {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database connection established successfully!");
            return connection;
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Close database connection
     * @param connection Connection object to close
     */
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed successfully!");
            } catch (SQLException e) {
                System.err.println("Error closing database connection: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
