package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.dao.PassengerDao;
import system.dao.StationDao;
import system.dao.TrainDao;
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

    public List<Station> getAllStations(){ return this.stationDao.getAllStations(); }

    public List<Station> getStationById(int id){ return this.stationDao.getStationById(id); }

    public List<Station> getStationByName(String name){ return this.stationDao.getStationByName(name); }

    public void addStation(Station station) {
        this.stationDao.addStation(station);
    }

}
