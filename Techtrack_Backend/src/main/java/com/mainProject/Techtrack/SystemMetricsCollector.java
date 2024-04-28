package com.mainProject.Techtrack;

import com.mainProject.DatabaseConnector.DatabaseManager;
import com.mainProject.userVariables.SystemMetricsVariables;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

@RestController
public class SystemMetricsCollector {

    // SQL query to insert data into the table
    private static final String INSERT_QUERY = "INSERT INTO metrics_data_1 (timestamp, osName, osVersion, hostName, macAddress, systemManufacturer, systemModel, systemType, totalPhysicalMemory) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    @PostMapping("/metrics_new")
    public String insertMetrics(@RequestBody SystemMetricsVariables metrics) {
        try (
                // Obtaining a connection from the DatabaseManager
                Connection connection = DatabaseManager.getConnection();
                // Creating a statement using the connection object
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)
        ) {
            // Setting parameters for the prepared statement
            preparedStatement.setTimestamp(1, new Timestamp(metrics.getTimestamp()));
            preparedStatement.setString(2, metrics.getOsName());
            preparedStatement.setString(3, metrics.getOsVersion());
            preparedStatement.setString(4, metrics.getHostName());
            preparedStatement.setString(5, metrics.getMacAddress());
            preparedStatement.setString(6, metrics.getSystemManufacturer());
            preparedStatement.setString(7, metrics.getSystemModel());
            preparedStatement.setString(8, metrics.getSystemType());
            preparedStatement.setLong(9, metrics.getTotalPhysicalMemory());

            // Executing the statement
            preparedStatement.executeUpdate();

            return "Metrics inserted successfully!";
        } catch (SQLException e) {
            return "Failed to insert metrics.";
        }
    }
}
