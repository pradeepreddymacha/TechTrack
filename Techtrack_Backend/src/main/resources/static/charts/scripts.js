document.addEventListener('DOMContentLoaded', function() {

    function initChart(chartId, label, backgroundColor, link) {
        const ctx = document.getElementById(chartId).getContext('2d');
        const chart = new Chart(ctx, {
            type: 'line',
            data: {
                labels: Array.from(new Array(10), (_, i) => i + 1),
                datasets: [{
                    label: label,
                    data: Array.from(new Array(10), () => Math.random() * 100),
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

        // Add click event listener
        if (link) {
            ctx.canvas.style.cursor = 'pointer'; // Show pointer cursor on hover
            ctx.canvas.addEventListener('click', () => {
                window.location.href = link; // Redirect to the specified link if available
            });
        }

        return chart;
    }
});
