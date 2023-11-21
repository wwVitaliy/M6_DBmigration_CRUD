package org.example.database;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseInitService {
    public static final String FILE_INIT_BD = "sql/init_db.sql";

    public static void main(String[] args) {

        // read file
        List<String> linesFromFile = new ArrayList<>();
        try {
            linesFromFile = Files.readAllLines(Path.of(FILE_INIT_BD));
        } catch (IOException e) {
            System.out.println("Cannot read file \"" + FILE_INIT_BD + "\"");
        }

        // create SQL query
        StringBuilder initCommand = new StringBuilder();
        for (String line : linesFromFile) {
            if (!line.strip().startsWith("--")) {
                initCommand.append(line)
                        .append("\n");
            }
        }

        // execute SQL query
        if (initCommand.toString().isBlank()) {
            System.out.println("There are no commands in \"" + FILE_INIT_BD + "\"");
        } else {
            try (Statement statement = Database.getConnection().createStatement()){
                statement.executeUpdate(initCommand.toString());
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
