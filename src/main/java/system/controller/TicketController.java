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
import system.service.TicketService;
import system.service.TrainService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.regex.Pattern;

@Controller
//@SessionAttributes(value="ticket")
@RequestMapping(value = "/railway", method = RequestMethod.GET)
public class TicketController {

    @Autowired
    private TicketService ticketService;

    public TicketService getTicketService() {
        return ticketService;
    }

    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @RequestMapping(value = "/listtickets", method = RequestMethod.GET)
    @ResponseBody
    public String getAllTickets(){
        return ticketService.getAllTickets().toString();
    }

    @RequestMapping(value = "/account/buyticket", method = RequestMethod.GET)
    public ModelAndView buyTicket(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        Passenger passenger = (Passenger) request.getSession().getAttribute("passenger");
        if (passenger.getLogin() == null) {
            modelAndView.setViewName("passenger_need_to_login_page");
            return modelAndView;
        }
        modelAndView.addObject("train", new Train());
        modelAndView.setViewName("ticket_buy_page");
        return modelAndView;
    }

    @RequestMapping(value = "/account/buyticket/result", method = RequestMethod.POST)
    public ModelAndView buyTicketResult(@ModelAttribute("train") Train train, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        Passenger passenger = (Passenger) request.getSession().getAttribute("passenger");
        if (passenger.getLogin() == null) {
            modelAndView.setViewName("passenger_need_to_login_page");
            return modelAndView;
        }
        try {
            ticketService.addTicket(passenger.getId(), train.getNumber());
            modelAndView.setViewName("ticket_correct_buy_page");
        } catch (Exception e) {
            modelAndView.addObject("exception", e.getMessage());
            modelAndView.setViewName("ticket_buy_error_page");
        }
        return modelAndView;
    }
}
