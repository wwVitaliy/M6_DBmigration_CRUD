package org.example.database;

import org.example.prop.PropertyReader;
import org.flywaydb.core.Flyway;

import java.sql.*;

/**
 * Postgresql DB configuration
 */
public class Database {
    private static final Database INSTANCE = new Database();
    private static final String SQL_EX_MESSAGE = "Cannot create connection. Reason: ";
    private static Connection connection;

    private Database() {
        String url = PropertyReader.getConnectionURLForPostgres();
        String user = PropertyReader.getUserForPostgres();
        String password = PropertyReader.getPasswordForPostgres();

        try {
            connection = DriverManager.getConnection(url, user, password);
            flywayMigration(url, user, password);
        } catch (SQLException e) {
            System.out.println(SQL_EX_MESSAGE + e.getMessage());
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static Database getInstance() {
        return INSTANCE;
    }

    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Flyway configuration
     */
    private void flywayMigration(String url, String user, String password) {
        Flyway flyway = Flyway.configure().dataSource(url, user, password).load();
        flyway.migrate();
    }
}
