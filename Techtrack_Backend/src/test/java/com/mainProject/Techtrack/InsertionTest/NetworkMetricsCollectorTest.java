package com.mainProject.Techtrack.InsertionTest;

import com.mainProject.DatabaseConnector.DatabaseManager;
import com.mainProject.Techtrack.NetworkMetricsCollector;
import com.mainProject.userVariables.NetworkMetricsVariables;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NetworkMetricsCollectorTest {

    private NetworkMetricsCollector networkMetricsCollector;
    private Connection connection;


    @BeforeEach
    void setUp() throws SQLException {
        // Creating a new instance of NetworkMetricsCollector
        networkMetricsCollector = new NetworkMetricsCollector();

        // Getting a connection from DatabaseManager
        connection = DatabaseManager.getConnection();

    }

    @Test
    void testInsertMetrics_Success() throws SQLException {
        // Arrange
        NetworkMetricsVariables metrics = new NetworkMetricsVariables(System.currentTimeMillis(), "Connection1", 100000, 500, 200000, 250);

        // Act
        String result = networkMetricsCollector.insertMetrics(metrics);

        // Assert
        assertEquals("Metrics inserted successfully!", result);

        // Closing the connection
        connection.close();
    }

    @Test
    void testInsertMetrics_Failure() throws SQLException {
        // Arrange
        // Setting up an invalid metric object with null values
        NetworkMetricsVariables metrics = new NetworkMetricsVariables(0, null, 0, 0, 0, 0);

        // Act
        String result = networkMetricsCollector.insertMetrics(metrics);

        // Assert
        assertEquals("Failed to insert metrics.", result);

        // Closing the connection
        connection.close();
    }
}
