package org.example.database;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseQueryServiceTest {

    @Test
    void testNameFromInitData(){
        List<MaxProjectCountClient> maxProjectCountClients = new DatabaseQueryService().findMaxProjectsClients();
        assertEquals("Prince Co.", maxProjectCountClients.get(0).name());
    }
    @Test
    void testProjectCountFromInitData(){
        List<MaxProjectCountClient> maxProjectCountClients = new DatabaseQueryService().findMaxProjectsClients();
        assertEquals(4, maxProjectCountClients.get(0).projectCount());
    }
}