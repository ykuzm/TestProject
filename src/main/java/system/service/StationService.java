package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.dao.UserDao;
import system.dao.StationDao;
import system.dao.TrainDao;
import system.exceptions.CantAddDataException;
import system.exceptions.NotFoundInDatabaseException;
import system.model.Schedule;
import system.model.User;
import system.model.Station;
import system.model.Train;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class StationService {

    @Autowired
    private StationDao stationDao;

    @Autowired
    private ScheduleService scheduleService;

    public StationService() { }

    public StationDao getStationDao() { return stationDao; }

    public void setStationDao(StationDao stationDao) { this.stationDao = stationDao; }

    public ScheduleService getScheduleService() {
        return scheduleService;
    }

    public void setScheduleService(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    /**
     * Method for getting all stations from DB
     *
     * @return full list of stations from DB
     */
    public List<Station> getAllStations(){ return stationDao.getAllStations(); }

    /**
     * Method for getting station by id
     *
     * @param id id
     * @return Station with selected id, null if there is no Station in DB with this id
     */
    public Station getStationById(int id){ return stationDao.getStationById(id); }

    /**
     * Method for getting user by name
     *
     * @param name name
     * @return Station with selected name
     * @throws NotFoundInDatabaseException if there is no Station in DB with this name
     */
    public Station getStationByName(String name) throws NotFoundInDatabaseException {
        Station station = stationDao.getStationByName(name);
        if (station == null) {
            throw new NotFoundInDatabaseException("There is no station with the name " + name + ".");
        }
        return station;
    }

    /**
     * Method for adding Station in DB
     *
     * @param station station
     * @throws CantAddDataException if Station with this coordinates already exsists in DB
     */
    public void addStation(Station station) throws CantAddDataException {
        if (stationDao.getAllStations().contains(station)) {
            throw new CantAddDataException("Station named " + station.getName() + " already exists " +
            " at coordinates (" + station.getCoordX() + ";" + station.getCoordY() + ").");
        }
        stationDao.addStation(station);
    }

    /**
     * Method for creating station from input data
     *
     * @param name name
     * @param coordX coordinate X
     * @param coordY coordinate Y
     * @return station created from input data
     */
    public Station createStation(String name, int coordX, int coordY) {
        Station station = new Station();
        station.setName(name);
        station.setCoordX(coordX);
        station.setCoordY(coordY);
        return station;
    }

    /**
     * Method for getting station id list through which goes train
     *
     * @param train train
     * @return station id list through which goes train
     */
    public List<Integer> getStationIdList(Train train) {
        List<Schedule> scheduleList = scheduleService.getScheduleByTrainId(train.getId());
        List<Integer> stationIdList = new ArrayList<Integer>();
        for (Schedule schedule : scheduleList) {
            stationIdList.add(schedule.getStationId());
        }
        return stationIdList;
    }

}
