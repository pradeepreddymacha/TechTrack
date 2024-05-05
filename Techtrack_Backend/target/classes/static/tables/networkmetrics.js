document.addEventListener('DOMContentLoaded', function() {
    function populateProcessTable() {
        const tableBody = document.getElementById('networkTable').getElementsByTagName('tbody')[0];

        // Make an HTTP GET request to fetch process metrics data
        fetch('/get_network_metrics')
            .then(response => response.json())
            .then(data => {
                let html = '';
                data.forEach(process => {
                    html += `<tr>
                                <td>${process.timestamp}</td>
                                <td>${process.connectionName}</td>
                                <td>${process.receivedBytes}</td>
                                <td>${process.receivedPackets}</td>
                                <td>${process.sentBytes}</td>
                                <td>${process.sentPackets}</td>
                            </tr>`;
                });
                tableBody.innerHTML = html;
            })
            .catch(error => {
                console.error('Error fetching process metrics data:', error);
            });
    }

    populateProcessTable();
});
