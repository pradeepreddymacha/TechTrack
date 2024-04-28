document.addEventListener('DOMContentLoaded', function() {
    // Function to populate memory metrics table from server data
    function populateMemoryTableFromServer(tableId, intervalLabel, endpoint) {
        const table = document.getElementById(tableId);
        fetch(endpoint)
            .then(response => response.json())
            .then(data => {
                let html = '<tr>';
                if (intervalLabel === 'Hourly') {
                    html += '<th>Hour</th>';
                } else if (intervalLabel === 'Weekly') {
                    html += '<th>Week</th>';
                } else if (intervalLabel === 'Monthly') {
                    html += '<th>Month</th>';
                }
                html += '<th>Free Physical Memory</th><th>Total Visible Memory Size</th></tr>';
                data.forEach(entry => {
                    let timestamp = new Date(entry.timestamp);

                });

                data.forEach(entry => {
                    html += '<tr>';
                    if (tableId === 'hourlyMemoryMetrics') {
                        html += `<td>${entry.hour}</td>`;
                    } else if (tableId === 'weeklyMemoryMetrics') {
                        html += `<td>${entry.day}</td>`;
                    } else if (tableId === 'monthlyMemoryMetrics') {
                        html += `<td>${entry.month}</td>`;
                    }
                    html += `<td>${entry.avgFreePhysicalMemory} MB</td><td>${entry.avgTotalVisibleMemorySize} MB</td></tr>`;
                });


                table.innerHTML = html;
            })
            .catch(error => console.error('Error fetching memory metrics data:', error));
    }

    // Populate memory metrics tables
    populateMemoryTableFromServer('hourlyMemoryMetrics', 'Hourly', '/get_hourly_memory_metrics');
    populateMemoryTableFromServer('weeklyMemoryMetrics', 'Weekly', '/get_weekly_memory_metrics');
    populateMemoryTableFromServer('monthlyMemoryMetrics', 'Monthly', '/get_monthly_memory_metrics');
});
