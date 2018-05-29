package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import system.dao.PassengerDao;
import system.model.Passenger;
import system.model.Train;
import system.service.PassengerService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.regex.Pattern;

@Controller
@SessionAttributes(value="passenger")
@RequestMapping(value = "/railway", method = RequestMethod.GET)
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    public PassengerService getPassengerService() {
        return passengerService;
    }

    public void setPassengerService(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public String getAllPassengers(){
        return this.passengerService.getAllPassengers().toString();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView passengerInit(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("passenger", new Passenger());
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("passenger", new Passenger());
        modelAndView.setViewName("passenger_login_page");
        return modelAndView;
    }

    @RequestMapping(value = "/login/result", method = RequestMethod.POST)
    public ModelAndView loginResult(@ModelAttribute("passenger") Passenger passenger, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        if(passenger.getLogin() == null) {
            modelAndView.setViewName("error_not_logged_page");
            return modelAndView;
        }
        try {
            Passenger passenger1 = passengerService.login(passenger);
            modelAndView.addObject("passenger", passenger1);
            modelAndView.setViewName("passenger_login_result_page");
            request.getSession().setAttribute("passenger", passenger1);
        } catch (Exception e) {
            modelAndView.addObject("exception", e.getMessage());
            modelAndView.setViewName("passenger_login_error_page");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public ModelAndView goToAccount(@ModelAttribute("passenger") Passenger passenger){
        ModelAndView modelAndView = new ModelAndView();
        if(passenger.getLogin() == null) {
            modelAndView.setViewName("error_not_logged_page");
            return modelAndView;
        }
        modelAndView.addObject("passenger", passenger);
        if (passenger.isAdmin()) {
            modelAndView.setViewName("account_admin_page");
        }
        else {
            modelAndView.setViewName("account_passenger_page");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("passenger", new Passenger());
        modelAndView.setViewName("passenger_register_page");
        return modelAndView;
    }

    @RequestMapping(value = "/register/result", method = RequestMethod.POST)
    public ModelAndView registerResult(@ModelAttribute("passenger") Passenger passenger, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        if(passenger.getLogin() == null) {
            modelAndView.setViewName("error_not_logged_page");
            return modelAndView;
        }
        try {
            passengerService.addPassenger(passenger);
            modelAndView.addObject("passenger", passenger);
            modelAndView.setViewName("passenger_register_result_page");
            request.getSession().setAttribute("passenger", passenger);
        } catch (Exception e) {
            modelAndView.addObject("exception", e.getMessage());
            modelAndView.setViewName("passenger_register_error_page");
            return modelAndView;
        }
        return modelAndView;
    }

    @RequestMapping(value = "/account/trainpassenger", method = RequestMethod.GET)
    public ModelAndView getPassengerByTrain(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        Passenger passenger = (Passenger) request.getSession().getAttribute("passenger");
        if (passenger.getLogin() == null) {
            modelAndView.setViewName("error_not_logged_page");
            return modelAndView;
        }
        if (!passenger.isAdmin()) {
            modelAndView.setViewName("error_not_admin_page");
            return modelAndView;
        }
        modelAndView.addObject("train", new Train());
        modelAndView.setViewName("train_passenger_page");
        return modelAndView;
    }

    @RequestMapping(value = "/account/trainpassenger/result", method = RequestMethod.POST)
    public ModelAndView getPassengerByTrainResult(@ModelAttribute("train") Train train,
                                                  HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        Passenger passenger = (Passenger) request.getSession().getAttribute("passenger");
        if (passenger.getLogin() == null) {
            modelAndView.setViewName("error_not_logged_page");
            return modelAndView;
        }
        if (!passenger.isAdmin()) {
            modelAndView.setViewName("error_not_admin_page");
            return modelAndView;
        }
        try {
            List<Passenger> passengerList = passengerService.getPassengerByTrainNumber(train.getNumber());
            modelAndView.addObject("passengerList", passengerList);
            modelAndView.addObject("trainNumber", train.getNumber());
            modelAndView.setViewName("train_passenger_result_page");
        } catch (Exception e) {
            modelAndView.addObject("exception", e.getMessage());
            modelAndView.setViewName("train_passenger_error_page");
            return modelAndView;
        }
        return modelAndView;
    }

    @RequestMapping(value = "/account/trainpassenger-{trainNumber}", method = RequestMethod.GET)
    public ModelAndView getPassengerByTrainNumber(@PathVariable("trainNumber") int trainNumber,
                                                  HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        Passenger passenger = (Passenger) request.getSession().getAttribute("passenger");
        if (passenger.getLogin() == null) {
            modelAndView.setViewName("error_not_logged_page");
            return modelAndView;
        }
        if (!passenger.isAdmin()) {
            modelAndView.setViewName("error_not_admin_page");
            return modelAndView;
        }
        try {
            List<Passenger> passengerList = passengerService.getPassengerByTrainNumber(trainNumber);
            modelAndView.addObject("passengerList", passengerList);
            modelAndView.addObject("trainNumber", trainNumber);
            modelAndView.setViewName("train_passenger_result_page");
        } catch (Exception e) {
            modelAndView.addObject("exception", e.getMessage());
            modelAndView.setViewName("train_passenger_error_page");
            return modelAndView;
        }
        return modelAndView;
    }
}
