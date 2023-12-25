package org.example.database;

import org.example.database.records.*;
import org.junit.jupiter.api.Test;

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
    void testLongestProjectIdFromInitData() {
        List<LongestProject> longestProjects
                = new DatabaseQueryService().findLongestProjects();
        assertEquals(6, longestProjects.get(0).id());
    }

    @Test
    void testLongestProjectMonthCountFromInitData() {
        List<LongestProject> longestProjects
                = new DatabaseQueryService().findLongestProjects();
        assertEquals(89, longestProjects.get(0).month_count());
    }

    @Test
    void testMaxSalaryWorkersCountFromInitData() {
        List<MaxSalaryWorker> maxSalaryWorkers
                = new DatabaseQueryService().findMaxSalaryWorkers();
        assertEquals(2, maxSalaryWorkers.size());
    }

    @Test
    void testMaxSalaryWorkersSalaryFromInitData() {
        List<MaxSalaryWorker> maxSalaryWorkers
                = new DatabaseQueryService().findMaxSalaryWorkers();
        assertEquals(6000, maxSalaryWorkers.get(0).salary());
    }

    @Test
    void testYoungestEldestWorkersCountFromInitData() {
        List<YoungestEldestWorker> youngestEldestWorkers
                = new DatabaseQueryService().findYoungestEldestWorker();
        assertEquals(2, youngestEldestWorkers.size());
    }

    @Test
    void testProjectPricesCountFromInitData() {
        List<ProjectPrice> projectPrices
                = new DatabaseQueryService().findProjectPrices();
        assertEquals(10, projectPrices.size());

    }
}