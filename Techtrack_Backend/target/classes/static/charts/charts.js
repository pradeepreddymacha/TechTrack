
document.addEventListener('DOMContentLoaded', function () {
    const cpuChart = document.getElementById('cpuChart');
    const memoryChart = document.getElementById('memoryChart');
    const diskChart = document.getElementById('diskChart');
    const networkChart = document.getElementById('networkChart');

    cpuChart.addEventListener('click', function () {
        window.location.href = 'HTML-files/cpumetrics.html';
    });

    memoryChart.addEventListener('click', function () {
        window.location.href = 'HTML-files/memorymetrics.html';
    });

    diskChart.addEventListener('click', function () {
        window.location.href = 'HTML-files/diskmetrics.html';
    });

    networkChart.addEventListener('click', function () {
        window.location.href = 'HTML-files/networkmetrics.html';
    });
});
