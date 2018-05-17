package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.dao.PassengerDao;
import system.dao.TrainDao;
import system.model.Passenger;
import system.model.Ticket;
import system.model.Train;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class TrainService {

    @Autowired
    private TrainDao trainDao;

    @Autowired
    private TicketService ticketService;

    public TrainService() { }

    public TrainDao getTrainDao() { return trainDao; }

    public void setTrainDao(TrainDao trainDao) { this.trainDao = trainDao; }

    public TicketService getTicketService() {
        return ticketService;
    }

    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public List<Train> getAllTrains(){ return this.trainDao.getAllTrains(); }

    public List<Train> getTrainById(int id){ return this.trainDao.getTrainById(id); }

    public List<Train> getTrainByNumber(int number){ return this.trainDao.getTrainByNumber(number); }

    public void reduceFreeSeats(Train train) {this.trainDao.reduceFreeSeats(train);}

    public void addTrain(Train train) {
        this.trainDao.addTrain(train);
    }

    public List<Train> getTrainByTicket(List<Ticket> ticketList){
        List<Train> trainList = new ArrayList<Train>();
        for (Ticket ticket: ticketList) {
            trainList.add(trainDao.getTrainById(ticket.getTrainId()).get(0));
        }
        return trainList;
    }

}
