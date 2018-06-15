package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.additional.TrainAdd;
import system.dao.UserDao;
import system.dao.ScheduleDao;
import system.dao.TrainDao;
import system.exceptions.CantAddDataException;
import system.exceptions.NotFoundInDatabaseException;
import system.model.*;

import javax.persistence.criteria.CriteriaBuilder;
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

    @Autowired
    private RoadService roadService;

    public ScheduleService() { }

    public ScheduleDao getScheduleDao() { return scheduleDao; }

    public void setScheduleDao(ScheduleDao scheduleDao) { this.scheduleDao = scheduleDao; }

    public TrainService getTrainService() {
        return trainService;
    }

    public void setTrainService(TrainService trainService) {
        this.trainService = trainService;
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
     * Method for getting all schedule from DB
     *
     * @return full list of schedule from DB
     */
    public List<Schedule> getAllSchedule(){ return scheduleDao.getAllSchedule(); }

    /**
     * Method for getting schedule by train id
     *
     * @param trainId train id
     * @return List of schedule for train with selected id
     */
    public List<Schedule> getScheduleByTrainId(int trainId){ return scheduleDao.getScheduleByTrainId(trainId); }

    /**
     * Method for getting schedule by train id and station id
     *
     * @param trainId  train id
     * @param stationId station id
     * @return schedule for selected train id and station id
     */
    public Schedule getScheduleByTrainIdAndStationId (int trainId, int stationId) {
        return scheduleDao.getScheduleByTrainIdAndStationId(trainId, stationId);
    }

    /**
     * Method for getting full schedule of the train by its number
     *
     * @param trainNumber train number
     * @return map of station name and departure date
     * @throws NotFoundInDatabaseException if there is no train with this number
     */
    public Map<String, Date> getScheduleByTrainNumber(int trainNumber) throws NotFoundInDatabaseException {
        Train train = trainService.getTrainByNumber(trainNumber);
        List<Schedule> scheduleList = getScheduleByTrainId(train.getId());
        Map<String, Date> scheduleMap = new LinkedHashMap<String, Date>();
        for (Schedule schedule: scheduleList) {
            scheduleMap.put(stationService.getStationById(schedule.getStationId()).getName(),
                    schedule.getDepartureDate());
        }
        return scheduleMap;
    }

    /**
     * Method for getting schedule by station id
     *
     * @param stationId station id
     * @return List of schedule for station with selected id
     */
    public List<Schedule> getScheduleByStationId(int stationId){ return scheduleDao.getScheduleByStationId(stationId); }

    /**
     * Method for adding schedule
     *
     * @param schedule schedule
     */
    public void addSchedule(Schedule schedule) {
        scheduleDao.addSchedule(schedule);
    }

    /**
     * Method for adding first schedule for train
     *
     * @param train train
     * @param stationId1 station id 1
     * @param stationId2 station id 2
     * @param departureDate departure date
     */
    public void addFirstSchedule(Train train, int stationId1, int stationId2, Date departureDate)
            throws NotFoundInDatabaseException, CantAddDataException {
        if (stationId1 == stationId2) {
            throw new CantAddDataException("Choose two different stations");
        }
        List<Integer> trainWay = roadService.dijkstra(stationId1, stationId2);
        addSchedule(createSchedule(train.getId(), trainWay.get(0), departureDate));
        addScheduleFromTrainWay(train, trainWay, departureDate);
    }

    /**
     * Method for adding additional schedule for train (which has got main schedule)
     *
     * @param train train
     * @param stationId station id
     */
    public void addAdditionalSchedule(Train train, int stationId)
            throws NotFoundInDatabaseException, CantAddDataException {
        List<Schedule> scheduleList = getScheduleByTrainId(train.getId());
        if (scheduleList.size() == 0) { // проверка, что у поезда есть основное расписание
            throw new CantAddDataException("This train hasn't got main route.");
        }
        List<Integer> existingTrainWay = new ArrayList<Integer>();
        for (Schedule schedule : scheduleList) { // получаем список station id для существующего маршрута
            existingTrainWay.add(schedule.getStationId());
        }
        if (existingTrainWay.contains(stationId)) { // проверка, что новая станция не находится на существующем маршруте
            throw new CantAddDataException("This station is already on the route.");
        }
        Schedule lastSchedule = scheduleList.get(scheduleList.size()-1);
        List<Integer> trainWay = roadService.dijkstra(lastSchedule.getStationId(), stationId); // алгоритм декйстры для последней станции в существующем маршруте и новой (добавляемой) станции маршрута
        existingTrainWay.removeAll(trainWay);
        if (existingTrainWay.size() < scheduleList.size() - 1) { // проверка, что новая часть маршрута не содержит пересечений с существующим маршрутом
            throw new CantAddDataException("Additional schedule contains stations from existing route.");
        }
        addScheduleFromTrainWay(train, trainWay, lastSchedule.getDepartureDate());
    }

    /**
     * Method for adding train schedule from trainWay (which is obtained from Dijksta algorithm)
     *
     * @param train train
     * @param trainWay train way
     * @param date date
     * @throws NotFoundInDatabaseException if there is no road between two stations
     */
    public void addScheduleFromTrainWay (Train train, List<Integer> trainWay, Date date)
            throws NotFoundInDatabaseException {
        double velocity = train.getVelocity();
        double s = 0;
        for (int i = 1; i < trainWay.size(); i++) {
            Road road = roadService.getRoadByStationId1AndStationId2(trainWay.get(i-1), trainWay.get(i));
            s = s + road.getDistance();
            date = new Date(date.getTime() + (int) (s/velocity*60*60*1000));
            addSchedule(createSchedule(train.getId(), trainWay.get(i), date));
        }
    }

    /**
     * Method for creating schedule from input data
     *
     * @param trainId train id
     * @param stationId station id
     * @param departureDate departure date
     * @return schedule
     */
    public Schedule createSchedule(int trainId, int stationId, Date departureDate) {
        Schedule schedule = new Schedule();
        schedule.setTrainId(trainId);
        schedule.setStationId(stationId);
        schedule.setDepartureDate(departureDate);
        return schedule;
    }

    /**
     * Method checks that train goes through two certain stations
     *
     * @param trainId train id
     * @param stationId1 station id 1
     * @param stationId2 station id 2
     * @return true if train goes through two certain stations, false otherwise
     */
    public boolean checkTrainSchedule (int trainId, int stationId1, int stationId2) {
        Schedule schedule1 = getScheduleByTrainIdAndStationId(trainId, stationId1);
        Schedule schedule2 = getScheduleByTrainIdAndStationId(trainId, stationId2);
        return (schedule1 != null && schedule2 != null && schedule1.getId() < schedule2.getId());
    }

}
