package com.fashionstore.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    /**
     * MySQL Workbench connection "faisal": root @ 127.0.0.1:3306
     * Default schema for this project: faisaldb
     */
    private static final String HOST = "127.0.0.1";
    private static final String PORT = "3306";
    private static final String DATABASE = "faisaldb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Faisal@2003";

    private static final String URL =
            "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE
                    + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

    private DBConnection() {
    }

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("[Aura] Connected to faisaldb @ " + HOST + ":" + PORT);
            return connection;
        } catch (ClassNotFoundException e) {
            System.err.println("[Aura] MySQL driver missing. Add mysql-connector-j.jar to WEB-INF/lib");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("[Aura] Database connection failed: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("[Aura] Unexpected DB error: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public static void closeQuietly(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ignored) {
            }
        }
    }
}
