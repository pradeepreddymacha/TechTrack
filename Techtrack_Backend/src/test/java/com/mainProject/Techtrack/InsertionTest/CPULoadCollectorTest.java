package com.mainProject.Techtrack.InsertionTest;

import com.mainProject.DatabaseConnector.DatabaseManager;
import com.mainProject.Techtrack.CPULoadCollector;
import com.mainProject.userVariables.CPUVaraiable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CPULoadCollectorTest {

    @Mock
    private CPULoadCollector cpuLoadController;

    @Mock
    private Connection connection;




    @BeforeEach
    void setUp() throws SQLException {

        connection = DatabaseManager.getConnection();

        cpuLoadController = new CPULoadCollector();
    }

    @Test
    void testInsertMetrics_Success() throws SQLException {

        // Arrange
        CPUVaraiable metrics = new CPUVaraiable(System.currentTimeMillis(), 80);

        // Act
        String result = cpuLoadController.insertMetrics(metrics);

        // Assert
        assertEquals("Metrics inserted successfully!", result);
        connection.close();

    }

    @Test
    void testInsertMetrics_Failure() throws SQLException {
        // Arrange
        // Setting up an invalid metric object with null values
        CPUVaraiable metrics = new CPUVaraiable(0,0);

        // Act
        String result = cpuLoadController.insertMetrics(metrics);

        // Assert
        assertEquals("Failed to insert metrics.", result);

        // Closing the connection
        connection.close();
    }

}
