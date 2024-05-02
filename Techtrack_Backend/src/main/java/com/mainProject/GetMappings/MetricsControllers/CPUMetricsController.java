package com.mainProject.GetMappings.MetricsControllers;

import com.mainProject.DatabaseConnector.DatabaseManager;
import com.mainProject.GetMappings.AggregatedMetricsVariables.AggregatedCPUMetrics;
import com.mainProject.GetMappings.MetricsVariables.CPUMetrics;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CPUMetricsController {

    @GetMapping("/get_cpu_metrics")
    public List<CPUMetrics> getCPUMetrics() {
        List<CPUMetrics> cpuMetricsList = new ArrayList<>();
        try (Connection connection = DatabaseManager.getConnection()) {
            String query = "SELECT timestamp, CPU_Load FROM CPU_metrics ORDER BY timestamp DESC LIMIT 20";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Timestamp timestamp = resultSet.getTimestamp("timestamp");
                    int cpuLoad = resultSet.getInt("CPU_Load");



                    CPUMetrics cpuVariable = new CPUMetrics();
                    cpuVariable.setTimestamp(timestamp.getTime());
                    cpuVariable.setLoadPercentage(cpuLoad);

                    cpuMetricsList.add(cpuVariable);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cpuMetricsList;
    }




    @GetMapping("/get_hourly_cpu_metrics")
    public List<AggregatedCPUMetrics> getHourlyCPUMetrics() {
        String query = "SELECT HOUR(timestamp) AS hour_var, AVG(CPU_Load) AS avg_CPU_Load FROM CPU_metrics WHERE DATE(timestamp) = CURRENT_DATE GROUP BY hour_var ORDER BY hour_var ASC";
        return getAggregatedCPUMetrics( "hour",query);
    }

    @GetMapping("/get_weekly_cpu_metrics")
    public List<AggregatedCPUMetrics> getWeeklyCPUMetrics() {
        String query ="SELECT DAYNAME(timestamp) AS day_var, AVG(CPU_Load) AS avg_CPU_Load FROM CPU_metrics WHERE WEEK(timestamp) = WEEK(CURRENT_DATE) GROUP BY day_var ORDER BY day_var ASC";
        return getAggregatedCPUMetrics( "day",query);
    }

    @GetMapping("/get_monthly_cpu_metrics")
    public List<AggregatedCPUMetrics> getMonthlyCPUMetrics() {
        String query = "SELECT YEAR(timestamp) AS year, MONTH(timestamp) AS month_var, AVG(CPU_Load) AS avg_CPU_Load FROM CPU_metrics GROUP BY year, month_var ORDER BY year ASC, month_var ASC";
        return getAggregatedCPUMetrics("month", query);
    }

    private List<AggregatedCPUMetrics> getAggregatedCPUMetrics(String tableName, String querygiven) {
        List<AggregatedCPUMetrics> aggregatedMetricsList = new ArrayList<>();
        try (Connection connection = DatabaseManager.getConnection()) {
            String query = querygiven;
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    double avgCPULoad = resultSet.getDouble("avg_CPU_Load");

                    // Set the appropriate interval based on the aggregation level
                    AggregatedCPUMetrics aggregatedMetrics = new AggregatedCPUMetrics();
                    switch (tableName) {
                        case "hour":
                            aggregatedMetrics.setHour(resultSet.getInt(tableName+"_var"));
                            break;
                        case "day":
                            aggregatedMetrics.setWeek(resultSet.getString(tableName+"_var"));
                            break;
                        case "month":
                            aggregatedMetrics.setYear(resultSet.getInt("year"));
                            aggregatedMetrics.setMonth(resultSet.getInt(tableName+"_var"));
                            break;
                        default:
                            break;
                    }
                    aggregatedMetrics.setAvgCPULoad(avgCPULoad);

                    aggregatedMetricsList.add(aggregatedMetrics);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aggregatedMetricsList;
    }


}
