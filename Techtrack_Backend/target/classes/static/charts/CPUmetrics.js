document.addEventListener('DOMContentLoaded', function() {
    const charts = {
        cpuChart: cpuChart('cpuChart', 'CPU Load', ['rgba(255, 99, 132, 0.5)'])
    };

    function cpuChart(chartId, label, backgroundColor) {
        const ctx = document.getElementById(chartId).getContext('2d');
        return new Chart(ctx, {
            type: 'line',
            data: {
                labels: Array.from(new Array(10), (_, i) => i + 1),
                datasets: [{
                    label: label,
                    data: [],
                    backgroundColor: backgroundColor,
                    borderColor: backgroundColor,
                    fill: false,
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

    function updateCPUCharts() {
        // Update CPU metrics
        fetch('/get_cpu_metrics')
            .then(response => response.json())
            .then(data => {
                const cpuData = data;
                const cpuChart = charts['cpuChart'];
                const cpuLoadData = cpuData.map(entry => entry.loadPercentage);
                cpuChart.data.datasets[0].data = cpuLoadData;
                cpuChart.update();
            })
            .catch(error => console.error('Error fetching CPU metrics:', error));
    }

    updateCPUCharts();
    setInterval(updateCPUCharts, 5000); // Update every 5 seconds
});
