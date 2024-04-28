package com.mainProject.Techtrack.InsertionTest;

import com.mainProject.DatabaseConnector.DatabaseManager;
import com.mainProject.Techtrack.DiskMetricsCollector;
import com.mainProject.userVariables.DiskMetricsVariables;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiskMetricsCollectorTest {

    private DiskMetricsCollector diskMetricsCollector;

    private Connection connection;


    @BeforeEach
    void setUp() throws SQLException {
        // Creating a new instance of DiskMetricsCollector
        diskMetricsCollector = new DiskMetricsCollector();
        // Creating a new instance of DatabaseManager
        connection = DatabaseManager.getConnection();
    }

    @Test
    void testInsertMetrics_Success() throws SQLException {
        // Arrange
        DiskMetricsVariables metrics = new DiskMetricsVariables(System.currentTimeMillis(), "Device1", 100000, 500000, "Volume1");

        // Act
        String result = diskMetricsCollector.insertMetrics(metrics);

        // Assert
        assertEquals("Metrics inserted successfully!", result);

        // Closing the connection
        connection.close();
    }

    @Test
    void testInsertMetrics_Failure() throws SQLException {
        // Arrange
        // Setting up an invalid metric object with null values
        DiskMetricsVariables metrics = new DiskMetricsVariables(0, null, 0, 0, null);

        // Act
        String result = diskMetricsCollector.insertMetrics(metrics);

        // Assert
        assertEquals("Failed to insert metrics.", result);

        // Closing the connection
        connection.close();
    }
}
