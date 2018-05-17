package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.dao.PassengerDao;
import system.dao.TicketDao;
import system.dao.TrainDao;
import system.exceptions.CantBuyTicketException;
import system.exceptions.NotFoundInDatabaseException;
import system.model.Passenger;
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

    public TicketService() { }

    public TicketDao getTicketDao() { return ticketDao; }

    public void setTicketDao(TicketDao ticketDao) { this.ticketDao = ticketDao; }

    public TrainService getTrainService() {
        return trainService;
    }

    public void setTrainService(TrainService trainService) {
        this.trainService = trainService;
    }

    public List<Ticket> getAllTickets(){return this.ticketDao.getAllTickets(); }

    public List<Ticket> getTicketByPassengerId(int passengerId){
        return this.ticketDao.getTicketByPassengerId(passengerId);
    }

    public List<Ticket> getTicketByTrainId(int trainId){ return this.ticketDao.getTicketByTrainId(trainId); }

    public void addTicket(int passengerId, int trainNumber) throws NotFoundInDatabaseException, CantBuyTicketException {
        List<Train> trainList = trainService.getTrainByNumber(trainNumber);
        if (trainList.size() == 0) {
            throw new NotFoundInDatabaseException("No train with this number!");
        }
        if (trainList.get(0).getFreeSeats() == 0) {
            throw new CantBuyTicketException("There are no free seats on this train.");
        }
        List<Ticket> ticketList = ticketDao.getTicketByPassengerIdAndTrainId(passengerId, trainList.get(0).getId());
        if (ticketList.size() !=0) {
            throw new CantBuyTicketException("You've already purchased ticket on this train.");
        }
        Ticket ticket = new Ticket();
        ticket.setPassengerId(passengerId);
        ticket.setTrainId(trainList.get(0).getId());
        trainService.reduceFreeSeats(trainList.get(0));
        this.ticketDao.addTicket(ticket);
    }

}
