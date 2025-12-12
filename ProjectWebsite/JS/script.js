fetch("GreenActions.json")
    .then(response => response.json())
    .then(data =>
    {
        console.log(data);
        document.getElementById("greenFocusList").innerHTML = data.greenActions[0].name + " "+ data.greenActions[0].greenPoints;
    })

    .catch(error => console.error('Error fetching JSON: ', error));

