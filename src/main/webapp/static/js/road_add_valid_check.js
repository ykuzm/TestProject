// Checking correct road data input
// admin_road_add

var stationName1 = document.getElementById("stationname1");
var stationNameRegex = new RegExp("^[^a-zA-Z].*|.+[^a-zA-Z0-9\\-\\s].*|.*[(\\s|\\-)]{2,}.*");

function checkStationName1() {
    if (stationNameRegex.test(stationName1.value) || stationName1.value == "") {
        if (stationName1.classList.contains("correct")) {
            stationName1.classList.remove("correct");
            turnOnButton();
        }
    }
    else {
        if (!stationName1.classList.contains("correct")) {
            stationName1.classList.add("correct");
            turnOnButton();
        }
    }
}

var stationName2 = document.getElementById("stationname2");

function checkStationName2() {
    if (stationNameRegex.test(stationName2.value) || stationName2.value == "") {
        if (stationName2.classList.contains("correct")) {
            stationName2.classList.remove("correct");
            turnOnButton();
        }
    }
    else {
        if (!stationName2.classList.contains("correct")) {
            stationName2.classList.add("correct");
            turnOnButton();
        }
    }
}

var button = document.getElementById("button");

function turnOnButton() {
    if (stationName1.classList.contains("correct") && stationName2.classList.contains("correct")) {
        button.disabled = false;
    }
    else {
        button.disabled = true;
    }
}