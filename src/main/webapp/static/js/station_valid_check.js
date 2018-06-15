// Checking correct station name input
// user_station_info
// admin_station_add
// user_station_info
// admin_schedule_add

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

var stationinfobutton = document.getElementById("button");

function turnOnButton() {
    if (stationName.classList.contains("correct")) {
        stationinfobutton.disabled = false;
    }
    else {
        stationinfobutton.disabled = true;
    }
}