document.addEventListener('DOMContentLoaded', function() {
    const charts = {
        memoryChart: memoryChart('memoryChart', 'Free Physical Memory (MB)', 'rgba(54, 162, 235, 0.5)')
    };

    function memoryChart(chartId, label, backgroundColor) {
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

    function updateMemoryCharts() {
        // Update CPU metrics
        fetch('/get_memory_metrics')
            .then(response => response.json())
            .then(data => {
                const memoryData = data;
                const memoryChart = charts['memoryChart'];
                const memoryLoadData = memoryData.map(entry => entry.freePhysicalMemory);
                memoryChart.data.datasets[0].data = memoryLoadData;
                memoryChart.update();
            })
            .catch(error => console.error('Error fetching Memory metrics:', error));
    }

    updateMemoryCharts();
    setInterval(updateMemoryCharts, 5000); // Update every 5 seconds
});
