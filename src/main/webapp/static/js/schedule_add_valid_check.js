// Checking schedule input
// admin_schedule_add

var scheduleStationName = document.getElementById("stationname");
var scheduleStationNameRegex = new RegExp("^[^a-zA-Z].*|.+[^a-zA-Z0-9\\-\\s].*|.*[(\\s|\\-)]{2,}.*");

function checkStationName() {
    if (scheduleStationNameRegex.test(scheduleStationName.value) || scheduleStationName.value == "") {
        if (scheduleStationName.classList.contains("correct")) {
            scheduleStationName.classList.remove("correct");
            turnOnButton();
        }
    }
    else {
        if (!scheduleStationName.classList.contains("correct")) {
            scheduleStationName.classList.add("correct");
            turnOnButton();
        }
    }
}

var departureAddDate = document.getElementById("departuredate");

function checkDepatureDate() {
    if (!dateAddRegex.test(departureAddDate.value == "")) {
        if (departureAddDate.classList.contains("correct")) {
            departureAddDate.classList.remove("correct");
            turnOnButton();
        }
    }
    else {
        if (!departureAddDate.classList.contains("correct")) {
            departureAddDate.classList.add("correct");
            turnOnButton();
        }
    }
}

var addScheduleButton = document.getElementById("button");

function turnOnButton() {
    if (scheduleStationName.classList.contains("correct") && departureAddDate.classList.contains("correct")) {
        addScheduleButton.disabled = false;
    }
    else {
        addScheduleButton.disabled = true;
    }
}