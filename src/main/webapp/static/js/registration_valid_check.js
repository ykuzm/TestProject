// Checking registration input
// register_page

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

var fname = document.getElementById("firstname");
var nameRegex = new RegExp("^[^A-Z].*|.+[^a-z].*");

function checkFName() {
    if (nameRegex.test(fname.value) || fname.value == "") {
        if (fname.classList.contains("correct")) {
            fname.classList.remove("correct");
            turnOnButton();
        }
    }
    else {
        if (!fname.classList.contains("correct")) {
            fname.classList.add("correct");
            turnOnButton();
        }
    }
}

var sname = document.getElementById("secondname");

function checkSName() {
    if (nameRegex.test(sname.value) || sname.value == "") {
        if (sname.classList.contains("correct")) {
            sname.classList.remove("correct");
            turnOnButton();
        }
    }
    else {
        if (!sname.classList.contains("correct")) {
            sname.classList.add("correct");
            turnOnButton();
        }
    }
}

// var dateRegex = new RegExp("^(([0-2][1-9]|3[01])-(January|March|May|July|August|October|December)|" +
//     "([0-2][1-9]|30)-(April|June|September|November)|([0-2][1-9])-(February))-(19[0-9][0-9]|20(0[0-9]|1[0-8]))$");
var birthDate = document.getElementById("birthdate");

function checkBirthDate() {
    if (birthDate.value == "") {
        if (birthDate.classList.contains("correct")) {
            birthDate.classList.remove("correct");
            turnOnButton();
        }
    }
    else {
        if (!birthDate.classList.contains("correct")) {
            birthDate.classList.add("correct");
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

var passwordConfirm = document.getElementById("passwordconfirm");

function checkPasswordConfirm() {
    if (loginRegex.test(passwordConfirm.value) || passwordConfirm.value == "" ||
        passwordConfirm.value.length < 4 || passwordConfirm.value != password.value) {
        if (passwordConfirm.classList.contains("correct")) {
            passwordConfirm.classList.remove("correct");
            turnOnButton();
        }
    }
    else {
        if (!passwordConfirm.classList.contains("correct")) {
            passwordConfirm.classList.add("correct");
            turnOnButton();
        }
    }
}

var regbutton = document.getElementById("button");

function turnOnButton() {
    if (login.classList.contains("correct") && fname.classList.contains("correct")
        && sname.classList.contains("correct") && birthDate.classList.contains("correct")
        && password.classList.contains("correct") && passwordConfirm.classList.contains("correct")) {
        regbutton.disabled = false;
    }
    else {
        regbutton.disabled = true;
    }
}

