package com.mainProject.Techtrack;

import com.mainProject.DatabaseConnector.DatabaseManager;
import com.mainProject.userVariables.MemoryMetricsVariables;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;





@RestController
public class MemoryMetricsCollector {

    // SQL query to insert data into the table
    private static final String INSERT_QUERY = "INSERT INTO memory_metrics (timestamp, FreePhysicalMemory, TotalVisibleMemorySize, SystemDirectory, BuildNumber) VALUES (?, ?, ?, ?, ?)";

    @PostMapping("/memory_metrics")
    public String insertMetrics(@RequestBody MemoryMetricsVariables metrics) {
        try (
                // Obtaining a connection from the DatabaseManager
                Connection connection = DatabaseManager.getConnection();
                // Creating a statement using the connection object
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)
        ) {
            // Setting parameters for the prepared statement
            preparedStatement.setTimestamp(1, new Timestamp(metrics.getTimestamp()));
            preparedStatement.setLong(2, metrics.getFreePhysicalMemory());
            preparedStatement.setLong(3, metrics.getTotalVisibleMemorySize());
            preparedStatement.setString(4, metrics.getSystemDirectory());
            preparedStatement.setString(5, metrics.getBuildNumber());

            // Executing the statement
            preparedStatement.executeUpdate();

            return "Metrics inserted successfully!";
        } catch (SQLException e) {
            return "Failed to insert metrics.";
        }
    }
}

