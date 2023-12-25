package org.example.database.m5;

import org.example.database.Database;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabasePopulateServiceV2 {
    public static final Connection CONNECTION = Database.getConnection();
    public static final String ADD_WORKER_SQL
            = "INSERT INTO worker (name, birthday, level, salary) VALUES (?, ?, ?, ?);";

    public static void main(String[] args) {
        addWorker("Bruce Wayne", "1939-03-30", "Senior", 6000 );
    }


    private static void addWorker(String name, String birthday, String level, int salary) {
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(ADD_WORKER_SQL)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, birthday);
            preparedStatement.setString(3, level);
            preparedStatement.setInt(4, salary);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
