document.addEventListener('DOMContentLoaded', function() {
    function populateProcessTable() {
        const tableBody = document.getElementById('processTable').getElementsByTagName('tbody')[0];

        // Make an HTTP GET request to fetch process metrics data
        fetch('/get_process_metrics_data')
            .then(response => response.json())
            .then(data => {
                let html = '';
                data.forEach(process => {
                    html += `<tr>
                                <td>${process.timestamp}</td>
                                <td>${process.processId}</td>
                                <td>${process.name}</td>
                                <td>${process.sessionId}</td>
                                <td>${process.memoryUsage} MB</td>
                                <td>${process.cpuTime} sec</td>
                                <td>${process.responding ? 'Yes' : 'No'}</td>
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
