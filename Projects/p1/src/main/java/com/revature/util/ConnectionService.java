package com.revature.util;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionService {
    private static ConnectionService instance;
    private static Connection connection = null;

    private ConnectionService() {
        connection = makeConnection();
    }

    // Return singleton instance of the connection
    public static synchronized ConnectionService getInstance() {
        if (instance == null) {
            instance = new ConnectionService();
        }
        return instance;
    }

    // Make connection to the DataBase
    private Connection makeConnection() {
        Connection c = null;
        Properties p = new Properties();

        try {
            String propertiesFile = "database.properties";
            p.load(new FileReader(propertiesFile));
            Class.forName(p.getProperty("driver"));
            c = DriverManager.getConnection(p.getProperty("url"),
                    p.getProperty("user"), p.getProperty("password"));
        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return c;
    }

    // Return connection to the Database
    public Connection getConnection() {
        return connection;
    }
}