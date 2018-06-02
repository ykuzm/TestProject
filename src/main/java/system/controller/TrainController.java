package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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

    @Autowired
    private PassengerService passengerService;

    public TrainService getTrainService() {
        return trainService;
    }

    public void setTrainService(TrainService trainService) {
        this.trainService = trainService;
    }

    public PassengerService getPassengerService() {
        return passengerService;
    }

    public void setPassengerService(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @RequestMapping(value = "/passenger/purchasedtickets", method = RequestMethod.GET)
    public ModelAndView passengerTrainList(@AuthenticationPrincipal UserDetails userDetails,
                                           @ModelAttribute String role){
        Passenger passenger = passengerService.getPassengerByLogin(userDetails.getUsername());
        ModelAndView modelAndView = new ModelAndView();
        List<Train> trainList = trainService.getTrainByPassengerId(passenger.getId());
        modelAndView.addObject("trainList", trainList);
        modelAndView.setViewName("ticket_list_page");
        return modelAndView;
    }

    @RequestMapping(value = "/passenger/station", method = RequestMethod.GET)
    public ModelAndView stationInfo(@ModelAttribute String role){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("station", new Station());
        modelAndView.setViewName("station_info_page");
        return modelAndView;
    }

    @RequestMapping(value = "/passenger/station/info", method = RequestMethod.POST)
    public ModelAndView stationInfoResult(@ModelAttribute("station") Station station,
                                          @ModelAttribute String role){
        ModelAndView modelAndView = new ModelAndView();
        try {
            Map<Integer,String> trainMap = trainService.getTrainsByStation(station);
            modelAndView.addObject("station", station);
            modelAndView.addObject("trainMap", trainMap);
            modelAndView.setViewName("station_info_result_page");
        } catch (Exception e) {
            modelAndView.addObject("exception", e.getMessage());
            modelAndView.setViewName("station_info_error_page");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/passenger/trainsearch", method = RequestMethod.GET)
    public ModelAndView scheduleInfo(@ModelAttribute String role){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("trainSearch", new TrainSearch());
        modelAndView.setViewName("schedule_info_page");
        return modelAndView;
    }

    @RequestMapping(value = "/passenger/trainsearch/info", method = RequestMethod.POST)
    public ModelAndView scheduleInfoResult(@ModelAttribute("trainSearch") TrainSearch trainSearch,
                                           @ModelAttribute String role){
        ModelAndView modelAndView = new ModelAndView();
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

    @RequestMapping(value = "/admin/addtrain", method = RequestMethod.GET)
    public ModelAndView addTrain(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("train", new Train());
        modelAndView.setViewName("train_add_page");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/addtrain/result", method = RequestMethod.POST)
    public ModelAndView addTrainResult(@ModelAttribute("train") Train train){
        ModelAndView modelAndView = new ModelAndView();
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

    @RequestMapping(value = "/admin/viewalltrains", method = RequestMethod.GET)
    public ModelAndView viewAllTrains(){
        List<Train> trainList = trainService.getAllTrains();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("trainList", trainList);
        modelAndView.setViewName("train_list_page");
        return modelAndView;
    }

}