package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.dao.PassengerDao;
import system.exceptions.CantRegisterException;
import system.exceptions.NotFoundInDatabaseException;
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

    public List<Passenger> getAllPassengers(){ return passengerDao.getAllPassengers(); }

    public Passenger getPassengerById(int id){ return passengerDao.getPassengerById(id); }

    public Passenger getPassengerByLogin(String login){ return passengerDao.getPassengerByLogin(login); }

    public Passenger login(Passenger passenger) throws NotFoundInDatabaseException {
        Passenger passenger1 = getPassengerByLogin(passenger.getLogin());
        if (passenger1 == null || !passenger1.getPassword().equals(passenger.getPassword())) {
            throw new NotFoundInDatabaseException("Sorry! But your pair login-password is incorrect. " +
                    "Try again to log in or register.");
        }
        return passenger1;
    }

    public void addPassenger(Passenger passenger) throws CantRegisterException {
        Passenger passenger1 = passengerDao.getPassengerByLogin(passenger.getLogin());
        if (passenger1 != null) {
            throw new CantRegisterException("Sorry! But account with this login already exists. Try another one.");
        }
        passengerDao.addPassenger(passenger);
    }

}
