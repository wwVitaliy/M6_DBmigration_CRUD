package org.example.database;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseQueryServiceTest {

    @Test
    void testMaxProjectCountClientNameFromInitData(){
        List<MaxProjectCountClient> maxProjectCountClients
                = new DatabaseQueryService().findMaxProjectsClients();
        assertEquals("Prince Co.", maxProjectCountClients.get(0).name());
    }
    @Test
    void testMaxProjectCountFromInitData(){
        List<MaxProjectCountClient> maxProjectCountClients
                = new DatabaseQueryService().findMaxProjectsClients();
        assertEquals(4, maxProjectCountClients.get(0).projectCount());
    }

    @Test
    void findLongestProjectIdFromInitData() {
        List<LongestProject> longestProjects
                = new DatabaseQueryService().findLongestProjects();
        assertEquals(6, longestProjects.get(0).id());
    }

    @Test
    void findLongestProjectMonthCountFromInitData() {
        List<LongestProject> longestProjects
                = new DatabaseQueryService().findLongestProjects();
        assertEquals(89, longestProjects.get(0).month_count());
    }
}