document.addEventListener('DOMContentLoaded', function() {
    function populateNetworkTable(tableId, count) {
        const tableBody = document.getElementById(tableId).querySelector('tbody');
        let html = '';
        for (let i = 0; i < count; i++) {
            const now = new Date();
            now.setHours(now.getHours() - i);
            html += `<tr>
                        <td>${now.toLocaleTimeString()}</td>
                        <td>Conn${Math.floor(Math.random() * 100)}</td>
                        <td>${Math.floor(Math.random() * 1000000)} bytes</td>
                        <td>${Math.floor(Math.random() * 1000)}</td>
                        <td>${Math.floor(Math.random() * 1000000)} bytes</td>
                        <td>${Math.floor(Math.random() * 1000)}</td>
                     </tr>`;
        }
        tableBody.innerHTML = html;
    }

    // Populate tables for daily, weekly, and monthly metrics
    populateNetworkTable('dailyNetworkLoad', 24); // Simulate 24 hours of data
    populateNetworkTable('weeklyNetworkLoad', 7); // Simulate 7 days of data
    populateNetworkTable('monthlyNetworkLoad', 30); // Simulate 30 days of data
});
