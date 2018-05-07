package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.dao.PassengerDao;
import system.model.Passenger;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PassengerService {

    @Autowired
    private PassengerDao passengerDao;

    public PassengerService() { }

    public PassengerDao getPassengerDao() { return passengerDao; }

    public void setPassengerDao(PassengerDao passengerDao) { this.passengerDao = passengerDao; }

    public List<Passenger> getAllPassengers(){ return this.passengerDao.getAllPassengers(); }

    public void addPassenger(Passenger passenger) {
        this.passengerDao.addPassenger(passenger);
    }

}
