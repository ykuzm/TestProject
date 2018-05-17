var login = document.getElementById("reglogininput");
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

var fname = document.getElementById("regfnameinput");
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

var sname = document.getElementById("regsnameinput");

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

var dateRegex = new RegExp("^(([0-2][1-9]|3[01])-(January|March|May|July|August|October|December)|" +
    "([0-2][1-9]|30)-(April|June|September|November)|([0-2][1-9])-(February))-(19[0-9][0-9]|20(0[0-9]|1[0-8]))$");
var birthDate = document.getElementById("regbirthdateinput");

function checkBirthDate() {
    if (!dateRegex.test(birthDate.value) || birthDate.value == "") {
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

var password = document.getElementById("regpasswordinput");

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

var regbutton = document.getElementById("regbutton");

function turnOnButton() {
    if (login.classList.contains("correct") && fname.classList.contains("correct")
        && sname.classList.contains("correct") && birthDate.classList.contains("correct")
        && password.classList.contains("correct")) {
        regbutton.disabled = false;
    }
    else {
        regbutton.disabled = true;
    }
}

