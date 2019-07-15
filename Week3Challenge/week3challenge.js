window.onload = function(){
    document.getElementById("starship").onclick = function(){
        sendAjaxGet("https://swapi.co/api/starships/9/?format=json",showStarship);
    }
}

function myFunc(){
    let starshipID=document.getElementById("starhispID").value; 
    sendAjaxGet("https://swapi.co/api/starships/"+starshipID+"/?format=json",showStarshipByID);
}

//perform Ajax call
// url represents the resource being requested
// func represents the callback function to be invoked when request is complete
function sendAjaxGet(url, func){
    //step 1: obtain xhr object (IE 5, 6 don't have it...)
    let xhr = new XMLHttpRequest() || new ActionXObject("Microsoft.HTTPRequest");
    //step 2: define onreadystatechange
    xhr.onreadystatechange = function(){
        // readyState of 4 means request is complete
        // status of 200 means ok
        if (this.readyState == 4 && this.status == 200){
            func(this);
        }
    }
    // step 3: prepare the request 
    xhr.open("GET", url, true); 
    // step 4: send the request
    xhr.send(); 
    //IF WERE SENDING A POST REQUEST OR ANYTHING THAT USED THE BODY
    // IT WOULD GO AS AN ARGUMENT TO SEND()
}

function showStarship(xhr){
    let starshipObj = JSON.parse(xhr.responseText);
    console.log(starshipObj);
    document.getElementById("name2").innerText = "Starship Name: "+starshipObj.name;
    document.getElementById("model2").innerText = "Starship Model: "+starshipObj.model;
    document.getElementById("manufacturer2").innerText = "Starship Manufacturer: "+starshipObj.manufacturer;
}

function showStarshipByID(xhr){
    let starshipObj = JSON.parse(xhr.responseText);
    console.log(starshipObj);
    document.getElementById("name1").innerText = "Starship Name: "+starshipObj.name;
    document.getElementById("model1").innerText = "Starship Model: "+starshipObj.model;
    document.getElementById("manufacturer1").innerText = "Starship Manufacturer: "+starshipObj.manufacturer;
}