package com.mainProject.GetMappings;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class NetworkMetricsController {

    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/sampleusers";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "toor";

    @GetMapping("/networkMetrics")
    public List<NetworkMetrics> getNetworkMetrics() {
        List<NetworkMetrics> networkMetricsList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD)) {
            String query = "SELECT received_bytes,sent_bytes,timestamp FROM network_metrics ORDER BY timestamp DESC LIMIT 20";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    long receivedBytes = resultSet.getInt("received_bytes");
                    long sentBytes = resultSet.getInt("sent_bytes");
                    Timestamp tmstp = resultSet.getTimestamp("timestamp");

                    //System.out.println(sentBytes);

                    // Assuming NetworkMetrics is a POJO representing your network metrics
                    NetworkMetrics networkMetrics = new NetworkMetrics();
                    networkMetrics.setReceivedBytes(receivedBytes);
                    networkMetrics.setSentBytes(sentBytes);
                    networkMetrics.setTimestamp(tmstp);
                    networkMetricsList.add(networkMetrics);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQLException appropriately
        }
        return networkMetricsList;
    }
}

