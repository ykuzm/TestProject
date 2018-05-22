package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import system.dao.PassengerDao;
import system.model.Passenger;
import system.model.Ticket;
import system.model.Train;
import system.service.PassengerService;
import system.service.ScheduleService;
import system.service.TicketService;
import system.service.TrainService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
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

    @RequestMapping(value = "/listschedule", method = RequestMethod.GET)
    @ResponseBody
    public String getAllSchdeule() {
        return scheduleService.getAllSchedule().toString();
    }

}