package com.mainProject.GetMappings;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DiskMetricsController {

    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/sampleusers";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "toor";

    @GetMapping("/get_disk_metrics")
    public List<DiskMetrics> getDiskMetrics() {
        List<DiskMetrics> diskMetricsList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD)) {
            String query = "SELECT timestamp, FreeSpace, Size FROM disk_metrics ORDER BY timestamp DESC LIMIT 20";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Timestamp timestamp = resultSet.getTimestamp("timestamp");
                    long diskVolume = resultSet.getLong("Size");
                    long freeSpace = resultSet.getLong("FreeSpace");


                    DiskMetrics diskVariable = new DiskMetrics();
                    diskVariable.setTimestamp(timestamp.getTime());
                    diskVariable.setFreeSpace(freeSpace/(1024*1024*1024));
                    diskVariable.setVolumeSize(diskVolume/(1024*1024*1024));

                    diskMetricsList.add(diskVariable);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return diskMetricsList;
    }

    @GetMapping("/get_hourly_disk_metrics")
    public List<AggregatedDiskMetrics> getHourlyDiskMetrics() {
        String query = "SELECT HOUR(timestamp) AS hour_var, AVG(FreeSpace) AS avg_FreeSpace, AVG(Size) AS avg_Size, VolumeName FROM disk_metrics WHERE DATE(timestamp) = CURRENT_DATE GROUP BY hour_var, VolumeName ORDER BY hour_var ASC";
        return getAggregatedDiskMetrics("hour", query);
    }

    @GetMapping("/get_weekly_disk_metrics")
    public List<AggregatedDiskMetrics> getWeeklyDiskMetrics() {
        String query = "SELECT DAYNAME(timestamp) AS day_var, AVG(FreeSpace) AS avg_FreeSpace, AVG(Size) AS avg_Size, VolumeName FROM disk_metrics WHERE WEEK(timestamp) = WEEK(CURRENT_DATE) GROUP BY day_var, VolumeName ORDER BY day_var ASC";
        return getAggregatedDiskMetrics("day", query);
    }

    @GetMapping("/get_monthly_disk_metrics")
    public List<AggregatedDiskMetrics> getMonthlyDiskMetrics() {
        String query = "SELECT YEAR(timestamp) AS year, MONTH(timestamp) AS month_var, AVG(FreeSpace) AS avg_FreeSpace, AVG(Size) AS avg_Size, VolumeName FROM disk_metrics GROUP BY year, month_var, VolumeName ORDER BY year ASC, month_var ASC";
        return getAggregatedDiskMetrics("month", query);
    }

    private List<AggregatedDiskMetrics> getAggregatedDiskMetrics(String tableName, String queryGiven) {
        List<AggregatedDiskMetrics> aggregatedMetricsList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD)) {
            String query = queryGiven;
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    double avgFreeSpace = Math.round(resultSet.getDouble("avg_FreeSpace") * 100.0 / (1024 * 1024 * 1024)) / 100.0;
                    double avgSize = Math.round(resultSet.getDouble("avg_Size") * 100.0 / (1024 * 1024 * 1024)) / 100.0;
                    String volumeName = resultSet.getString("VolumeName");

                    // Set the appropriate interval based on the aggregation level
                    AggregatedDiskMetrics aggregatedMetrics = new AggregatedDiskMetrics();
                    switch (tableName) {
                        case "hour":
                            aggregatedMetrics.setHour(resultSet.getInt(tableName + "_var"));
                            break;
                        case "day":
                            aggregatedMetrics.setWeek(resultSet.getString(tableName + "_var"));
                            break;
                        case "month":
                            aggregatedMetrics.setYear(resultSet.getInt("year"));
                            aggregatedMetrics.setMonth(resultSet.getInt(tableName + "_var"));
                            break;
                        default:
                            break;
                    }
                    aggregatedMetrics.setAvgFreeSpace(avgFreeSpace);
                    aggregatedMetrics.setAvgSize(avgSize);
                    aggregatedMetrics.setVolumeName(volumeName);

                    aggregatedMetricsList.add(aggregatedMetrics);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aggregatedMetricsList;
    }

}