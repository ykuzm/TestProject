// Checking correct station names for train search
// index
// user_train_search

var stationName1 = document.getElementById("stationname1");
var stationNameRegex = new RegExp("^[^a-zA-Z].*|.+[^a-zA-Z0-9\\-\\s].*|.*[(\\s|\\-)]{2,}.*");

function checkStation1Name() {
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

function checkStation2Name() {
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

var departureDate = document.getElementById("departuredate");

function checkDepatureDate() {
    if (departureDate.value == "") {
        if (departureDate.classList.contains("correct")) {
            departureDate.classList.remove("correct");
            turnOnButton();
        }
    }
    else {
        if (!departureDate.classList.contains("correct")) {
            departureDate.classList.add("correct");
            turnOnButton();
        }
    }
}

var trainsearchbutton = document.getElementById("button");

function turnOnButton() {
    if (stationName1.classList.contains("correct") && stationName2.classList.contains("correct") &&
        departureDate.classList.contains("correct")) {
        trainsearchbutton.disabled = false;
    }
    else {
        trainsearchbutton.disabled = true;
    }
}