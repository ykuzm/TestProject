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

    @RequestMapping(value = "/admin/addstation", method = RequestMethod.GET)
    public ModelAndView addStation(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("station", new Station());
        modelAndView.setViewName("station_add_page");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/addstation/result", method = RequestMethod.POST)
    public ModelAndView addStationResult(@ModelAttribute("station") Station station){
        ModelAndView modelAndView = new ModelAndView();
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
