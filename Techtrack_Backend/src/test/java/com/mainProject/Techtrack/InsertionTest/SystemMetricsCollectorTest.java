package com.mainProject.Techtrack.InsertionTest;

import com.mainProject.DatabaseConnector.DatabaseManager;
import com.mainProject.Techtrack.SystemMetricsCollector;
import com.mainProject.userVariables.SystemMetricsVariables;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SystemMetricsCollectorTest {

    private SystemMetricsCollector systemMetricsCollector;
    private Connection connection;

    @BeforeEach
    void setUp() throws SQLException {
        // Creating a new instance of SystemMetricsCollector
        systemMetricsCollector = new SystemMetricsCollector();

        // Getting a connection from DatabaseManager
        connection = DatabaseManager.getConnection();
    }

    @Test
    void testInsertMetrics_Success() throws SQLException {

        SystemMetricsVariables sys = new SystemMetricsVariables(System.currentTimeMillis(), "Windows_Mock", "10.0.19042", "MyComputer", "00-1A-2B-3C-4D-5E", "Dell", "Latitude E7450", "Laptop", 16_000_000_000L);

        String result = systemMetricsCollector.insertMetrics(sys);
        // Act & Assert
        assertEquals("Metrics inserted successfully!", result);

        // Closing the connection
        connection.close();
    }

    @Test
    void testInsertMetrics_Failure() throws SQLException {

        SystemMetricsVariables sys = new SystemMetricsVariables(0, null, null, null, null, null, null, null, 0);

        String result = systemMetricsCollector.insertMetrics(sys);
        // Act & Assert
        assertEquals("Failed to insert metrics.", systemMetricsCollector.insertMetrics(sys));

        // Closing the connection
        connection.close();
    }
}
