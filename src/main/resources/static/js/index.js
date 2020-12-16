load = async () => {
    let jwt = localStorage.getItem("jwt");
    if(jwt== null){
        document.location.href = "/login";
    }
    let user_info = await fetch('api/v1/userinfo/',
        {
            method: 'GET',
            headers: {'Authorization': 'Bearer_' + jwt, 'Accept': 'application/json'}
        });
    let user_info_json = await user_info.json();
    let length = Object.keys(user_info_json).length;
    document.getElementById("username").innerHTML = user_info_json.username;

    let ulk = document.getElementById("keys");
    let ulv = document.getElementById("values");

    ulk.innerHTML += "<li>username</li>"

    ulv.innerHTML += "<li class=\"inf_li\">" + user_info_json.username + "</li>";

    //admin
    if(length == 3)
    {
        ulk.innerHTML += "<li>Email</li>"

        ulv.innerHTML += "<li class=\"inf_li\">" + user_info_json.email + "</li>";
        let ulk2 = document.getElementById("buttt");
        ulk2.innerHTML += "<button type=\"button\" id=\"but\" class=\"btn btn-secondary btn-lg btn-block\" onclick='onPlayerPage()' style=\"margin-left: 35px; margin-bottom: 20px; height: 50px; width:280px\"></button>";
        document.getElementById("but").innerHTML = "TO CLIENT PAGE";
    }else
    {
        let ulk2 = document.getElementById("buttt");
        ulk2.innerHTML += "<button type=\"button\" id=\"but\" class=\"btn btn-secondary btn-lg btn-block\" onclick='onAdminPage()' style=\"margin-left: 35px; margin-bottom: 20px; height: 50px; width:280px\"></button>";
        document.getElementById("user_info").style.marginTop = "90px";
        document.getElementById("spisok").style.marginTop = "50px";
        document.getElementById("but").innerHTML = "TO ADMIN PANEL";
    }

}

onAdminPage = () => {
    document.location.href = "/register";
}

onPlayerPage = () => {
    document.location.href = "/player";
}

logout = () => {
    localStorage.clear();
    document.location.href = "/login";
}
load();