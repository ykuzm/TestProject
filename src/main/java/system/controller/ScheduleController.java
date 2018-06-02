package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import system.additional.ScheduleAdd;
import system.dao.PassengerDao;
import system.model.Passenger;
import system.model.Schedule;
import system.model.Ticket;
import system.model.Train;
import system.service.PassengerService;
import system.service.ScheduleService;
import system.service.TicketService;
import system.service.TrainService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Controller
@RequestMapping(value = "/railway", method = RequestMethod.GET)
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    public ScheduleService getScheduleService() {
        return scheduleService;
    }

    public void setScheduleService(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @RequestMapping(value = "/passenger/trainschedule-{trainNumber}", method = RequestMethod.GET)
    public ModelAndView viewTrainSchedule(@ModelAttribute String role,
                                          @PathVariable int trainNumber){
        ModelAndView modelAndView = new ModelAndView();
        try {
            Map<String, Date> scheduleMap = scheduleService.getScheduleByTrainNumber(trainNumber);
            modelAndView.addObject("scheduleMap", scheduleMap);
            modelAndView.addObject("trainNumber", trainNumber);
            modelAndView.setViewName("schedule_train_page");
        } catch (Exception e) {
            modelAndView.addObject("exception", e.getMessage());
            modelAndView.setViewName("schedule_train_error_page");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/admin/addschedule-{trainNumber}", method = RequestMethod.GET)
    public ModelAndView addScedule(@PathVariable int trainNumber){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("trainNumber", trainNumber);
        modelAndView.addObject("scheduleAdd", new ScheduleAdd());
        modelAndView.setViewName("schedule_add_page");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/addschedule/result-{trainNumber}", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView addSceduleResult(@PathVariable int trainNumber,
                                         @ModelAttribute("scheduleAdd") ScheduleAdd scheduleAdd){
        ModelAndView modelAndView = new ModelAndView();
        try {
            scheduleService.addSchedule(trainNumber, scheduleAdd.getStation().getName(), scheduleAdd.getDate());
            modelAndView.addObject("trainNumber", trainNumber);
            modelAndView.setViewName("schedule_add_result_page");
        } catch (Exception e) {
            modelAndView.addObject("exception", e.getMessage());
            modelAndView.setViewName("schedule_add_error_page");
        }
        return modelAndView;
    }

}