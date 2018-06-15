package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.additional.TrainAdd;
import system.additional.TrainSearch;
import system.dao.UserDao;
import system.dao.TrainDao;
import system.exceptions.CantAddDataException;
import system.exceptions.CantBuyTicketException;
import system.exceptions.NotFoundInDatabaseException;
import system.model.*;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TrainService {

    @Autowired
    private TrainDao trainDao;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private StationService stationService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private RoadService roadService;

    public TrainService() { }

    public TrainDao getTrainDao() { return trainDao; }

    public void setTrainDao(TrainDao trainDao) { this.trainDao = trainDao; }

    public TicketService getTicketService() {
        return ticketService;
    }

    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
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

    public RoadService getRoadService() {
        return roadService;
    }

    public void setRoadService(RoadService roadService) {
        this.roadService = roadService;
    }

    /**
     * Method for getting all trains from DB
     *
     * @return full list of trains from DB
     */
    public List<Train> getAllTrains(){ return trainDao.getAllTrains(); }

    /**
     * Method for getting train by id
     *
     * @param id id
     * @return Train with selected id, null if there is no Train in DB with this id
     */
    public Train getTrainById(int id){ return trainDao.getTrainById(id); }

    /**
     * Method for getting train by number
     *
     * @param number number
     * @return Train with selected number
     * @throws NotFoundInDatabaseException if there is ni Train with such number
     */
    public Train getTrainByNumber(int number) throws NotFoundInDatabaseException {
        Train train = trainDao.getTrainByNumber(number);
        if (train == null) {
            throw new NotFoundInDatabaseException("No train with number " + number + "!");
        }
        return train;
    }

    /**
     * Method for creating train from input data
     *
     * @param number number
     * @param capacity capacity
     * @param velocity velocity
     * @return train with parameters from trainAdd
     */
    public Train createTrain(int number, int capacity, int velocity){
        Train train = new Train();
        train.setNumber(number);
        train.setCapacity(capacity);
        train.setVelocity(velocity);
        return train;
    }

    /**
     * Method for adding Train in DB
     *
     * @param trainAdd trainAdd
     * @throws CantAddDataException if Train with this number already exists
     * @throws NotFoundInDatabaseException if there is no station with such name
     */
    public void addTrain(TrainAdd trainAdd) throws CantAddDataException, NotFoundInDatabaseException {
        Train train = trainAdd.getTrain();
        if (trainDao.getAllTrains().contains(train)) {
            throw new CantAddDataException("Train number " + train.getNumber() + " already exists.");
        }
        Station station1 = stationService.getStationByName(trainAdd.getStation1().getName());
        Station station2 = stationService.getStationByName(trainAdd.getStation2().getName());
        if (station1.equals(station2)) {
            throw new CantAddDataException("Choose two different stations!");
        }
        trainDao.addTrain(train);
        scheduleService.addFirstSchedule(train, station1.getId(), station2.getId(), trainAdd.getDepartureDate());
    }

    /**
     * Method for getting all Trains witch User purchased tickets on
     *
     * @param userId user id
     * @return list of Train
     */
    public List<Train> getTrainByUserId(int userId){
        List<Ticket> ticketList = ticketService.getTicketByUserId(userId);
        List<Train> trainList = new ArrayList<Train>();
        for (Ticket ticket: ticketList) {
            trainList.add(getTrainById(ticket.getTrainId()));
        }
        return trainList;
    }

    /**
     * Method for getting all trains going through station
     *
     * @param station1 station1
     * @return map of train number and departure date
     * @throws NotFoundInDatabaseException if there is no station with this name
     */
    public Map<Integer, Date> getTrainsByStation(Station station1) throws NotFoundInDatabaseException {
        Station station = stationService.getStationByName(station1.getName());
        List<Schedule> scheduleList = scheduleService.getScheduleByStationId(station.getId());
        Map<Integer, Date> trainMap = new HashMap<Integer, Date>();
        for (Schedule schedule: scheduleList) {
            trainMap.put(getTrainById(schedule.getTrainId()).getNumber(), schedule.getDepartureDate());
        }
        return trainMap;
    }

    /**
     * Method for getting all Trains going from station1 to station2 on certain date
     *
     * @param trainSearch trainSearch
     * @return map with train number and departure date
     * @throws NotFoundInDatabaseException if there is no station with this name
     */
    public Map<Integer, Date> searchTrains(TrainSearch trainSearch) throws NotFoundInDatabaseException {
        Station station1 = stationService.getStationByName(trainSearch.getStation1().getName());
        Station station2 = stationService.getStationByName(trainSearch.getStation2().getName());
        if (station1.getName().equals(station2.getName())) {
            throw new NotFoundInDatabaseException("You should choose two different stations!");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<Train> trainList = getAllTrains();
        Map<Integer, Date> trainMap = new HashMap<Integer, Date>();
        for (Train train: trainList) {
            if (scheduleService.checkTrainSchedule(train.getId(), station1.getId(), station2.getId())) {
                Schedule schedule = scheduleService.getScheduleByTrainIdAndStationId(train.getId(), station1.getId());
                String stringSearchDate = sdf.format(trainSearch.getDate());
                String stringScheduleDate = sdf.format(schedule.getDepartureDate());
                if (stringSearchDate.equals(stringScheduleDate)) {
                    trainMap.put(train.getNumber(), schedule.getDepartureDate());
                }
            }
        }
        return trainMap;
    }

}
