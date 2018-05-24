package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.dao.PassengerDao;
import system.dao.ScheduleDao;
import system.dao.TrainDao;
import system.exceptions.CantAddDataException;
import system.exceptions.NotFoundInDatabaseException;
import system.model.*;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleDao scheduleDao;

    @Autowired
    private TrainService trainService;

    @Autowired
    private StationService stationService;

    public ScheduleService() { }

    public ScheduleDao getScheduleDao() { return scheduleDao; }

    public void setScheduleDao(ScheduleDao scheduleDao) { this.scheduleDao = scheduleDao; }

    public TrainService getTrainService() {
        return trainService;
    }

    public void setTrainService(TrainService trainService) {
        this.trainService = trainService;
    }

    public List<Schedule> getAllSchedule(){ return scheduleDao.getAllSchedule(); }

    public List<Schedule> getScheduleByTrainId(int trainId){ return scheduleDao.getScheduleByTrainId(trainId); }

    public Map<String, Date> getScheduleByTrainNumber(int trainNumber) throws NotFoundInDatabaseException {
        Train train = trainService.getTrainByNumber(trainNumber);
        List<Schedule> scheduleList = getScheduleByTrainId(train.getId());
        Map<String, Date> scheduleMap = new LinkedHashMap<String, Date>();
        for (Schedule schedule: scheduleList) {
            scheduleMap.put(stationService.getStationById(schedule.getStationId()).getName(), schedule.getArrivalTime());
        }
        return scheduleMap;
    }

    public List<Schedule> getScheduleByStationId(int stationId){ return scheduleDao.getScheduleByStationId(stationId); }

    public void addSchedule(int trainNumber, String stationName, Date departureDate) throws NotFoundInDatabaseException, CantAddDataException {
        Train train = trainService.getTrainByNumber(trainNumber);
        Station station = stationService.getStationByName(stationName);
        List<Schedule> scheduleList = scheduleDao.getScheduleByTrainId(train.getId());
        for (Schedule schedule: scheduleList) {
            if (schedule.getStationId() == station.getId()) {
                throw new CantAddDataException("Station " + stationName + " is already on the route of train №"
                        + trainNumber);
            }
        }
        if (scheduleList.size() > 0 && scheduleList.get(scheduleList.size()-1).getArrivalTime().after(departureDate)) {
            throw new CantAddDataException("Departure time is too early");
        }
        Schedule schedule = new Schedule();
        schedule.setTrainId(train.getId());
        schedule.setStationId(station.getId());
        schedule.setArrivalTime(departureDate);
        scheduleDao.addSchedule(schedule);
    }

}
