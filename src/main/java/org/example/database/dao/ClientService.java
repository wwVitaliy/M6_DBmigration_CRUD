package org.example.database.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Client DAO (data access object)
 */
public class ClientService {
    private static final String CREATE_CLIENT_SQL
            = "INSERT INTO client (name) VALUES (?);";
    private static final String GET_CLIENT_NAME_SQL
            = "SELECT * FROM client WHERE id = ?;";
    private static final String SET_CLIENT_NAME_SQL
            = "UPDATE client SET name = ? WHERE id = ?;";
    private static final String DELETE_CLIENT_BY_ID_SQL
            = "DELETE FROM client WHERE id = ?;";
    private static final String GET_ALL_CLIENTS_SQL
            = "SELECT * FROM client;";
    private final Connection CONNECTION;

    private PreparedStatement createStatement;
    private PreparedStatement getClientNameStatement;
    private PreparedStatement setClientNameStatement;
    private PreparedStatement deleteClientByIdStatement;
    private Statement getAllClientsStatement;

    /**
     * ClientService constructor
     *
     * @param connection connection to relation DB
     */
    public ClientService(Connection connection) {
        this.CONNECTION = connection;

        try {
            this.createStatement = CONNECTION.prepareStatement(CREATE_CLIENT_SQL, Statement.RETURN_GENERATED_KEYS);
            this.getClientNameStatement = CONNECTION.prepareStatement(GET_CLIENT_NAME_SQL);
            this.setClientNameStatement = CONNECTION.prepareStatement(SET_CLIENT_NAME_SQL);
            this.deleteClientByIdStatement = CONNECTION.prepareStatement(DELETE_CLIENT_BY_ID_SQL);
            this.getAllClientsStatement = CONNECTION.createStatement();
        } catch (SQLException e) {
            System.out.println("Cannot create statement. Reason: " + e.getMessage());
        }
    }

    /**
     * Adds new clint to DB
     *
     * @param name client's name
     * @return clients id in DB
     */
    long create(String name) throws Exception {
        //name validation
        if (!isValidName(name)) throw new Exception("Wrong name");

        //DB shenanigans
        try {
            createStatement.setString(1, name);
            createStatement.executeUpdate();
            ResultSet generatedKeys = createStatement.getGeneratedKeys();
            if (generatedKeys.next()) return generatedKeys.getLong("id");

        } catch (SQLException e) {

            System.out.println("Cannot create client. Reason: " + e.getMessage());
        }

        return -1;
    }

    /**
     * Gets clients name form DB
     *
     * @param id client id to get name for
     * @return clients name form DB
     */
    String getById(long id) throws Exception {
        //id validation
        if (!isValidId(id)) throw new Exception("Wrong id");

        //DB shenanigans
        try {
            getClientNameStatement.setLong(1, id);
            ResultSet resultSet = getClientNameStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("name");
            } else {
                throw new Exception("No client with id = " + id);
            }
        } catch (SQLException e) {
            System.out.println("Cannot get name from DB. Reason: " + e.getMessage());
        }
        return "";
    }

    /**
     * Sets new client's name in DB
     *
     * @param id   clients id to set new name for
     * @param name clients new name
     */
    void setName(long id, String name) throws Exception {
        //Name and id validation
        if (!isValidName(name) || !isValidId(id)) throw new Exception("Wrong name or id");

        //DB shenanigans
        try {
            setClientNameStatement.setString(1, name);
            setClientNameStatement.setLong(2, id);
            if (setClientNameStatement.executeUpdate() == 0) throw new Exception("No client with id = " + id);
        } catch (SQLException e) {
            System.out.println("Cannot set new name. Reason: " + e.getMessage());
        }
    }

    /**
     * Deletes client from DB
     *
     * @param id id of te client to delete
     */
    void deleteById(long id) throws Exception {
        //id validation
        if (!isValidId(id)) throw new Exception("Wrong id");

        //DB shenanigans
        try {
            deleteClientByIdStatement.setLong(1, id);
            if (deleteClientByIdStatement.executeUpdate() == 0) throw new Exception("No client with id = " + id);
        } catch (SQLException e) {
            System.out.println("Cannot delete client. Reason: " + e.getMessage());
        }
    }

    /**
     * Gets all clients from DB as a list of Client
     *
     * @return list of all client from DB
     */
    List<Client> listAll() {
        ArrayList<Client> clients = new ArrayList<>();

        //DB shenanigans
        try {
            ResultSet resultSet = getAllClientsStatement.executeQuery(GET_ALL_CLIENTS_SQL);
            while (resultSet.next()) {
                clients.add(new Client(
                        resultSet.getInt("id"),
                        resultSet.getString("name")));
            }
        } catch (SQLException e) {
            System.out.println("Cannot get all clients. Reason: " + e.getMessage());
        }
        return clients;
    }

    /**
     * Validates name value.
     *
     * @param name name to validate
     * @return true if name is valid; false if name is not valid
     */
    private boolean isValidName(String name) {
        return name.length() >= 2 && name.length() <= 1000;
    }

    /**
     * Validates id value
     *
     * @param id id to validate
     * @return true if id is valid; false if id is not valid
     */
    private boolean isValidId(long id) {
        return id > 0;
    }

}
