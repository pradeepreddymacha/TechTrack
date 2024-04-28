document.addEventListener('DOMContentLoaded', function() {
    const charts = {
        networkChart: netChart('networkChart', 'Bytes (MB)', ['rgba(75, 192, 192, 0.5)', 'rgba(255, 206, 86, 0.5)'])
    };

    function netChart(chartId, label, backgroundColor) {
        const ctx = document.getElementById(chartId).getContext('2d');
        return new Chart(ctx, {
            type: 'line',
            data: {
                labels: Array.from(new Array(10), (_, i) => i + 1),
                datasets: [{
                    label: 'Bytes Received (MB)',
                    data: [],
                    backgroundColor: backgroundColor[0],
                    borderColor: backgroundColor[0],
                    fill: false,
                },{
                    label: 'Bytes Sent (MB)',
                    data: [],
                    backgroundColor: backgroundColor[1],
                    borderColor: backgroundColor[1],
                    fill: false
                }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
    }

    function updateDashboardAndCharts() {
        fetch('/networkMetrics') // Endpoint mapping to your Spring Boot controller
            .then(response => response.json())
            .then(data => {
                const networkData = data; // Assuming your JSON response contains the network metrics directly

                const networkChart = charts['networkChart'];
                const receivedBytesData = networkData.map(entry => entry.receivedBytes / (1024 * 1024));
                const sentBytesData = networkData.map(entry => entry.sentBytes / (1024 * 1024));

                networkChart.data.datasets[0].data = receivedBytesData;
                networkChart.data.datasets[1].data = sentBytesData;

                networkChart.update();
            })
            .catch(error => console.error('Error fetching network metrics:', error));
    }

    updateDashboardAndCharts();
    setInterval(updateDashboardAndCharts, 5000);
});