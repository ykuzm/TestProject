package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/logout/result", method = RequestMethod.GET)
    public String logout(){
        return "passenger_logout_result_page";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("passenger", new Passenger());
        modelAndView.setViewName("passenger_login_page");
        return modelAndView;
    }

    @RequestMapping(value = "/login/result", method = RequestMethod.POST)
    public String loginResult(){
        return "passenger_login_result_page";
    }

    @RequestMapping(value = "/login/error", method = RequestMethod.POST)
    public String loginError(){
        return "passenger_login_error_page";
    }

    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public ModelAndView goToAccount(@AuthenticationPrincipal UserDetails userDetails){
        Passenger passenger = passengerService.getPassengerByLogin(userDetails.getUsername());
        ModelAndView modelAndView = new ModelAndView();
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
    public ModelAndView registerResult(@ModelAttribute("passenger") Passenger passenger){
        ModelAndView modelAndView = new ModelAndView();
        try {
            passengerService.addPassenger(passenger);
            modelAndView.addObject("passenger", passenger);
            modelAndView.setViewName("passenger_register_result_page");
        } catch (Exception e) {
            modelAndView.addObject("exception", e.getMessage());
            modelAndView.setViewName("passenger_register_error_page");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/account/trainpassenger", method = RequestMethod.GET)
    public ModelAndView getPassengerByTrain(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("train", new Train());
        modelAndView.setViewName("train_passenger_page");
        return modelAndView;
    }

    @RequestMapping(value = "/account/trainpassenger/result", method = RequestMethod.POST)
    public ModelAndView getPassengerByTrainResult(@ModelAttribute("train") Train train){
        ModelAndView modelAndView = new ModelAndView();
        try {
            List<Passenger> passengerList = passengerService.getPassengerByTrainNumber(train.getNumber());
            modelAndView.addObject("passengerList", passengerList);
            modelAndView.addObject("trainNumber", train.getNumber());
            modelAndView.setViewName("train_passenger_result_page");
        } catch (Exception e) {
            modelAndView.addObject("exception", e.getMessage());
            modelAndView.setViewName("train_passenger_error_page");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/account/trainpassenger-{trainNumber}", method = RequestMethod.GET)
    public ModelAndView getPassengerByTrainNumber(@PathVariable("trainNumber") int trainNumber){
        ModelAndView modelAndView = new ModelAndView();
        try {
            List<Passenger> passengerList = passengerService.getPassengerByTrainNumber(trainNumber);
            modelAndView.addObject("passengerList", passengerList);
            modelAndView.addObject("trainNumber", trainNumber);
            modelAndView.setViewName("train_passenger_result_page");
        } catch (Exception e) {
            modelAndView.addObject("exception", e.getMessage());
            modelAndView.setViewName("train_passenger_error_page");
        }
        return modelAndView;
    }
}
