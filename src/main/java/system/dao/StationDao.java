package system.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import system.model.Passenger;
import system.model.Station;
import system.model.Train;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class StationDao {

    private SessionFactory sessionFactory;

    public StationDao() { }

    public SessionFactory getSessionFactory() { return sessionFactory; }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @SuppressWarnings("unchecked")
    public List<Station> getAllStations() {
        Session session;
        try {
            session = this.sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        List<Station> stationList = session.createQuery("from Station" ).list();
        return stationList;
    }

    @SuppressWarnings("unchecked")
    public List<Station> getStationById(int id) {
        Session session;
        try {
            session = this.sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        Query query = session.createQuery("from Station s where s.id=:id");
        query.setParameter("id", id);
        List<Station> stationList = ((org.hibernate.query.Query) query).list();
        return stationList;
    }

    @SuppressWarnings("unchecked")
    public List<Station> getStationByName(String name) {
        Session session;
        try {
            session = this.sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        Query query = session.createQuery("from Station s where s.name=:name");
        query.setParameter("name", name);
        List<Station> stationList = ((org.hibernate.query.Query) query).list();
        return stationList;
    }

    public void addStation(Station station) {
        Session session;
        try {
            session = this.sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        Transaction transaction = session.beginTransaction();
        session.persist(station);
        transaction.commit();
    }

}