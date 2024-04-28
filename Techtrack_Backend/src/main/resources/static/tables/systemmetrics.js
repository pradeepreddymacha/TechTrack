document.addEventListener('DOMContentLoaded', function() {
    function fetchMetricsData() {
        fetch('/get_system_metrics_data')
            .then(response => response.json())
            .then(metricsData => {
                console.log(metricsData[0]);

                const latestData = metricsData[0]; // Assuming the latest data is at index 0
                //document.getElementById('timestamp').textContent = new Date(latestData.timestamp).toLocaleString();
                document.getElementById('osName').textContent = latestData.osName;
                document.getElementById('osVersion').textContent = latestData.osVersion;
                document.getElementById('hostName').textContent = latestData.hostName;
                document.getElementById('macAddress').textContent = latestData.macAddress;
                document.getElementById('systemManufacturer').textContent = latestData.systemManufacturer;
                document.getElementById('systemModel').textContent = latestData.systemModel;
                document.getElementById('systemType').textContent = latestData.systemType;
                document.getElementById('totalPhysicalMemory').textContent = `${latestData.totalPhysicalMemory} bytes`;
            })
            .catch(error => console.error('Error fetching metrics data:', error));
    }

    fetchMetricsData();
    setInterval(fetchMetricsData, 50000);
});
