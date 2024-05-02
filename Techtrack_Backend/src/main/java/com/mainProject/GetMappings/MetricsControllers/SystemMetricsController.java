package com.mainProject.GetMappings.MetricsControllers;

import com.mainProject.DatabaseConnector.DatabaseManager;
import com.mainProject.GetMappings.MetricsVariables.SystemMetrics;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class SystemMetricsController {

    @GetMapping("/get_system_metrics_data")
    public List<SystemMetrics> getMetricsData() {
        List<SystemMetrics> metricsDataList = new ArrayList<>();
        try (Connection connection = DatabaseManager.getConnection()) {
            String query = "SELECT * FROM metrics_data_1 ORDER BY timestamp DESC LIMIT 1";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    SystemMetrics data = new SystemMetrics();
                    data.setTimestamp(resultSet.getTimestamp("timestamp"));
                    data.setOsName(resultSet.getString("osName"));
                    data.setOsVersion(resultSet.getString("osVersion"));
                    data.setHostName(resultSet.getString("hostName"));
                    data.setMacAddress(resultSet.getString("macAddress"));
                    data.setSystemManufacturer(resultSet.getString("systemManufacturer"));
                    data.setSystemModel(resultSet.getString("systemModel"));
                    data.setSystemType(resultSet.getString("systemType"));
                    data.setTotalPhysicalMemory(resultSet.getLong("totalPhysicalMemory"));
                    metricsDataList.add(data);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return metricsDataList;
    }
}
