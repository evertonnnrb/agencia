package com.agencia.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://127.0.0.1/agencia", "root", "root");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                getConnection().close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
