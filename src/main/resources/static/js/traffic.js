async function refreshTraffic() {
    try {
        const response = await fetch('/api/public/status', { cache: 'no-store' });
        if (!response.ok) return;
        const statuses = await response.json();

        for (const status of statuses) {
            const selector = `[data-junction-id="${status.junctionId}"]`;
            document.querySelectorAll(selector).forEach(card => {
                // Deliberately update values only. Do not replace the card HTML: this keeps
                // the correct junction name, manual-override form and hidden junction id.
                updateText(card, 'vehicles', status.vehicleCount);
                updateText(card, 'speed', status.speedKmh);
                updateText(card, 'occupancy', status.occupancyPercent);
                updateText(card, 'queue', status.queueLength);
                updateText(card, 'road', status.roadCondition);
                updateText(card, 'green', status.greenSeconds);
                updateText(card, 'emergency', status.emergencyPriority ? 'ACTIVE' : 'Inactive');
                updateText(card, 'override', status.manualOverride ? 'ACTIVE' : 'Inactive');

                const badge = card.querySelector('[data-field="congestion"]');
                if (badge) {
                    badge.textContent = status.congestion;
                    badge.className = `badge ${status.congestion}`;
                }
            });
        }
    } catch (error) {
        console.debug('Traffic refresh unavailable', error);
    }
}

function updateText(card, field, value) {
    const element = card.querySelector(`[data-field="${field}"]`);
    if (element) element.textContent = value;
}

setInterval(refreshTraffic, 5000);
