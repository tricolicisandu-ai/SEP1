fetch("../Clovervile/GreenActions.json")
.then(response => response.json())
.then(data => {
    console.log(data);

      let html = ""; // We create an empty string where we'll store list items

    data.greenActions.forEach(greenAction => {
        html += `<li>${greenAction.name} — ${greenAction.greenPoints} points</li>`;
    });

    document.getElementById("greenFocusList").innerHTML = html;
})
.catch(error => console.error('Error fetching JSON:', error));


fetch("../Clovervile/TradeOffers.json")
    .then(response => response.json())
    .then(data => {
        console.log(data);

        let html = "";

        data.offers.forEach(offer => {
            html += `
            <li>
                <strong>${offer.offerName}</strong> — ${offer.pointCost} points<br>
                <span>Seller: ${offer.seller.firstName} ${offer.seller.lastName}</span>
            </li>
            `;
        });

    document.getElementById("tradeOfferList").innerHTML = html;
    })
    .catch(error => console.error('Error fetching JSON:', error));

fetch("../Clovervile/Threshold.json")
    .then(response => response.json())
    .then(data => {
        console.log(data);

      // Insert required points
        document.getElementById("thrsholdPoints").innerHTML =
        `<h1>${data.requiredPoints}</h1>`;

      // Insert description / goal name
        document.getElementById("thrsholdDescription").innerHTML =
        `<h2>TO GET: ${data.goalName}</h2>`;
    })
    .catch(error => console.error("Error fetching threshold JSON:", error));
