package org.example.simplesocialnetworkingapp.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnectionUtil {

    private static final String PROPERTIES_FILE = "application.properties";
    private static String URL;
    private static String USER;
    private static String PASS;

    static {
        try {
            // Load MySQL JDBC driver explicitly
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (InputStream input = DBConnectionUtil.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
                Properties prop = new Properties();
                if (input == null) {
                    System.err.println("Unable to find " + PROPERTIES_FILE);
                    throw new RuntimeException("Unable to find " + PROPERTIES_FILE);
                }
                prop.load(input);
                URL = prop.getProperty("db.url");
                USER = prop.getProperty("db.username");
                PASS = prop.getProperty("db.password");
            }
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Failed to initialize DBConnectionUtil", ex);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
