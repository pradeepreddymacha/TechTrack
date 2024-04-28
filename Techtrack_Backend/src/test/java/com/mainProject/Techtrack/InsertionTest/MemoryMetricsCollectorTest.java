package com.mainProject.Techtrack.InsertionTest;

import com.mainProject.DatabaseConnector.DatabaseManager;
import com.mainProject.Techtrack.MemoryMetricsCollector;
import com.mainProject.userVariables.MemoryMetricsVariables;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MemoryMetricsCollectorTest {

    private MemoryMetricsCollector memoryMetricsCollector;
    private Connection connection;

    @BeforeEach
    void setUp() throws SQLException {
        // Creating a new instance of MemoryMetricsCollector
        memoryMetricsCollector = new MemoryMetricsCollector();
        // Creating a new instance of DatabaseManager
        connection = DatabaseManager.getConnection();

    }

    @Test
    void testInsertMetrics_Success() throws SQLException {
        // Arrange
        MemoryMetricsVariables metrics = new MemoryMetricsVariables(System.currentTimeMillis(), 100000, 500000, "C:\\Windows", "12345");

        // Act
        String result = memoryMetricsCollector.insertMetrics(metrics);

        // Assert
        assertEquals("Metrics inserted successfully!", result);

        // Closing the connection
        connection.close();
    }

    @Test
    void testInsertMetrics_Failure() throws SQLException {
        // Arrange
        // Setting up an invalid metric object with null values
        MemoryMetricsVariables metrics = new MemoryMetricsVariables(0, 0, 0, null, null);

        // Act
        String result = memoryMetricsCollector.insertMetrics(metrics);

        // Assert
        assertEquals("Failed to insert metrics.", result);

        // Closing the connection
        connection.close();
    }
}
