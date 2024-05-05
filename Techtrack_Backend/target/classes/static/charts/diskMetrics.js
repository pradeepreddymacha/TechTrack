document.addEventListener('DOMContentLoaded', function() {
    const charts = {
        diskChart: diskChart('diskChart', 'Disk Space (GB)', 'rgba(255, 206, 86, 0.5)')
    };

    function diskChart(chartId, label, backgroundColor) {
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

    function updateDiskCharts() {
        // Update CPU metrics
        fetch('/get_disk_metrics')
            .then(response => response.json())
            .then(data => {
                const diskData = data;
                const diskChart = charts['diskChart'];
                console.log(data);
                const diskLoadData = diskData.map(entry => entry.freeSpace);
                diskChart.data.datasets[0].data = diskLoadData;
                diskChart.update();
            })
            .catch(error => console.error('Error fetching Disk metrics:', error));
    }

    updateDiskCharts();
    setInterval(updateDiskCharts, 5000); // Update every 5 seconds
});
