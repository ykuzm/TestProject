// Checking correct login information
var login = document.getElementById("loglogininput");
var loginRegex = new RegExp(".*[^a-zA-Z0-9].*");

function checkLogin() {
    if (loginRegex.test(login.value) || login.value == "" || login.value.length < 4) {
        if (login.classList.contains("correct")) {
            login.classList.remove("correct");
            turnOnLogButton();
        }
    }
    else {
        if (!login.classList.contains("correct")) {
            login.classList.add("correct");
            turnOnLogButton();
        }
    }
}

var password = document.getElementById("logpasswordinput");

function checkPassword() {
    if (loginRegex.test(password.value) || password.value == "" || password.value.length < 4) {
        if (password.classList.contains("correct")) {
            password.classList.remove("correct");
            turnOnLogButton();
        }
    }
    else {
        if (!password.classList.contains("correct")) {
            password.classList.add("correct");
            turnOnLogButton();
        }
    }
}

var logbutton = document.getElementById("logbutton");

function turnOnLogButton() {
    if (login.classList.contains("correct") && password.classList.contains("correct")) {
        logbutton.disabled = false;
    }
    else {
        logbutton.disabled = true;
    }
}

// Checking correct trainNumber input
var trainNumber = document.getElementById("trainnumberinput");
var trainNumberRegex = new RegExp("^[^1-9].*|.*[^0-9].*");

function checkTrainNumber() {
    if (trainNumberRegex.test(trainNumber.value) || trainNumber.value == "" || trainNumber.value.length > 3) {
        if (trainNumber.classList.contains("correct")) {
            trainNumber.classList.remove("correct");
            turnOnBuyButton();
        }
    }
    else {
        if (!trainNumber.classList.contains("correct")) {
            trainNumber.classList.add("correct");
            turnOnBuyButton();
        }
    }
}

var ticketbuybutton = document.getElementById("ticketbuybutton");

function turnOnBuyButton() {
    if (trainNumber.classList.contains("correct")) {
        ticketbuybutton.disabled = false;
    }
    else {
        ticketbuybutton.disabled = true;
    }
}

// Checking correct station name input
var stationName = document.getElementById("stationnameinput");
var stationNameRegex = new RegExp("^[^A-Z].*|.+[^a-zA-Z\-].*");

function checkStationName() {
    if (stationNameRegex.test(stationName.value) || stationName.value == "") {
        if (stationName.classList.contains("correct")) {
            stationName.classList.remove("correct");
            turnOnStationButton();
        }
    }
    else {
        if (!stationName.classList.contains("correct")) {
            stationName.classList.add("correct");
            turnOnStationButton();
        }
    }
}

var stationinfobutton = document.getElementById("stationinfobutton");

function turnOnStationButton() {
    if (stationName.classList.contains("correct")) {
        stationinfobutton.disabled = false;
    }
    else {
        stationinfobutton.disabled = true;
    }
}

// Checking correct station names for train search
var stationName1 = document.getElementById("stationnameinput1");

function checkStation1Name() {
    if (stationNameRegex.test(stationName1.value) || stationName1.value == "") {
        if (stationName1.classList.contains("correct")) {
            stationName1.classList.remove("correct");
            turnOnSearchButton();
        }
    }
    else {
        if (!stationName1.classList.contains("correct")) {
            stationName1.classList.add("correct");
            turnOnSearchButton();
        }
    }
}

var stationName2 = document.getElementById("stationnameinput2");

function checkStation2Name() {
    if (stationNameRegex.test(stationName2.value) || stationName2.value == "") {
        if (stationName2.classList.contains("correct")) {
            stationName2.classList.remove("correct");
            turnOnSearchButton();
        }
    }
    else {
        if (!stationName2.classList.contains("correct")) {
            stationName2.classList.add("correct");
            turnOnSearchButton();
        }
    }
}

var dateRegex = new RegExp("^(([0-2][1-9]|3[01])-(January|March|May|July|August|October|December)|" +
    "([0-2][1-9]|30)-(April|June|September|November)|([0-2][1-9])-(February))-[2][0][1][8]$");
var departureDate = document.getElementById("departuredate");

function checkDepatureDate() {
    if (!dateRegex.test(departureDate.value) || departureDate.value == "") {
        if (departureDate.classList.contains("correct")) {
            departureDate.classList.remove("correct");
            turnOnSearchButton();
        }
    }
    else {
        if (!departureDate.classList.contains("correct")) {
            departureDate.classList.add("correct");
            turnOnSearchButton();
        }
    }
}

var trainsearchbutton = document.getElementById("trainsearchbutton");

function turnOnSearchButton() {
    if (stationName1.classList.contains("correct") && stationName2.classList.contains("correct") &&
        departureDate.classList.contains("correct")) {
        trainsearchbutton.disabled = false;
    }
    else {
        trainsearchbutton.disabled = true;
    }
}

// Checking input for train_add_page
var trainAddNumber = document.getElementById("trainaddnumberinput");
var trainAddNumberRegex = new RegExp("^[^1-9].*|.*[^0-9].*");

function checkTrainAddNumber() {
    if (trainAddNumberRegex.test(trainAddNumber.value) || trainAddNumber.value == "" || trainAddNumber.value.length > 3) {
        if (trainAddNumber.classList.contains("correct")) {
            trainAddNumber.classList.remove("correct");
            turnOnAddTrainButton();
        }
    }
    else {
        if (!trainAddNumber.classList.contains("correct")) {
            trainAddNumber.classList.add("correct");
            turnOnAddTrainButton();
        }
    }
}

var trainAddSeats = document.getElementById("trainaddseatsinput");

function checkTrainAddSeats() {
    if (trainAddNumberRegex.test(trainAddSeats.value) || trainAddSeats.value == "" || trainAddSeats.value.length > 3) {
        if (trainAddSeats.classList.contains("correct")) {
            trainAddSeats.classList.remove("correct");
            turnOnAddTrainButton();
        }
    }
    else {
        if (!trainAddSeats.classList.contains("correct")) {
            trainAddSeats.classList.add("correct");
            turnOnAddTrainButton();
        }
    }
}

var trainAddButton = document.getElementById("addtrainbutton");

function turnOnAddTrainButton() {
    if (trainAddNumber.classList.contains("correct") && trainAddSeats.classList.contains("correct")) {
        trainAddButton.disabled = false;
    }
    else {
        trainAddButton.disabled = true;
    }
}

// Checking for station_add_page
var stationAddName = document.getElementById("stationaddnameinput");
var stationAddNameRegex = new RegExp("^[^A-Z].*|.+[^a-zA-Z\-].*");

function checkAddStationName() {
    if (stationAddNameRegex.test(stationAddName.value) || stationAddName.value == "") {
        if (stationAddName.classList.contains("correct")) {
            stationAddName.classList.remove("correct");
            turnOnAddStationButton();
        }
    }
    else {
        if (!stationAddName.classList.contains("correct")) {
            stationAddName.classList.add("correct");
            turnOnAddStationButton();
        }
    }
}

var stationaddbutton = document.getElementById("stationaddbutton");

function turnOnAddStationButton() {
    if (stationAddName.classList.contains("correct")) {
        stationaddbutton.disabled = false;
    }
    else {
        stationaddbutton.disabled = true;
    }
}

// Checking for schedule_add_page
// Checking correct station names for train search
var scheduleStationName = document.getElementById("stationnameinput3");
var scheduleStationNameRegex = new RegExp("^[^A-Z].*|.+[^a-zA-Z\-].*");

function checkStation3Name() {
    if (scheduleStationNameRegex.test(scheduleStationName.value) || scheduleStationName.value == "") {
        if (scheduleStationName.classList.contains("correct")) {
            scheduleStationName.classList.remove("correct");
            turnOnAddScheduleButton();
        }
    }
    else {
        if (!scheduleStationName.classList.contains("correct")) {
            scheduleStationName.classList.add("correct");
            turnOnAddScheduleButton();
        }
    }
}

var dateAddRegex = new RegExp("^(([0-2][1-9]|3[01])-(January|March|May|July|August|October|December)|" +
    "([0-2][1-9]|30)-(April|June|September|November)|([0-2][1-9])-(February))-[2][0][1][8]$");
var departureAddDate = document.getElementById("departuredate1");

function checkDepatureDate1() {
    if (!dateAddRegex.test(departureAddDate.value) || departureAddDate.value == "") {
        if (departureAddDate.classList.contains("correct")) {
            departureAddDate.classList.remove("correct");
            turnOnAddScheduleButton();
        }
    }
    else {
        if (!departureAddDate.classList.contains("correct")) {
            departureAddDate.classList.add("correct");
            turnOnAddScheduleButton();
        }
    }
}

var addScheduleButton = document.getElementById("scheduleaddbutton");

function turnOnAddScheduleButton() {
    if (scheduleStationName.classList.contains("correct") && departureAddDate.classList.contains("correct")) {
        addScheduleButton.disabled = false;
    }
    else {
        addScheduleButton.disabled = true;
    }
}