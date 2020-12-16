async function MakeReqest() {
    let jwt = localStorage.getItem("jwt");
    if (jwt == null) {
        document.location.href = "/login";
    }
    let req = document.getElementById("championships");
    let mess = document.getElementById("message").value;
    let str = req.options[req.selectedIndex].text;
    if (!str) return;
    let user_info = await fetch('api/v1/userinfo/',
        {
            method: 'GET',
            headers: {'Authorization': 'Bearer_' + jwt, 'Accept': 'application/json'}
        });
    let user_info_json = await user_info.json();
    jwt = localStorage.getItem("jwt");
    console.log(jwt);
    let response = await fetch("api/v1/players/makeRequest",
        {
            method: 'POST',
            headers: {
                'Authorization': 'Bearer_' + jwt,
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
            body: JSON.stringify(
                str + ']' + await user_info_json.username + ']' + mess
            )
        });
    let isS = await response;
    if (isS.status === 200)
        alert("Request sended to admin");
    else
        alert("Same request already exist");
}

async function ChangeEmail() {
    let jwt = localStorage.getItem("jwt");
    if (jwt == null) {
        document.location.href = "/login";
    }
    let email = document.getElementById("email").value;
    let user_info = await fetch('api/v1/userinfo/',
        {
            method: 'GET',
            headers: {'Authorization': 'Bearer_' + jwt, 'Accept': 'application/json'}
        });
    let user_info_json = await user_info.json();
    jwt = localStorage.getItem("jwt");
    console.log(jwt);
    let response = await fetch("api/v1/players/changeEmail",
        {
            method: 'POST',
            headers: {
                'Authorization': 'Bearer_' + jwt,
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
            body: JSON.stringify(
                await user_info_json.username + ']' + email
            )
        });
    alert("Email changed on "+email);
}

async function setChampionship() {
    let response = await fetch("api/v1/auth/championships",
        {
            method: 'GET', mode: 'no-cors',
            headers: {'Content-Type': 'application/json', 'Accept': 'application/json'}
        });
    let data = await response.json();
    let select = document.getElementById("championships");
    select.innerHTML = "";
    let i = 1;
    data.forEach(el => {
        select.innerHTML += "<option value=\"Choice " + i + "\">" + el.name + "</option>";
        i++;
    });
}

setChampionship();
logout = () => {
    localStorage.clear();
    document.location.href = "/login";
}




