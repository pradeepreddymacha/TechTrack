package com.mainProject.Techtrack;

import com.mainProject.DatabaseConnector.DatabaseManager;
import com.mainProject.userVariables.NetworkMetricsVariables;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

@RestController
public class NetworkMetricsCollector {

    // SQL query to insert data into the table
    private static final String INSERT_QUERY = "INSERT INTO network_metrics (timestamp, connection_name ,received_bytes , unicast_packets, sent_bytes, sent_unicast_packets) VALUES (?, ?, ?, ?, ?, ?)";

    @PostMapping("/Network_metrics")
    public String insertMetrics(@RequestBody NetworkMetricsVariables metrics) {
        try (
                // Obtaining a connection from the DatabaseManager
                Connection connection = DatabaseManager.getConnection();
                // Creating a statement using the connection object
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)
        ) {
            // Setting parameters for the prepared statement
            preparedStatement.setTimestamp(1, new Timestamp(metrics.getTimestamp()));
            preparedStatement.setString(2, metrics.getConnectionName());
            preparedStatement.setLong(3, metrics.getReceivedBytes());
            preparedStatement.setLong(4, metrics.getUnicastPackets());
            preparedStatement.setLong(5, metrics.getSentBytes());
            preparedStatement.setLong(6, metrics.getSentUnicastPackets());

            // Executing the statement
            preparedStatement.executeUpdate();

            return "Metrics inserted successfully!";
        } catch (SQLException e) {
            return "Failed to insert metrics.";
        }
    }
}
