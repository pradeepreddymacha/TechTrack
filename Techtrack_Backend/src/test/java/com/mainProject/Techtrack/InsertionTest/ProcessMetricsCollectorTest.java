package com.mainProject.Techtrack.InsertionTest;

import com.mainProject.DatabaseConnector.DatabaseManager;
import com.mainProject.Techtrack.ProcessMetricsCollector;
import com.mainProject.userVariables.ProcessMetricsVariables;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProcessMetricsCollectorTest {

    private ProcessMetricsCollector processMetricsCollector;
    private Connection connection;

    @BeforeEach
    void setUp() throws SQLException {
        // Creating a new instance of ProcessMetricsCollector
        processMetricsCollector = new ProcessMetricsCollector();

        // Getting a connection from DatabaseManager
        connection = DatabaseManager.getConnection();
    }

    @Test
    void testInsertMetrics_Success() throws SQLException {
        // Arrange
        ProcessMetricsVariables metrics = new ProcessMetricsVariables( System.currentTimeMillis(),"Process123", "Test Process", "Session123", 1024.5, 10.5, "2024-04-20 12:00:00", true);

        // Act
        String result = processMetricsCollector.insertMetrics(metrics);

        // Assert
        assertEquals("Metrics inserted successfully!", result);

        // Closing the connection
        connection.close();
    }

    @Test
    void testInsertMetrics_Failure() throws SQLException {
        // Arrange
        // Setting up an invalid metric object with null values
        ProcessMetricsVariables metrics = new ProcessMetricsVariables(0, null, null, null, 0, 0, "", false);

        // Act
        String result = processMetricsCollector.insertMetrics(metrics);

        // Assert
        assertEquals("Failed to insert metrics.", result);

        // Closing the connection
        connection.close();
    }
}
