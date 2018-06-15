package system.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import system.additional.TrainSearch;
import system.model.Station;
import system.model.Train;
import system.model.User;
import system.service.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Controller
@SessionAttributes(value="role")
@RequestMapping(value = "/railway", method = RequestMethod.GET)
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    UserService userService;

    @Autowired
    TicketService ticketService;

    @Autowired
    TrainService trainService;

    @Autowired
    StationService stationService;

    public ScheduleService getScheduleService() {
        return scheduleService;
    }

    public void setScheduleService(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public TicketService getTicketService() {
        return ticketService;
    }

    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public TrainService getTrainService() {
        return trainService;
    }

    public StationService getStationService() {
        return stationService;
    }

    public void setStationService(StationService stationService) {
        this.stationService = stationService;
    }

    public void setTrainService(TrainService trainService) {
        this.trainService = trainService;
    }

    /**
     * Returns view of train search form
     *
     * @return train_search_page
     */
    @RequestMapping(value = "/user/trainsearch", method = RequestMethod.GET)
    public ModelAndView trainSearch(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("trainSearch", new TrainSearch());
        modelAndView.setViewName("user_train_search");
        return modelAndView;
    }

    /**
     * Returns table of trains
     *
     * @param trainSearch trainSearch
     * @return view of train search result
     */
    @RequestMapping(value = {"/user/trainsearch/info", "/trainsearch/info"}, method = RequestMethod.POST)
    public ModelAndView trainSearchResult(@ModelAttribute("trainSearch") TrainSearch trainSearch){
        ModelAndView modelAndView = new ModelAndView();
        try {
            Map<Integer, Date> trainMap = trainService.searchTrains(trainSearch);
            modelAndView.addObject("trainMap", trainMap);
            modelAndView.addObject("trainSearch", trainSearch);
            modelAndView.setViewName("user_train_search_result");
        } catch (Exception e) {
            modelAndView.addObject("exception", e.getMessage());
            modelAndView.setViewName("user_data_error");
        }
        return modelAndView;
    }

    /**
     * Returns table with train schedule
     *
     * @param trainNumber train number
     * @return view with train schedule
     */
    @RequestMapping(value = {"/user/trainschedule-{trainNumber}", "/trainschedule-{trainNumber}"},
            method = RequestMethod.GET)
    public ModelAndView trainSchedule(@PathVariable int trainNumber){
        ModelAndView modelAndView = new ModelAndView();
        try {
            Map<String, Date> scheduleMap = scheduleService.getScheduleByTrainNumber(trainNumber);
            modelAndView.addObject("scheduleMap", scheduleMap);
            modelAndView.addObject("trainNumber", trainNumber);
            modelAndView.setViewName("user_train_schedule");
        } catch (Exception e) {
            modelAndView.addObject("exception", e.getMessage());
            modelAndView.setViewName("user_data_error");
        }
        return modelAndView;
    }

    /**
     * Returns station info form
     *
     * @return station_info_page
     */
    @RequestMapping(value = "/user/station", method = RequestMethod.GET)
    public ModelAndView stationInfo(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("station", new Station());
        modelAndView.setViewName("user_station_info");
        return modelAndView;
    }

    /**
     * Returns trains going through station
     *
     * @param station station
     * @return trains going through station
     */
    @RequestMapping(value = "/user/station/info", method = RequestMethod.POST)
    public ModelAndView stationInfoResult(@ModelAttribute("station") Station station){
        ModelAndView modelAndView = new ModelAndView();
        try {
            Map<Integer,Date> trainMap = trainService.getTrainsByStation(station);
            modelAndView.addObject("station", station);
            modelAndView.addObject("trainMap", trainMap);
            modelAndView.setViewName("user_station_info_result");
        } catch (Exception e) {
            modelAndView.addObject("exception", e.getMessage());
            modelAndView.setViewName("user_data_error");
        }
        return modelAndView;
    }

    /**
     * Returns ticket buy result
     *
     * @param userDetails user details
     * @param trainNumber train number
     * @return ticket buy result
     */
    @RequestMapping(value = "/user/buyticket+{trainNumber}+{stationName1}+{stationName2}", method = RequestMethod.GET)
    public ModelAndView buyTicket(@AuthenticationPrincipal UserDetails userDetails,
                                  @PathVariable int trainNumber,
                                  @PathVariable String stationName1,
                                  @PathVariable String stationName2){
        User user = userService.getUserByLogin(userDetails.getUsername());
        ModelAndView modelAndView = new ModelAndView();
        try {
            ticketService.addTicket(user.getId(), trainNumber, stationName1, stationName2);
            modelAndView.setViewName("user_ticket_buy");
        } catch (Exception e) {
            modelAndView.addObject("exception", e.getMessage());
            modelAndView.setViewName("user_data_error");
        }
        return modelAndView;
    }

    /**
     * Returns table of trains, which user purchased tickets on
     *
     * @param userDetails user details
     * @return table of trains, which user purchased tickets on
     */
    @RequestMapping(value = "/user/purchasedtickets", method = RequestMethod.GET)
    public ModelAndView purchasedTickets(@AuthenticationPrincipal UserDetails userDetails){
        User user = userService.getUserByLogin(userDetails.getUsername());
        ModelAndView modelAndView = new ModelAndView();
        List<Train> trainList = trainService.getTrainByUserId(user.getId());
        modelAndView.addObject("trainList", trainList);
        modelAndView.setViewName("user_ticket_list");
        return modelAndView;
    }

}
