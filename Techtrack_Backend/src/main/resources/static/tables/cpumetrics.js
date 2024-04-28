document.addEventListener('DOMContentLoaded', function() {
    // Function to populate tables with CPU load data from the server
    function populateTableFromServer(tableId, data) {
        const table = document.getElementById(tableId);
        let html = '<tr>';
        if (tableId === 'dailyCpuLoad') {
            html += '<th>Hour</th>';
        } else if (tableId === 'weeklyCpuLoad') {
            html += '<th>Week</th>';
        } else if (tableId === 'monthlyCpuLoad') {
            html += '<th>Year</th>';
            html += '<th>Month</th>';
        }
        html += '<th>Average CPU Load (%)</th></tr>';  // Header for tables
        data.forEach(entry => {
            if (tableId === 'dailyCpuLoad') {
                html += `<tr>`;
                html += `<td>${entry.hour}</td>`;
            } else if (tableId === 'weeklyCpuLoad') {
                html += `<td>${entry.week}</td>`;
            } else if (tableId === 'monthlyCpuLoad') {
                html += `<tr><td>${entry.year}</td>`;
                html += `<td>${entry.month}</td>`;
            }
            html += `<td>${entry.avgCPULoad.toFixed(2)}%</td></tr>`;
        });
        table.innerHTML = html;
    }

    // Function to fetch CPU load data from the server and populate tables
    function fetchAndPopulateCPULoadData() {
        fetchCPULoadData('/get_hourly_cpu_metrics', 'dailyCpuLoad');
        fetchCPULoadData('/get_weekly_cpu_metrics', 'weeklyCpuLoad');
        fetchCPULoadData('/get_monthly_cpu_metrics', 'monthlyCpuLoad');
    }

    // Function to fetch CPU load data from the server
    function fetchCPULoadData(endpoint, tableId) {
        fetch(endpoint)
            .then(response => response.json())
            .then(data => {
                // Populate table with received data
                populateTableFromServer(tableId, data);
            })
            .catch(error => console.error('Error fetching CPU load data:', error));
    }

    // Populate tables with CPU load data from the server on initial load
    fetchAndPopulateCPULoadData();

    // Update the tables periodically
    setInterval(fetchAndPopulateCPULoadData, 5000);  // Update every 5 seconds
});