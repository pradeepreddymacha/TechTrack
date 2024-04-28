package com.mainProject.Techtrack;

import com.mainProject.DatabaseConnector.DatabaseManager;
import com.mainProject.userVariables.ProcessMetricsVariables;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

@RestController
public class ProcessMetricsCollector {

    // SQL query to insert data into the table
    private static final String INSERT_QUERY = "INSERT INTO process_metrics_data (timestamp, process_id, name, session_id, memory_usage, cpu_time, start_time, responding) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    @PostMapping("/process_metrics")
    public String insertMetrics(@RequestBody ProcessMetricsVariables metrics) {
        try (
                // Obtaining a connection from the DatabaseManager
                Connection connection = DatabaseManager.getConnection();
                // Creating a statement using the connection object
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)
        ) {
            // Setting parameters for the prepared statement
            preparedStatement.setTimestamp(1, new Timestamp(metrics.getTimestamp()));
            preparedStatement.setString(2, metrics.getProcessId());
            preparedStatement.setString(3, metrics.getName());
            preparedStatement.setString(4, metrics.getSessionId());
            preparedStatement.setDouble(5, metrics.getMemoryUsage());
            preparedStatement.setDouble(6, metrics.getCpuTime());

            if (metrics.getStartTime().isEmpty()) {
                preparedStatement.setTimestamp(7, null);
            } else {
                preparedStatement.setTimestamp(7, Timestamp.valueOf(metrics.getStartTime()));
            }

            preparedStatement.setBoolean(8, metrics.isResponding());

            // Executing the statement
            preparedStatement.executeUpdate();

            return "Metrics inserted successfully!";
        } catch (SQLException e) {
            return "Failed to insert metrics.";
        }
    }
}
