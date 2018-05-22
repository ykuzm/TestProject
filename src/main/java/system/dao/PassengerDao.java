package system.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import system.model.Passenger;
import system.model.Station;

import javax.persistence.Query;
import javax.transaction.Transactional;
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
        Session session;
        try {
        session = this.sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        List<Passenger> passengerList = session.createQuery("from Passenger" ).list();
        session.close();
        return passengerList;
    }

    @SuppressWarnings("unchecked")
    public Passenger getPassengerById(int id) {
        Session session;
        try {
            session = this.sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        Query query = session.createQuery("from Passenger p where p.id=:id");
        query.setParameter("id", id);
        List<Passenger> passengerList = ((org.hibernate.query.Query) query).list();
        session.close();
        if (passengerList.size() == 0) {
            return null;
        }
        return passengerList.get(0);
    }

    @SuppressWarnings("unchecked")
    public Passenger getPassengerByLogin(String login) {
        Session session;
        try {
            session = this.sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        Query query = session.createQuery("from Passenger p where p.login=:login");
        query.setParameter("login", login);
        List<Passenger> passengerList = ((org.hibernate.query.Query) query).list();
        session.close();
        if (passengerList.size() == 0) {
            return null;
        }
        return passengerList.get(0);
    }

    public void addPassenger(Passenger passenger) {
        Session session;
        try {
            session = this.sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        Transaction transaction = session.beginTransaction();
        session.persist(passenger);
        transaction.commit();
        session.close();
    }

}
