package com.mvictorl.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnectionUtils {
    public static Connection getMySQLConnection(String hostName, String dbName, String userName, String password)
            throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName + "?useSSL=false";
        Connection conn = DriverManager.getConnection(connectionURL, userName, password);
        return conn;
    }
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        return getMySQLConnection("localhost", "gtb", "gtb_root", "nimda");
    }

    public static void closeQuietly(Connection conn) {
        try {
            conn.close();
        } catch (Exception e) { }
    }

    public static void rollbackQuietly(Connection conn) {
        try {
            conn.rollback();
        } catch (Exception e) { }
    }
}