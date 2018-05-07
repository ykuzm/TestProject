package system.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import system.model.Passenger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class PassengerDao {

    private SessionFactory sessionFactory;

    public PassengerDao() { }

    public SessionFactory getSessionFactory() { return sessionFactory; }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @SuppressWarnings("unchecked")
    public List<Passenger> getAllPassengers() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Passenger> passengerList = session.createQuery("from Passenger").list();
        return passengerList;
    }

    public void addPassenger(Passenger passenger) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(passenger);
    }

}
