package system.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import system.additional.RoadAdd;
import system.additional.TrainAdd;
import system.exceptions.NotFoundInDatabaseException;
import system.model.Road;
import system.model.Station;
import system.model.Train;
import system.model.User;
import system.service.*;

import java.util.List;

@Controller
@SessionAttributes(value="role")
@RequestMapping(value = "/railway", method = RequestMethod.GET)
public class AdminController {

    private static final Logger logger = Logger.getLogger(AdminController.class);

    @Autowired
    UserService userService;

    @Autowired
    StationService stationService;

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    TrainService trainService;

    @Autowired
    RoadService roadService;

    public UserService getUserService() { return userService; }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public StationService getStationService() {
        return stationService;
    }

    public void setStationService(StationService stationService) {
        this.stationService = stationService;
    }

    public ScheduleService getScheduleService() {
        return scheduleService;
    }

    public void setScheduleService(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    public TrainService getTrainService() {
        return trainService;
    }

    public void setTrainService(TrainService trainService) {
        this.trainService = trainService;
    }

    public RoadService getRoadService() {
        return roadService;
    }

    public void setRoadService(RoadService roadService) {
        this.roadService = roadService;
    }

    /**
     * Returns view of personal account according to changed role
     *
     * @param role role
     * @return view of personal account according to changed role
     */
    @RequestMapping(value = "/admin/rolechange", method = RequestMethod.POST)
    public ModelAndView roleChange(@ModelAttribute("role") String role){
        if (role.equals("admin")) {
            role = "user";
        }
        else {
            role = "admin";
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("role", role);
        modelAndView.setViewName("admin_role_change");
        return modelAndView;
    }

    /**
     * Returns view of station add panel
     *
     * @return view of station add panel
     */
    @RequestMapping(value = "/admin/stationadd", method = RequestMethod.GET)
    public ModelAndView addStation(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("station", new Station());
        modelAndView.setViewName("admin_station_add");
        return modelAndView;
    }

    /**
     * Returns the result of adding station
     *
     * @param station station
     * @return the result of adding station
     */
    @RequestMapping(value = "/admin/stationadd/result", method = RequestMethod.POST)
    public ModelAndView addStationResult(@ModelAttribute("station") Station station){
        ModelAndView modelAndView = new ModelAndView();
        try {
            stationService.addStation(station);
            modelAndView.addObject("station", stationService.getStationByName(station.getName()));
            modelAndView.setViewName("admin_station_add_result");
        } catch (Exception e) {
            modelAndView.addObject("exception", e.getMessage());
            modelAndView.setViewName("admin_data_error");
        }
        return modelAndView;
    }

    /**
     * Returns view of road add panel
     *
     * @return view of road add panel
     */
    @RequestMapping(value = "/admin/roadadd", method = RequestMethod.GET)
    public ModelAndView addRoad(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("roadAdd", new RoadAdd());
        modelAndView.setViewName("admin_road_add");
        return modelAndView;
    }

    /**
     * Returns the result of adding road
     *
     * @param roadAdd roadAdd contains two station names
     * @return the result of adding road
     */
    @RequestMapping(value = "/admin/roadadd/result", method = RequestMethod.POST)
    public ModelAndView addRoadResult(@ModelAttribute("roadAdd") RoadAdd roadAdd){
        ModelAndView modelAndView = new ModelAndView();
        try {
            roadService.addRoad(roadAdd);
            modelAndView.addObject("roadAdd", roadAdd);
            modelAndView.setViewName("admin_road_add_result");
        } catch (Exception e) {
            modelAndView.addObject("exception", e.getMessage());
            modelAndView.setViewName("admin_data_error");
        }
        return modelAndView;
    }

    /**
     * Returns view of train add panel
     *
     * @return view of train add panel
     */
    @RequestMapping(value = "/admin/trainadd", method = RequestMethod.GET)
    public ModelAndView addTrain(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("trainAdd", new TrainAdd());
        modelAndView.setViewName("admin_train_add");
        return modelAndView;
    }

    /**
     * Returns the result of adding train
     *
     * @param trainAdd trainAdd
     * @return the result of adding train
     */
    @RequestMapping(value = "/admin/trainadd/result", method = RequestMethod.POST)
    public ModelAndView addTrainResult(@ModelAttribute("trainAdd") TrainAdd trainAdd){
        ModelAndView modelAndView = new ModelAndView();
        try {
            trainService.addTrain(trainAdd);
            modelAndView.addObject("trainAdd", trainAdd);
            modelAndView.setViewName("admin_train_add_result");
        } catch (Exception e) {
            modelAndView.addObject("exception", e.getMessage());
            modelAndView.setViewName("admin_data_error");
        }
        return modelAndView;
    }

    /**
     * Returns the table which contains all trains
     *
     * @return the table which contains all trains
     */
    @RequestMapping(value = "/admin/viewalltrains", method = RequestMethod.GET)
    public ModelAndView viewAllTrains(){
        List<Train> trainList = trainService.getAllTrains();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("trainList", trainList);
        modelAndView.setViewName("admin_train_list");
        return modelAndView;
    }

    /**
     * Returns the table which contains users, registered on this train
     *
     * @param trainNumber train number
     * @return the table which contains users, registered on this train
     */
    @RequestMapping(value = "/admin/trainuser-{trainNumber}", method = RequestMethod.GET)
    public ModelAndView getUserByTrainNumber(@PathVariable("trainNumber") int trainNumber){
        ModelAndView modelAndView = new ModelAndView();
        try {
            List<User> userList = userService.getUserByTrainNumber(trainNumber);
            modelAndView.addObject("userList", userList);
            modelAndView.addObject("trainNumber", trainNumber);
            modelAndView.setViewName("admin_train_user");
        } catch (Exception e) {
            modelAndView.addObject("exception", e.getMessage());
            modelAndView.setViewName("admin_data_error");
        }
        return modelAndView;
    }

    /**
     * Returns the view of schedule add panel
     *
     * @param trainNumber train number
     * @return the view of schedule add panel
     */
    @RequestMapping(value = "/admin/addschedule-{trainNumber}", method = RequestMethod.GET)
    public ModelAndView addScedule(@PathVariable int trainNumber){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("trainNumber", trainNumber);
        modelAndView.addObject("station", new Station());
        modelAndView.setViewName("admin_schedule_add");
        return modelAndView;
    }

    /**
     * Returns the result of schedule add
     *
     * @param trainNumber train number
     * @return the result of schedule add
     */
    @RequestMapping(value = "/admin/addschedule/result-{trainNumber}", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView addSceduleResult(@PathVariable int trainNumber,
                                         @ModelAttribute("station") Station station){
        ModelAndView modelAndView = new ModelAndView();
        try {
            Station station1 = stationService.getStationByName(station.getName());
            Train train = trainService.getTrainByNumber(trainNumber);
            scheduleService.addAdditionalSchedule(train, station1.getId());
            modelAndView.addObject("trainNumber", trainNumber);
            modelAndView.setViewName("admin_schedule_add_result");
        } catch (Exception e) {
            modelAndView.addObject("exception", e.getMessage());
            modelAndView.setViewName("admin_data_error");
        }
        return modelAndView;
    }

    /**
     * Returns view of full road scheme
     *
     * @return view of full road scheme
     */
    @RequestMapping(value = "admin/viewroadscheme", method = {RequestMethod.GET})
    public ModelAndView viewTrainScheme() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("stations", stationService.getAllStations());
        modelAndView.addObject("leftCoordX", roadService.getLeftCoordX());
        modelAndView.addObject("rightCoordX", roadService.getRightCoordX());
        modelAndView.addObject("topCoordY", roadService.getTopCoordY());
        modelAndView.addObject("bottomCoordY", roadService.getBottomCoordY());
        modelAndView.addObject("roadCoordScheme", roadService.getRoadCoordScheme());
        modelAndView.setViewName("admin_view_scheme");
        return modelAndView;
    }

}
