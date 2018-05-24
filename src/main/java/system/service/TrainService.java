package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.additional.TrainSearch;
import system.dao.PassengerDao;
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

    public List<Train> getAllTrains(){ return trainDao.getAllTrains(); }

    public Train getTrainById(int id){ return trainDao.getTrainById(id); }

    public Train getTrainByNumber(int number) throws NotFoundInDatabaseException {
        Train train = trainDao.getTrainByNumber(number);
        if (train == null) {
            throw new NotFoundInDatabaseException("No train with number " + number + "!");
        }
        return train;
    }

    public void reduceFreeSeats(Train train) {trainDao.reduceFreeSeats(train);}

    public void addTrain(Train train) throws CantAddDataException {
        try {
            Train train1 = getTrainByNumber(train.getNumber());
            throw new CantAddDataException("Train number " + train.getNumber() + " already exists.");
        } catch (NotFoundInDatabaseException e) {
            trainDao.addTrain(train);
        }
    }

    public List<Train> getTrainByPassengerId(int passengerId){
        List<Ticket> ticketList = ticketService.getTicketByPassengerId(passengerId);
        List<Train> trainList = new ArrayList<Train>();
        for (Ticket ticket: ticketList) {
            trainList.add(getTrainById(ticket.getTrainId()));
        }
        return trainList;
    }

    public Map<Integer, String> getTrainsByStation(Station station1) throws NotFoundInDatabaseException {
        Station station = stationService.getStationByName(station1.getName());
        List<Schedule> scheduleList = scheduleService.getScheduleByStationId(station.getId());
        Map<Integer, String> trainMap = new HashMap<Integer, String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        for (Schedule schedule: scheduleList) {
            trainMap.put(getTrainById(schedule.getTrainId()).getNumber(), sdf.format(schedule.getDepartureDate()));
        }
        return trainMap;
    }

    public Map<Integer, String> searchTrains(TrainSearch trainSearch) throws NotFoundInDatabaseException,
            CantBuyTicketException {
        Station station1 = stationService.getStationByName(trainSearch.getStation1().getName());
        Station station2 = stationService.getStationByName(trainSearch.getStation2().getName());
        Date date = trainSearch.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        if (station1.getName().equals(station2.getName())) {
            throw new CantBuyTicketException("You should choose two different stations!");
        }
        List<Schedule> stationScheduleList = scheduleService.getScheduleByStationId(station1.getId());
        Map<Integer, String> trainMap = new HashMap<Integer, String>();
        outer:
        for (Schedule schedule: stationScheduleList) {
            Train train = getTrainById(schedule.getTrainId());
            List<Schedule> trainScheduleList = scheduleService.getScheduleByTrainId(train.getId());
            int i = 0;
            int j = 0;
            for (int index = 0; index < trainScheduleList.size(); index++) {
                if (trainScheduleList.get(index).getStationId() == station1.getId()) {
                    String a = sdf.format(trainScheduleList.get(index).getDepartureDate());
                    String d = sdf.format(date);
                    if (a.equals(d)) {
                        i = index;
                    }
                    else {
                        continue outer;
                    }
                }
                if (trainScheduleList.get(index).getStationId() == station2.getId()) {
                    j = index;
                }
                if (i > 0 && j > 0) {
                    break;
                }
            }
            if (j > i) {
                trainMap.put(train.getNumber(), sdf2.format(trainScheduleList.get(i).getDepartureDate()));
            }
        }
        return trainMap;
    }

}
