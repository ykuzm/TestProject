package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.dao.PassengerDao;
import system.dao.StationDao;
import system.dao.TrainDao;
import system.exceptions.CantAddDataException;
import system.exceptions.NotFoundInDatabaseException;
import system.model.Passenger;
import system.model.Station;
import system.model.Train;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class StationService {

    @Autowired
    private StationDao stationDao;

    public StationService() { }

    public StationDao getStationDao() { return stationDao; }

    public void setStationDao(StationDao stationDao) { this.stationDao = stationDao; }

    public List<Station> getAllStations(){ return stationDao.getAllStations(); }

    public Station getStationById(int id){ return stationDao.getStationById(id); }

    public Station getStationByName(String name) throws NotFoundInDatabaseException {
        Station station = stationDao.getStationByName(name);
        if (station == null) {
            throw new NotFoundInDatabaseException("There is no station with the name " + name);
        }
        return station;
    }

    public void addStation(Station station) throws CantAddDataException {
        try {
            Station station1 = getStationByName(station.getName());
            throw new CantAddDataException("Station " + station.getName() + " already exists.");
        } catch (NotFoundInDatabaseException e) {
            stationDao.addStation(station);
        }
    }

}
