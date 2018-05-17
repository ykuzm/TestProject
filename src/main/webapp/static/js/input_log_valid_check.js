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
