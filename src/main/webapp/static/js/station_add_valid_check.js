// Checking correct station name input
// user_station_info
// admin_station_add

var stationName = document.getElementById("stationname");
var stationNameRegex = new RegExp("^[^a-zA-Z].*|.+[^a-zA-Z0-9\\-\\s].*|.*[(\\s|\\-)]{2,}.*");

function checkStationName() {
    if (stationNameRegex.test(stationName.value) || stationName.value == "") {
        if (stationName.classList.contains("correct")) {
            stationName.classList.remove("correct");
            turnOnButton();
        }
    }
    else {
        if (!stationName.classList.contains("correct")) {
            stationName.classList.add("correct");
            turnOnButton();
        }
    }
}

var coordX = document.getElementById("coordX");
var coordRegex = new RegExp("^[^1-9].*|.*[^0-9].*");

function checkCoordX() {
    if (coordRegex.test(coordX.value) || coordX.value == "" || coordX.value > 50) {
        if (coordX.classList.contains("correct")) {
            coordX.classList.remove("correct");
            turnOnButton();
        }
    }
    else {
        if (!coordX.classList.contains("correct")) {
            coordX.classList.add("correct");
            turnOnButton();
        }
    }
}

var coordY = document.getElementById("coordY");

function checkCoordY() {
    if (coordRegex.test(coordY.value) || coordY.value == "" || coordY.value > 50) {
        if (coordY.classList.contains("correct")) {
            coordY.classList.remove("correct");
            turnOnButton();
        }
    }
    else {
        if (!coordY.classList.contains("correct")) {
            coordY.classList.add("correct");
            turnOnButton();
        }
    }
}

var button = document.getElementById("button");

function turnOnButton() {
    if (stationName.classList.contains("correct") && coordX.classList.contains("correct")
                                                  && coordY.classList.contains("correct")) {
        button.disabled = false;
    }
    else {
        button.disabled = true;
    }
}