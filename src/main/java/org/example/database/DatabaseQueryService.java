package org.example.database;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {
    public static final String MAX_PROJECTS_CLIENTS_SQL = "sql/find_max_projects_client.sql";
    public static final String LONGEST_PROJECTS_SQL = "sql/find_longest_project.sql";
    public static final String MAX_SALARY_WORKERS_SQL = "sql/find_max_salary_worker.sql";
    public static final String YOUNGEST_OLDEST_WORKERS_SQL = "sql/find_youngest_eldest_workers.sql";
    public static final String PROJECT_PRICES_SQL = "sql/print_project_prices.sql";


    public List<ProjectPrice> findProjectPrices(){
        ArrayList<ProjectPrice> projectPrices = new ArrayList<>();

        try (Statement statement = Database.getConnection().createStatement()) {
            ResultSet resultSet  = statement.executeQuery(
                    createSqlQuery(
                            readFileAsList(PROJECT_PRICES_SQL)));

            while (resultSet.next()) {
                projectPrices.add(
                        new ProjectPrice(
                                resultSet.getInt("project_id"),
                                resultSet.getInt("price")));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return projectPrices;
    }

    public List<YoungestEldestWorker> findYoungestEldestWorker(){
        ArrayList<YoungestEldestWorker> youngestEldestWorkers = new ArrayList<>();

        try (Statement statement = Database.getConnection().createStatement()) {
            ResultSet resultSet  = statement.executeQuery(
                    createSqlQuery(
                            readFileAsList(YOUNGEST_OLDEST_WORKERS_SQL)));

            while (resultSet.next()) {
                youngestEldestWorkers.add(
                        new YoungestEldestWorker(
                                resultSet.getString("type"),
                                resultSet.getString("name"),
                                resultSet.getString("birthday")));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return youngestEldestWorkers;
    }

    public List<MaxSalaryWorker> findMaxSalaryWorkers(){
        ArrayList<MaxSalaryWorker> maxSalaryWorkers = new ArrayList<>();

        try (Statement statement = Database.getConnection().createStatement()) {
            ResultSet resultSet  = statement.executeQuery(
                    createSqlQuery(
                            readFileAsList(MAX_SALARY_WORKERS_SQL)));

            while (resultSet.next()) {
                maxSalaryWorkers.add(
                        new MaxSalaryWorker(
                                resultSet.getString("name"),
                                resultSet.getInt("salary")));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return maxSalaryWorkers;
    }



    public List<LongestProject> findLongestProjects(){
        ArrayList<LongestProject> longestProjects = new ArrayList<LongestProject>();

        try (Statement statement = Database.getConnection().createStatement()) {
            ResultSet resultSet  = statement.executeQuery(
                    createSqlQuery(
                            readFileAsList(LONGEST_PROJECTS_SQL)));

            while (resultSet.next()) {
                longestProjects.add(
                        new LongestProject(
                                resultSet.getInt("id"),
                                resultSet.getInt("month_count")));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return longestProjects;
    }

    public List<MaxProjectCountClient> findMaxProjectsClients() {
        List<MaxProjectCountClient> maxProjectCountClients = new ArrayList<>();

        try (Statement statement = Database.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    createSqlQuery(
                            readFileAsList(MAX_PROJECTS_CLIENTS_SQL)));

            while (resultSet.next()) {
                maxProjectCountClients.add(
                        new MaxProjectCountClient(
                                resultSet.getString("name"),
                                resultSet.getInt("project_count")));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return maxProjectCountClients;
    }

    private String createSqlQuery(List<String> lines) {
        StringBuilder query = new StringBuilder();
        for (String line : lines) {
            if (!line.strip().startsWith("--")) {
                query.append(line).append("\n");
            }
        }
        return query.toString();
    }

    private List<String> readFileAsList(String filePath) {
        List<String> linesFromFile = new ArrayList<>();
        try {
            linesFromFile = Files.readAllLines(Path.of(filePath));
        } catch (IOException e) {
            System.out.println("Cannot read file \"" + filePath + "\"");
        }
        return linesFromFile;
    }
}
