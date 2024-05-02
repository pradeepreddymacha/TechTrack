package com.mainProject.GetMappings.MetricsControllers;

import com.mainProject.DatabaseConnector.DatabaseManager;
import com.mainProject.GetMappings.MetricsVariables.ProcessMetrics;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@RestController
public class ProcessMetricsController {

    @GetMapping("/get_process_metrics_data")
    public List<ProcessMetrics> getMetricsData() {
        List<ProcessMetrics> metricsDataList = new ArrayList<>();
        try (Connection connection = DatabaseManager.getConnection()) {
            String query = "SELECT * FROM process_metrics_data WHERE timestamp = (SELECT MAX(timestamp) FROM process_metrics_data) ORDER BY cpu_time DESC";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    ProcessMetrics data = new ProcessMetrics();
                    data.setTimestamp(resultSet.getTimestamp("timestamp"));
                    data.setProcessId(resultSet.getString("process_id"));
                    data.setName(resultSet.getString("name"));
                    data.setSessionId(resultSet.getString("session_id"));
                    data.setMemoryUsage(resultSet.getDouble("memory_usage"));
                    data.setCpuTime(resultSet.getDouble("cpu_time"));
                    data.setResponding(resultSet.getBoolean("responding"));
                    metricsDataList.add(data);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return metricsDataList;
    }

}