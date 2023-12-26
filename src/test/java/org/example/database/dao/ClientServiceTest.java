package org.example.database.dao;

import org.example.database.Database;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClientServiceTest {
    private static ClientService clientService;

    @BeforeAll
    static void beforeAll() {
        clientService = new ClientService(Database.getConnection());
    }

    @Test
    void testCreateNewClient() throws Exception {
        String clientName = "name";
        long id = clientService.create(clientName);
        assertTrue(id > 0);
        clientService.deleteById(id);
    }

    @Test
    void testGetById() throws Exception {
        long id = 1L;
        String clientName1 = "Wayne Enterprises";
        assertEquals(clientService.getById(id), clientName1);
    }

    @Test
    void testGetByIdWithWrongId() {
        long id = 0L;
        assertThrows(Exception.class, () -> clientService.getById(id));
    }

    @Test
    void testSetName() throws Exception {
        long id = 1L;
        String oldName = clientService.getById(id);
        String newName = "new name";
        clientService.setName(id, newName);
        assertEquals(clientService.getById(id), newName);
        clientService.setName(id, oldName);
    }

    @Test
    void testDeleteById() throws Exception {
        long id = clientService.create("Test client");
        clientService.deleteById(id);
        assertThrows(Exception.class, () -> clientService.getById(id));
    }

    @Test
    void testListAllClients() {
        List<Client> clients = clientService.listAll();
        assertFalse(clients.isEmpty());
    }
}