document.addEventListener('DOMContentLoaded', function() {
    // Function to populate tables with disk metrics data from the server
    function populateTableFromServer(tableId, data) {
        const table = document.getElementById(tableId);
        let html = '<tr>';
        if (tableId === 'hourlyDiskMetrics') {
            html += '<th>Hour</th>';
        } else if (tableId === 'weeklyDiskMetrics') {
            html += '<th>Week</th>';
        } else if (tableId === 'monthlyDiskMetrics') {
            html += '<th>Month</th>';
        }
        html += '<th>Free Space (GB)</th><th>Size (GB)</th><th>Volume Name</th></tr>';  // Header for tables
        data.forEach(entry => {
            html += '<tr>';
            if (tableId === 'hourlyDiskMetrics') {
                html += `<td>${entry.hour}</td>`;
            } else if (tableId === 'weeklyDiskMetrics') {
                html += `<td>${entry.week}</td>`;
            } else if (tableId === 'monthlyDiskMetrics') {
                html += `<td>${entry.month}</td>`;
            }
            html += `<td>${entry.avgFreeSpace.toFixed(2)}</td><td>${entry.avgSize.toFixed(2)}</td><td>${entry.volumeName}</td></tr>`;
        });
        table.innerHTML = html;
    }

    // Function to fetch disk metrics data from the server and populate tables
    function fetchAndPopulateDiskMetricsData() {
        fetchDiskMetricsData('/get_hourly_disk_metrics', 'hourlyDiskMetrics');
        fetchDiskMetricsData('/get_weekly_disk_metrics', 'weeklyDiskMetrics');
        fetchDiskMetricsData('/get_monthly_disk_metrics', 'monthlyDiskMetrics');
    }

    // Function to fetch disk metrics data from the server
    function fetchDiskMetricsData(endpoint, tableId) {
        fetch(endpoint)
            .then(response => response.json())
            .then(data => {
                // Populate table with received data
                populateTableFromServer(tableId, data);
            })
            .catch(error => console.error('Error fetching disk metrics data:', error));
    }

    // Populate tables with disk metrics data from the server on initial load
    fetchAndPopulateDiskMetricsData();

    // Update the tables periodically
    setInterval(fetchAndPopulateDiskMetricsData, 5000);  // Update every 5 seconds
});
