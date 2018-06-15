package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.additional.TrainAdd;
import system.dao.UserDao;
import system.dao.TicketDao;
import system.dao.TrainDao;
import system.exceptions.CantBuyTicketException;
import system.exceptions.NotFoundInDatabaseException;
import system.model.*;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class TicketService {

    @Autowired
    private TicketDao ticketDao;

    @Autowired
    private TrainService trainService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private StationService stationService;

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

    public StationService getStationService() {
        return stationService;
    }

    public void setStationService(StationService stationService) {
        this.stationService = stationService;
    }

    /**
     * Method for getting all tickets from DB
     *
     * @return full list of tickets from DB
     */
    public List<Ticket> getAllTickets(){return ticketDao.getAllTickets(); }

    /**
     * Method for getting ticket by user id
     *
     * @param userId user id
     * @return list of tickets for User with selected id
     */
    public List<Ticket> getTicketByUserId(int userId){
        return ticketDao.getTicketByUserId(userId);
    }

    /**
     * Method for getting ticket by train id
     *
     * @param trainId train id
     * @return list of tickets for Train with selected id
     */
    public List<Ticket> getTicketByTrainId(int trainId){ return ticketDao.getTicketByTrainId(trainId); }

    /**
     * Method for adding Ticket in DB
     *
     * @param userId user id
     * @param trainNumber train number
     * @throws NotFoundInDatabaseException if there is no Train with this number
     * @throws CantBuyTicketException if there are no free seats on the train, or there is no schedule for
     * this train, or user already bought ticket on this train
     */
    public void addTicket(int userId, int trainNumber, String stationName1, String stationName2)
            throws NotFoundInDatabaseException, CantBuyTicketException {
        Train train = trainService.getTrainByNumber(trainNumber);
        Station station1 = stationService.getStationByName(stationName1);
        Station station2 = stationService.getStationByName(stationName2);
        if (!scheduleService.checkTrainSchedule(train.getId(), station1.getId(), station2.getId())) { // проверка, что поезд идет через выбранные станции
            throw new NotFoundInDatabaseException("Train number " + trainNumber + " doesn't go " +
                    "through stations " + stationName1 + " and " + stationName2 +".");
        }
        Ticket ticket = createTicket(userId, train.getId(), station1.getId(), station2.getId());
//        if (getAllTickets().contains(ticket)) { // проверка, что user еще не покупал билет на этот поезд
//            throw new CantBuyTicketException("You've already purchased ticket on train number " + trainNumber + ".");
//        }
//        if (!checkAvailableTickets(train, station1, station2)) { // проверка свободных билетов на выбранный участок маршрута
//            throw new CantBuyTicketException("There is no free seats on train number " + trainNumber +
//            " on its way from " + stationName1 + " to " + stationName2 + ".");
//        };
        List<Schedule> scheduleList = scheduleService.getScheduleByTrainId(train.getId());
        if (scheduleList.size() < 2) { // проверка, что у поезда есть правильное расписание
            throw new CantBuyTicketException("There are no schedule for train number " + trainNumber + " yet.");
        }
        ticketDao.addTicket(ticket);
    }


    /**
     * Method for creating ticket from input data
     *
     * @param userId user id
     * @param trainId train id
     * @param stationId1 station id 1
     * @param stationId2 station id 2
     * @return ticket with input data
     */
    private Ticket createTicket(int userId, int trainId, int stationId1, int stationId2){
        Ticket ticket = new Ticket();
        ticket.setUserId(userId);
        ticket.setTrainId(trainId);
        ticket.setStationId1(stationId1);
        ticket.setStationId2(stationId2);
        return ticket;
    }

    

}
