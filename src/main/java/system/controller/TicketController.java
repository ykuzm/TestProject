package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
@RequestMapping(value = "/railway", method = RequestMethod.GET)
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private PassengerService passengerService;

    public TicketService getTicketService() {
        return ticketService;
    }

    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public PassengerService getPassengerService() {
        return passengerService;
    }

    public void setPassengerService(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @RequestMapping(value = "/account/buyticket", method = RequestMethod.GET)
    public ModelAndView buyTicket(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("train", new Train());
        modelAndView.setViewName("ticket_buy_page");
        return modelAndView;
    }

    @RequestMapping(value = "/account/buyticket/result", method = RequestMethod.POST)
    public ModelAndView buyTicketResult(@AuthenticationPrincipal UserDetails userDetails,
                                        @ModelAttribute("train") Train train){
        Passenger passenger = passengerService.getPassengerByLogin(userDetails.getUsername());
        ModelAndView modelAndView = new ModelAndView();
        try {
            ticketService.addTicket(passenger.getId(), train.getNumber());
            modelAndView.setViewName("ticket_buy_result_page");
        } catch (Exception e) {
            modelAndView.addObject("exception", e.getMessage());
            modelAndView.setViewName("ticket_buy_error_page");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/account/buyticket/result-{trainNumber}", method = RequestMethod.GET)
    public ModelAndView buyTicketResult(@AuthenticationPrincipal UserDetails userDetails,
                                        @PathVariable int trainNumber){
        Passenger passenger = passengerService.getPassengerByLogin(userDetails.getUsername());
        ModelAndView modelAndView = new ModelAndView();
        try {
            ticketService.addTicket(passenger.getId(), trainNumber);
            modelAndView.setViewName("ticket_buy_result_page");
        } catch (Exception e) {
            modelAndView.addObject("exception", e.getMessage());
            modelAndView.setViewName("ticket_buy_error_page");
        }
        return modelAndView;
    }
}
