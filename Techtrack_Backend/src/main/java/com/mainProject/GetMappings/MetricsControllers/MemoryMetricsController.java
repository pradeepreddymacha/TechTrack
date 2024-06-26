package com.mainProject.GetMappings.MetricsControllers;

import com.mainProject.DatabaseConnector.DatabaseManager;
import com.mainProject.GetMappings.AggregatedMetricsVariables.AggregatedMemoryMetrics;
import com.mainProject.GetMappings.MetricsVariables.MemoryMetrics;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@RestController
public class MemoryMetricsController {

    @GetMapping("/get_memory_metrics")
    public List<MemoryMetrics> getMemoryMetrics() {
        List<MemoryMetrics> memoryMetricsList = new ArrayList<>();
        // Establishing a connection to the database
        try (Connection connection = DatabaseManager.getConnection()) {
            // Creating the SQL query to retrieve memory metrics
            String query = "SELECT timestamp, FreePhysicalMemory FROM memory_metrics ORDER BY timestamp DESC LIMIT 20";
            // Executing the query and retrieving results
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                // Iterating through the result set and populating memory metrics list
                while (resultSet.next()) {
                    // Extracting memory metrics from the result set
                    Timestamp timestamp = resultSet.getTimestamp("timestamp");
                    long freePhysicalMemory = resultSet.getLong("FreePhysicalMemory");

                    // Creating a MemoryMetrics object and setting its properties
                    MemoryMetrics memoryMetric = new MemoryMetrics();
                    memoryMetric.setTimestamp(timestamp.getTime());
                    memoryMetric.setFreePhysicalMemory(freePhysicalMemory/1024);

                    // Adding the memory metrics object to the list
                    memoryMetricsList.add(memoryMetric);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Returning the list of memory metrics
        return memoryMetricsList;
    }


    @GetMapping("/get_hourly_memory_metrics")
    public List<AggregatedMemoryMetrics> getHourlyMemoryMetrics() {
        String query = "SELECT HOUR(timestamp) AS hour_var, AVG(FreePhysicalMemory) AS avg_FreePhysicalMemory, AVG(TotalVisibleMemorySize) AS avg_TotalVisibleMemorySize FROM memory_metrics WHERE DATE(timestamp) = CURRENT_DATE GROUP BY hour_var ORDER BY hour_var ASC";
        return getAggregatedMemoryMetrics("hour", query);
    }

    @GetMapping("/get_weekly_memory_metrics")
    public List<AggregatedMemoryMetrics> getWeeklyMemoryMetrics() {
        String query = "SELECT DAYNAME(timestamp) AS day_var, AVG(FreePhysicalMemory) AS avg_FreePhysicalMemory, AVG(TotalVisibleMemorySize) AS avg_TotalVisibleMemorySize FROM memory_metrics WHERE WEEK(timestamp) = WEEK(CURRENT_DATE) GROUP BY day_var ORDER BY day_var ASC";
        return getAggregatedMemoryMetrics("day", query);
    }

    @GetMapping("/get_monthly_memory_metrics")
    public List<AggregatedMemoryMetrics> getMonthlyMemoryMetrics() {
        String query = "SELECT YEAR(timestamp) AS year, MONTH(timestamp) AS month_var, AVG(FreePhysicalMemory) AS avg_FreePhysicalMemory, AVG(TotalVisibleMemorySize) AS avg_TotalVisibleMemorySize FROM memory_metrics GROUP BY year, month_var ORDER BY year ASC, month_var ASC";
        return getAggregatedMemoryMetrics("month", query);
    }

    private List<AggregatedMemoryMetrics> getAggregatedMemoryMetrics(String tableName, String queryGiven) {
        List<AggregatedMemoryMetrics> aggregatedMetricsList = new ArrayList<>();
        try (Connection connection = DatabaseManager.getConnection()) {
            String query = queryGiven;
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    double avgFreePhysicalMemory = resultSet.getDouble("avg_FreePhysicalMemory");
                    double avgTotalVisibleMemorySize = resultSet.getDouble("avg_TotalVisibleMemorySize");

                    // Set the appropriate interval based on the aggregation level
                    AggregatedMemoryMetrics aggregatedMetrics = new AggregatedMemoryMetrics();
                    switch (tableName) {
                        case "hour":
                            aggregatedMetrics.setHour(resultSet.getInt(tableName + "_var"));
                            break;
                        case "day":
                            aggregatedMetrics.setDay(resultSet.getString(tableName + "_var"));
                            break;
                        case "month":
                            aggregatedMetrics.setYear(resultSet.getInt("year"));
                            aggregatedMetrics.setMonth(resultSet.getInt(tableName + "_var"));
                            break;
                        default:
                            break;
                    }
                    aggregatedMetrics.setAvgFreePhysicalMemory(avgFreePhysicalMemory);
                    aggregatedMetrics.setAvgTotalVisibleMemorySize(avgTotalVisibleMemorySize);

                    aggregatedMetricsList.add(aggregatedMetrics);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aggregatedMetricsList;
    }
}


