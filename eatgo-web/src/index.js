(async () => {
    const url = 'http://localhost:29903/restaurant';
    const response = await fetch(url);
    const restaurant = await response.json();

    const element = document.getElementById('app');
    element.innerHTML = `
        ${restaurant.map(restaurant => `
            <p>
                ${restaurant.id}
                ${restaurant.name}
                ${restaurant.address}
            </p>        
        `).join('')}
    `;
})();