package org.example.database;

import org.example.database.Database;
import org.example.database.records.Client;
import org.example.database.records.Project;
import org.example.database.records.ProjectWorker;
import org.example.database.records.Worker;

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
    private static final Connection CONNECTION = Database.getConnection();

    private static final String ADD_WORKER_SQL
            = "INSERT INTO worker (name, birthday, level, salary) VALUES (?, ?, ?, ?);";
    private static final String ADD_CLIENT_SQL
            = "INSERT INTO client (name) VALUES (?);";
    private static final String ADD_PROJECT_SQL
            = "INSERT INTO project (client_id, start_date, finish_date) VALUES (?, ?, ?);";
    private static final String ADD_PROJECT_WORKER_SQL
            = "INSERT INTO project_worker (project_id, worker_id) VALUES (?, ?);";

    public static void main(String[] args) {



        //Create a list of workers
        List<Worker> workers = new ArrayList<>();
        workers.add(new Worker("Bruce Wayne", "1939-03-30", "Senior", 6000));
        workers.add(new Worker("Diana Prince", "1942-01-01", "Senior", 6000));
        workers.add(new Worker("Barry Allen", "1956-10-01", "Senior", 5000));
        workers.add(new Worker("Wally West", "1986-03-01", "Middle", 3500));
        workers.add(new Worker("Arthur Curry", "1941-11-01", "Middle", 3000));
        workers.add(new Worker("Oliver Queen", "2012-10-10", "Middle", 2500));
        workers.add(new Worker("John Constantine", "1985-06-06", "Junior", 1500));
        workers.add(new Worker("Billy Batson", "1940-02-02", "Junior", 1000));
        workers.add(new Worker("Kara Zor-El", "1959-05-05", "Trainee", 900));
        workers.add(new Worker("Kyle Rayner", "1994-01-10", "Trainee", 500));

        //Add workers to DB
        saveAllWorkers(workers);

        //Create list of clients
        List<Client> clients = new ArrayList<>();
        clients.add(new Client("Wayne Enterprises"));
        clients.add(new Client("Allen Limited"));
        clients.add(new Client("Prince Co."));
        clients.add(new Client("Star City"));
        clients.add(new Client("Emerald Twilight"));
        clients.add(new Client("Shazam"));

        //Add clients to DB
        saveAllClients(clients);

        //Create list of projects
        List<Project> projects = new ArrayList<>();
        projects.add(new Project(1, "2021-03-03", "2025-01-30"));
        projects.add(new Project(2, "2022-02-02", "2024-05-30"));
        projects.add(new Project(2, "2022-04-01", "2023-12-30"));
        projects.add(new Project(2, "2020-03-03", "2024-08-01"));
        projects.add(new Project(3, "2019-11-10", "2025-12-01"));
        projects.add(new Project(3, "2018-05-05", "2025-11-01"));
        projects.add(new Project(3, "2021-06-14", "2026-07-01"));
        projects.add(new Project(3, "2023-05-03", "2027-05-01"));
        projects.add(new Project(4, "2020-08-28", "2024-08-01"));
        projects.add(new Project(4, "2021-07-12", "2025-07-12"));

        //Add projects to DB
        saveAllProjects(projects);

        //Create list of projectWorkers relations
        List<ProjectWorker> projectWorkers = new ArrayList<>();
        projectWorkers.add(new ProjectWorker(1, 1));
        projectWorkers.add(new ProjectWorker(1, 3));
        projectWorkers.add(new ProjectWorker(1, 9));
        projectWorkers.add(new ProjectWorker(2, 2));
        projectWorkers.add(new ProjectWorker(3, 1));
        projectWorkers.add(new ProjectWorker(3, 2));
        projectWorkers.add(new ProjectWorker(3, 3));
        projectWorkers.add(new ProjectWorker(3, 4));
        projectWorkers.add(new ProjectWorker(4, 1));
        projectWorkers.add(new ProjectWorker(4, 4));
        projectWorkers.add(new ProjectWorker(5,1));
        projectWorkers.add(new ProjectWorker(5,2));
        projectWorkers.add(new ProjectWorker(6,3));
        projectWorkers.add(new ProjectWorker(6,4));
        projectWorkers.add(new ProjectWorker(6,5));
        projectWorkers.add(new ProjectWorker(6,6));
        projectWorkers.add(new ProjectWorker(7,3));
        projectWorkers.add(new ProjectWorker(8,5));
        projectWorkers.add(new ProjectWorker(8,6));
        projectWorkers.add(new ProjectWorker(8,7));
        projectWorkers.add(new ProjectWorker(8,8));
        projectWorkers.add(new ProjectWorker(8,9));
        projectWorkers.add(new ProjectWorker(9,5));
        projectWorkers.add(new ProjectWorker(9,6));
        projectWorkers.add(new ProjectWorker(10,7));
        projectWorkers.add(new ProjectWorker(10,8));
        projectWorkers.add(new ProjectWorker(10,9));

        //Add projectWorkers to BD
        saveAllProjectWorkers(projectWorkers);

    }

    /**
     * Adds all workers from a list to BD in one batch.
     */
    public static void saveAllWorkers(List<Worker> workers) {
        try (PreparedStatement addWorkerStatement = CONNECTION.prepareStatement(ADD_WORKER_SQL)) {
            for (Worker worker : workers) {
                addWorkerStatement.setString(1, worker.name());
                addWorkerStatement.setDate(2, java.sql.Date.valueOf(worker.birthday()));
                addWorkerStatement.setString(3, worker.level());
                addWorkerStatement.setInt(4, worker.salary());

                addWorkerStatement.addBatch();
            }
            addWorkerStatement.executeBatch();
        } catch (SQLException e) {
            System.out.println("Insert ALL user exception. Reason: " + e.getMessage());
        }
    }

    /**
     * Adds all clients from a list to BD in one batch.
     */
    public static void saveAllClients(List<Client> clients) {
        try (PreparedStatement addClientStatement = CONNECTION.prepareStatement(ADD_CLIENT_SQL)) {
            for (Client client : clients) {
                addClientStatement.setString(1, client.name());

                addClientStatement.addBatch();
            }
            addClientStatement.executeBatch();
        } catch (SQLException e) {
            System.out.println("Insert ALL clients exception. Reason: " + e.getMessage());
        }
    }

    /**
     * Adds all projects from a list to BD in one batch.
     */
    public static void saveAllProjects(List<Project> projects) {
        try (PreparedStatement addProjectStatement = CONNECTION.prepareStatement(ADD_PROJECT_SQL)) {
            for (Project project : projects) {
                addProjectStatement.setInt(1, project.client_id());
                addProjectStatement.setDate(2, java.sql.Date.valueOf(project.start_date()));
                addProjectStatement.setDate(3, java.sql.Date.valueOf(project.finish_date()));

                addProjectStatement.addBatch();
            }
            addProjectStatement.executeBatch();
        } catch (SQLException e) {
            System.out.println("Insert ALL projects exception. Reason: " + e.getMessage());
        }
    }

    /**
     * Adds all project_workers from a list to BD in one batch.
     */
    public static void saveAllProjectWorkers(List<ProjectWorker> projectWorkers) {
        try (PreparedStatement addProjectWorkersStatement = CONNECTION.prepareStatement(ADD_PROJECT_WORKER_SQL)) {
            for (ProjectWorker projectWorker : projectWorkers) {
                addProjectWorkersStatement.setInt(1, projectWorker.project_id());
                addProjectWorkersStatement.setInt(2, projectWorker.worker_id());

                addProjectWorkersStatement.addBatch();
            }
            addProjectWorkersStatement.executeBatch();
        } catch (SQLException e) {
            System.out.println("Insert ALL projectWorkers exception. Reason: " + e.getMessage());
        }
    }
}
