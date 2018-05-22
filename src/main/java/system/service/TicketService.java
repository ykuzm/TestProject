package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.dao.PassengerDao;
import system.dao.TicketDao;
import system.dao.TrainDao;
import system.exceptions.CantBuyTicketException;
import system.exceptions.NotFoundInDatabaseException;
import system.model.Passenger;
import system.model.Schedule;
import system.model.Ticket;
import system.model.Train;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketDao ticketDao;

    @Autowired
    private TrainService trainService;

    @Autowired
    private ScheduleService scheduleService;

    public TicketService() { }

    public TicketDao getTicketDao() { return ticketDao; }

    public void setTicketDao(TicketDao ticketDao) { this.ticketDao = ticketDao; }

    public TrainService getTrainService() {
        return trainService;
    }

    public void setTrainService(TrainService trainService) {
        this.trainService = trainService;
    }

    public ScheduleService getScheduleService() {
        return scheduleService;
    }

    public void setScheduleService(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    public List<Ticket> getAllTickets(){return ticketDao.getAllTickets(); }

    public List<Ticket> getTicketByPassengerId(int passengerId){
        return ticketDao.getTicketByPassengerId(passengerId);
    }

    public List<Ticket> getTicketByTrainId(int trainId){ return ticketDao.getTicketByTrainId(trainId); }

    public void addTicket(int passengerId, int trainNumber) throws NotFoundInDatabaseException, CantBuyTicketException {
        Train train = trainService.getTrainByNumber(trainNumber);
        if (train.getFreeSeats() == 0) {
            throw new CantBuyTicketException("There are no free seats on train number " + trainNumber + ".");
        }
        List<Schedule> scheduleList = scheduleService.getScheduleByTrainId(train.getId());
        if (scheduleList.size() < 2) {
            throw new CantBuyTicketException("There are no schedule for train number " + trainNumber + " yet.");
        }
        Ticket ticket = ticketDao.getTicketByPassengerIdAndTrainId(passengerId, train.getId());
        if (ticket != null) {
            throw new CantBuyTicketException("You've already purchased ticket on train number " + trainNumber + ".");
        }
        Ticket newTicket = new Ticket();
        newTicket.setPassengerId(passengerId);
        newTicket.setTrainId(train.getId());
        trainService.reduceFreeSeats(train);
        ticketDao.addTicket(newTicket);
    }

}
