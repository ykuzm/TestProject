package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import system.model.Passenger;
import system.service.PassengerService;

import java.util.regex.Pattern;

@Controller
@RequestMapping("/userssystem")
public class PassengerController {

    private PassengerService passengerService;

    public PassengerService getPassengerService() {
        return passengerService;
    }

    public void setPassengerService(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
//    @ResponseBody
//    public String getAllPassengers(){
//        return this.passengerService.getAllPassengers().toString();
//    }
    public String listBooks(Model model){
        model.addAttribute("passenger", new Passenger());
        model.addAttribute("listBooks", this.passengerService.getAllPassengers());

        return "passengers";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("logpassenger", new Passenger());
        modelAndView.setViewName("passenger_login_page");
        return modelAndView;
    }

    @RequestMapping(value = "/login/result", method = RequestMethod.POST)
    public ModelAndView checkPassengers(@ModelAttribute("logpassenger") Passenger passenger){
        ModelAndView modelAndView = new ModelAndView();
        for (Passenger passenger1: passengerService.getAllPassengers()) {
            if (passenger.getLogin().equals(passenger1.getLogin()) &&
                    passenger.getPassword().equals(passenger1.getPassword())) {
                modelAndView.addObject("passenger", passenger1);
                modelAndView.setViewName("passenger_welcome_page");
                return modelAndView;
            }
        }
        modelAndView.setViewName("passenger_incorrect_login_page");
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView addPassenger(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("regpassenger", new Passenger());
        modelAndView.setViewName("passenger_register_page");
        return modelAndView;
    }

    @RequestMapping(value = "/register/result", method = RequestMethod.POST)
    public ModelAndView addUser(@ModelAttribute("regpassenger") Passenger passenger){
        ModelAndView modelAndView = new ModelAndView();
        if (!isPassengerDataValid(passenger)) {
            modelAndView.setViewName("passenger_incorrect_register_page");
            return modelAndView;
        }
        for (Passenger passenger1: passengerService.getAllPassengers()) {
            if (passenger1.getLogin().equals(passenger.getLogin())) {
                modelAndView.setViewName("passenger_already_exists");
                return modelAndView;
            }
        }
        passengerService.addPassenger(passenger);
        modelAndView.addObject("passenger", passenger);
        modelAndView.setViewName("passenger_welcome_page");
        return modelAndView;
    }

    public boolean isPassengerDataValid(Passenger passenger) {
        String loginRegex = ".*[^a-zA-Z0-9].*";
        String nameRegex = "^[^A-Z].*|.*[^a-zA-Z].*";
        if (passenger.getLogin().equals("") || passenger.getFirstName().equals("") || passenger.getSecondName().equals("")
                || passenger.getYear() == 0 || passenger.getMonth() == 0 ||
                passenger.getDay() == 0 || passenger.getPassword().equals("")) {
            return false;
        }
        if (Pattern.matches(loginRegex, passenger.getLogin())) { return false; }
        if (Pattern.matches(loginRegex, passenger.getPassword())) { return false; }
        if (Pattern.matches(nameRegex, passenger.getFirstName())) { return false; }
        if (Pattern.matches(nameRegex, passenger.getSecondName())) { return false; }
        if (passenger.getYear() > 2000 || passenger.getYear() < 1900) { return false; }
        if (passenger.getMonth() < 1 || passenger.getMonth() > 12) { return false; }
        if (passenger.getDay() < 1 || passenger.getDay() > 31) { return false; }
        if (passenger.getMonth() == 3 && passenger.getDay() > 29) {return false; }
        if (passenger.getMonth() == 3 && passenger.getDay() == 29 && passenger.getYear() % 4 != 0) {return false; }
        return true;
    }

}
