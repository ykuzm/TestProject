package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import system.dao.PassengerDao;
import system.model.Passenger;
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
        return this.passengerService.getPassengerByLogin("user2").toString();
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
    public ModelAndView checkLogin(@ModelAttribute("passenger") Passenger passenger){
        ModelAndView modelAndView = new ModelAndView();
        if(passenger.getLogin() == null) {
            modelAndView.setViewName("passenger_need_to_login_page");
            return modelAndView;
        }
        List<Passenger> passengerList = passengerService.getPassengerByLogin(passenger.getLogin());
        if (passengerList.size() != 0 && passengerList.get(0).getPassword().equals(passenger.getPassword())) {
            modelAndView.addObject("passenger", passengerList.get(0));
            modelAndView.setViewName("passenger_correct_login_page");
            return modelAndView;
        }
        modelAndView.setViewName("passenger_incorrect_login_page");
        return modelAndView;
    }

    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public ModelAndView goToAccount(@ModelAttribute("passenger") Passenger passenger, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        if(passenger.getLogin() == null) {
            modelAndView.setViewName("passenger_need_to_login_page");
            return modelAndView;
        }
        modelAndView.addObject("passenger", passenger);
        request.getSession().setAttribute("passenger", passenger);
        if (passenger.isAdmin()) {
            modelAndView.setViewName("passenger_admin_account_page");
        }
        else {
            modelAndView.setViewName("passenger_passenger_account_page");
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
    public ModelAndView checkRegister(@ModelAttribute("passenger") Passenger passenger){
        ModelAndView modelAndView = new ModelAndView();
        if(passenger.getLogin() == null) {
            modelAndView.setViewName("passenger_need_to_login_page");
            return modelAndView;
        }
        List<Passenger> passengerList = passengerService.getPassengerByLogin(passenger.getLogin());
        if (passengerList.size() != 0) {
            modelAndView.setViewName("passenger_already_exists_page");
            return modelAndView;
        }
        passengerService.addPassenger(passenger);
        modelAndView.addObject("passenger", passenger);
        modelAndView.setViewName("passenger_correct_register_page");
        return modelAndView;
    }
}
