package org.example;

import org.example.database.Database;

import java.io.IOException;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) throws IOException {
        Connection connection = Database.getConnection();
    }
}