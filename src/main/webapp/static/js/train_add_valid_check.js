// Checking input for train_add_page
// admin_train_add

var trainNumber = document.getElementById("trainnumber");
var trainNumberRegex = new RegExp("^[^1-9].*|.*[^0-9].*");

function checkTrainNumber() {
    if (trainNumberRegex.test(trainNumber.value) || trainNumber.value == "" || trainNumber.value.length > 3) {
        if (trainNumber.classList.contains("correct")) {
            trainNumber.classList.remove("correct");
            turnOnButton();
        }
    }
    else {
        if (!trainNumber.classList.contains("correct")) {
            trainNumber.classList.add("correct");
            turnOnButton();
        }
    }
}

var trainCapacity = document.getElementById("capacity");

function checkTrainCapacity() {
    if (trainNumberRegex.test(trainCapacity.value) || trainCapacity.value == "" || trainCapacity.value.length > 3) {
        if (trainCapacity.classList.contains("correct")) {
            trainCapacity.classList.remove("correct");
            turnOnButton();
        }
    }
    else {
        if (!trainCapacity.classList.contains("correct")) {
            trainCapacity.classList.add("correct");
            turnOnButton();
        }
    }
}

var trainVelocity = document.getElementById("velocity");
var trainVelocityRegex = new RegExp("^[0-9][.][0-9]+$|^[1-9][0-9]*$");

function checkTrainVelocity() {
    if (!trainVelocityRegex.test(trainVelocity.value) || trainVelocity.value == "" || trainVelocity.value > 5.0) {
        if (trainVelocity.classList.contains("correct")) {
            trainVelocity.classList.remove("correct");
            turnOnButton();
        }
    }
    else {
        if (!trainVelocity.classList.contains("correct")) {
            trainVelocity.classList.add("correct");
            turnOnButton();
        }
    }
}

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

var button = document.getElementById("button");

function turnOnButton() {
    if (trainNumber.classList.contains("correct") && trainCapacity.classList.contains("correct") &&
        trainVelocity.classList.contains("correct") && stationName1.classList.contains("correct") &&
        stationName2.classList.contains("correct") && departureDate.classList.contains("correct")) {
        button.disabled = false;
    }
    else {
        button.disabled = true;
    }
}