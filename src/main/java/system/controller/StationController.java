package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import system.dao.PassengerDao;
import system.model.Passenger;
import system.model.Station;
import system.model.Train;
import system.service.PassengerService;
import system.service.StationService;
import system.service.TrainService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.regex.Pattern;

@Controller
@RequestMapping(value = "/railway", method = RequestMethod.GET)
public class StationController {

    @Autowired
    private StationService stationService;

    public StationService getStationService() {
        return stationService;
    }

    public void setStationService(StationService stationService) {
        this.stationService = stationService;
    }

    @RequestMapping(value = "/liststations", method = RequestMethod.GET)
    @ResponseBody
    public String getAllStations(){
        return stationService.getAllStations().toString();
    }

    @RequestMapping(value = "/account/addstation", method = RequestMethod.GET)
    public ModelAndView addStation(HttpServletRequest request){
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
        modelAndView.addObject("station", new Station());
        modelAndView.setViewName("station_add_page");
        return modelAndView;
    }

    @RequestMapping(value = "/account/addstation/result", method = RequestMethod.POST)
    public ModelAndView addStationResult(@ModelAttribute("station") Station station, HttpServletRequest request){
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
            stationService.addStation(station);
            modelAndView.addObject(station);
            modelAndView.setViewName("station_add_result_page");
        } catch (Exception e) {
            modelAndView.addObject("exception", e.getMessage());
            modelAndView.setViewName("station_add_error_page");
        }
        return modelAndView;
    }

}
