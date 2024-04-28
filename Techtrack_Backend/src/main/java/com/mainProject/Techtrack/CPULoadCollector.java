package com.mainProject.Techtrack;

import com.mainProject.DatabaseConnector.DatabaseManager;
import com.mainProject.userVariables.CPUVaraiable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;





@RestController
public class CPULoadCollector {

    // SQL query to insert data into the table
    public static final String INSERT_QUERY = "INSERT INTO CPU_metrics (timestamp, CPU_Load) VALUES (?, ?)";

    @PostMapping("/cpu_metrics")
    public String insertMetrics(@RequestBody CPUVaraiable metrics) {
        try (
                // Obtaining a connection from the DatabaseManager
                Connection connection = DatabaseManager.getConnection();
                // Creating a statement using the connection object
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)
        ) {
            // Setting parameters for the prepared statement
            preparedStatement.setTimestamp(1, new Timestamp(metrics.getTimestamp()));
            preparedStatement.setLong(2, metrics.getLoadPercentage());


            // Executing the statement
            preparedStatement.executeUpdate();

            return "Metrics inserted successfully!";
        } catch (SQLException e) {
            return "Failed to insert metrics.";
        }
    }
}


