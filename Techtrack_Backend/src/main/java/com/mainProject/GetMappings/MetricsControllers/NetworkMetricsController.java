package com.mainProject.GetMappings.MetricsControllers;

import com.mainProject.DatabaseConnector.DatabaseManager;
import com.mainProject.GetMappings.AggregatedMetricsVariables.AggregatedNetworkMetrics;
import com.mainProject.GetMappings.MetricsVariables.NetworkMetrics;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class NetworkMetricsController {

    @GetMapping("/networkMetrics")
    public List<NetworkMetrics> getNetworkMetrics() {
        List<NetworkMetrics> networkMetricsList = new ArrayList<>();
        try (Connection connection = DatabaseManager.getConnection()) {
            String query = "SELECT received_bytes,sent_bytes,timestamp FROM network_metrics ORDER BY timestamp DESC LIMIT 20";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    long receivedBytes = resultSet.getLong("received_bytes");
                    long sentBytes = resultSet.getLong("sent_bytes");
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

    @GetMapping("/get_network_metrics")
    public List<AggregatedNetworkMetrics> getAllNetworkMetrics() {
        List<AggregatedNetworkMetrics> networkMetricsList = new ArrayList<>();
        try (Connection connection = DatabaseManager.getConnection()) {
            String query = "SELECT * FROM network_metrics ORDER BY timestamp DESC LIMIT 20";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    // Assuming NetworkMetrics is a POJO representing your network metrics
                    AggregatedNetworkMetrics networkMetrics = new AggregatedNetworkMetrics();
                    networkMetrics.setConnectionName(resultSet.getString("connection_name"));
                    networkMetrics.setReceivedBytes(resultSet.getLong("received_bytes"));
                    networkMetrics.setSentBytes(resultSet.getLong("sent_bytes"));
                    networkMetrics.setReceivedPackets(resultSet.getLong("unicast_packets"));
                    networkMetrics.setSentPackets(resultSet.getLong("sent_unicast_packets"));
                    networkMetrics.setTimestamp(resultSet.getTimestamp("timestamp"));
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

