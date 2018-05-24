package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import system.additional.TrainSearch;
import system.dao.PassengerDao;
import system.model.*;
import system.service.PassengerService;
import system.service.TicketService;
import system.service.TrainService;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Controller
@RequestMapping(value = "/railway", method = RequestMethod.GET)
public class TrainController {

    @Autowired
    private TrainService trainService;

    public TrainService getTrainService() {
        return trainService;
    }

    public void setTrainService(TrainService trainService) {
        this.trainService = trainService;
    }

    @RequestMapping(value = "/account/purchasedtickets", method = RequestMethod.GET)
    public ModelAndView passengerTrainList(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        Passenger passenger = (Passenger) request.getSession().getAttribute("passenger");
        if (passenger.getLogin() == null) {
            modelAndView.setViewName("error_not_logged_page");
            return modelAndView;
        }
        List<Train> trainList = trainService.getTrainByPassengerId(passenger.getId());
        modelAndView.addObject(trainList);
        modelAndView.setViewName("ticket_list_page");
        return modelAndView;
    }

    @RequestMapping(value = "/account/station", method = RequestMethod.GET)
    public ModelAndView stationInfo(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        Passenger passenger = (Passenger) request.getSession().getAttribute("passenger");
        if (passenger.getLogin() == null) {
            modelAndView.setViewName("error_not_logged_page");
            return modelAndView;
        }
        modelAndView.addObject("station", new Station());
        modelAndView.setViewName("station_info_page");
        return modelAndView;
    }

    @RequestMapping(value = "/account/station/info", method = RequestMethod.POST)
    public ModelAndView stationInfoResult(@ModelAttribute("station") Station station1, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        Passenger passenger = (Passenger) request.getSession().getAttribute("passenger");
        if (passenger.getLogin() == null) {
            modelAndView.setViewName("error_not_logged_page");
            return modelAndView;
        }
        try {
            Map<Integer,String> trainMap = trainService.getTrainsByStation(station1);
            modelAndView.addObject("station", station1);
            modelAndView.addObject("trainMap", trainMap);
            modelAndView.setViewName("station_info_result_page");
        } catch (Exception e) {
            modelAndView.addObject("exception", e.getMessage());
            modelAndView.setViewName("station_info_error_page");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/account/trainsearch", method = RequestMethod.GET)
    public ModelAndView scheduleInfo(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        Passenger passenger = (Passenger) request.getSession().getAttribute("passenger");
        if (passenger.getLogin() == null) {
            modelAndView.setViewName("error_not_logged_page");
            return modelAndView;
        }
        modelAndView.addObject("trainSearch", new TrainSearch());
        modelAndView.setViewName("schedule_info_page");
        return modelAndView;
    }

    @RequestMapping(value = "/account/trainsearch/info", method = RequestMethod.POST)
    public ModelAndView scheduleInfoResult(@ModelAttribute("trainSearch") TrainSearch trainSearch,
                                           HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        Passenger passenger = (Passenger) request.getSession().getAttribute("passenger");
        if (passenger.getLogin() == null) {
            modelAndView.setViewName("error_not_logged_page");
            return modelAndView;
        }
        try {
            Map<Integer, String> trainMap = trainService.searchTrains(trainSearch);
            modelAndView.addObject("trainMap", trainMap);
            modelAndView.addObject("station1", trainSearch.getStation1());
            modelAndView.addObject("station2", trainSearch.getStation2());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            modelAndView.addObject("date", sdf.format(trainSearch.getDate()));
            modelAndView.setViewName("schedule_info_result_page");
        } catch (Exception e) {
            modelAndView.addObject("exception", e.getMessage());
            modelAndView.setViewName("station_info_error_page");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/account/addtrain", method = RequestMethod.GET)
    public ModelAndView addTrain(HttpServletRequest request){
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
        modelAndView.setViewName("train_add_page");
        return modelAndView;
    }

    @RequestMapping(value = "/account/addtrain/result", method = RequestMethod.POST)
    public ModelAndView addTrainResult(@ModelAttribute("train") Train train, HttpServletRequest request){
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
            trainService.addTrain(train);
            modelAndView.addObject(train);
            modelAndView.setViewName("train_add_result_page");
        } catch (Exception e) {
            modelAndView.addObject("exception", e.getMessage());
            modelAndView.setViewName("train_add_error_page");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/account/viewalltrains", method = RequestMethod.GET)
    public ModelAndView viewAllTrains(HttpServletRequest request){
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
        List<Train> trainList = trainService.getAllTrains();
        modelAndView.addObject("trainList", trainList);
        modelAndView.setViewName("train_list_page");
        return modelAndView;
    }

}