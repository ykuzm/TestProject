// Checking correct login input
// login_page

var login = document.getElementById("login");
var loginRegex = new RegExp(".*[^a-zA-Z0-9].*");

function checkLogin() {
    if (loginRegex.test(login.value) || login.value == "" || login.value.length < 4) {
        if (login.classList.contains("correct")) {
            login.classList.remove("correct");
            turnOnButton();
        }
    }
    else {
        if (!login.classList.contains("correct")) {
            login.classList.add("correct");
            turnOnButton();
        }
    }
}

var password = document.getElementById("password");

function checkPassword() {
    if (loginRegex.test(password.value) || password.value == "" || password.value.length < 4) {
        if (password.classList.contains("correct")) {
            password.classList.remove("correct");
            turnOnButton();
        }
    }
    else {
        if (!password.classList.contains("correct")) {
            password.classList.add("correct");
            turnOnButton();
        }
    }
}

var logbutton = document.getElementById("button");

function turnOnButton() {
    if (login.classList.contains("correct") && password.classList.contains("correct")) {
        logbutton.disabled = false;
    }
    else {
        logbutton.disabled = true;
    }
}