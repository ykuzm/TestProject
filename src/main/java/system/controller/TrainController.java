package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import system.dao.PassengerDao;
import system.model.Passenger;
import system.model.Train;
import system.service.PassengerService;
import system.service.TicketService;
import system.service.TrainService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.regex.Pattern;

@Controller
@SessionAttributes(value="train")
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

    @RequestMapping(value = "/listtrains", method = RequestMethod.GET)
    @ResponseBody
    public String getAllTrains() {
        return trainService.getTrainById(4).toString();
    }

    @RequestMapping(value = "/account/purchasedtickets", method = RequestMethod.GET)
    public ModelAndView getTicketsByPassengerId(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        Passenger passenger = (Passenger) request.getSession().getAttribute("passenger");
        if (passenger.getLogin() == null) {
            modelAndView.setViewName("passenger_need_to_login_page");
            return modelAndView;
        }
        List<Train> trainList = trainService.getTrainByTicket(trainService.getTicketService()
                                            .getTicketByPassengerId(passenger.getId()));
        System.out.println(trainList.size());
        modelAndView.addObject(trainList);
        modelAndView.setViewName("ticket_list_page");
        return modelAndView;
    }

}