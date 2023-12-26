package org.example.database.dao;

import java.util.ArrayList;
import java.util.List;

/**
 * Client DAO (data access object)
 */
public class ClientService {

    /**
     * Adds new clint to DB
     *
     * @param name - client's name
     * @return clients id in DB
     */
    long create(String name) {
        return 0L;
    }

    /**
     * Gets clients name form DB
     *
     * @param id - client id to get name for
     * @return clients name form DB
     */
    String getById(long id) {
        return "";
    }

    /**
     * Sets new clients name in DB
     *
     * @param id   - clients id to set new name for
     * @param name clients new name
     */
    void setName(long id, String name) {

    }

    /**
     * Deletes client from DB
     *
     * @param id - id of te client to delete
     */
    void deleteById(long id) {

    }

    /**
     * Gets all clients from DB as a list of Client
     *
     * @return
     */
    List<Client> listAll() {
        return new ArrayList<>();
    }

}
