package system.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import system.model.Station;
import system.model.Ticket;
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

    /**
     * Method for getting all stations from DB
     *
     * @return full list of stations from DB
     */
    @SuppressWarnings("unchecked")
    public List<Station> getAllStations() {
        Session session;
        try {
            session = this.sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        List<Station> stationList = session.createQuery("from Station" ).list();
        session.close();
        return stationList;
    }

    /**
     * Method for getting station by id
     *
     * @param id id
     * @return Station with selected id, null if there is no Station in DB with this id
     */
    @SuppressWarnings("unchecked")
    public Station getStationById(int id) {
        Session session;
        try {
            session = this.sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        Query query = session.createQuery("from Station s where s.id=:id");
        query.setParameter("id", id);
        List<Station> stationList = ((org.hibernate.query.Query) query).list();
        session.close();
        if (stationList.size() == 0) {
            return null;
        }
        return stationList.get(0);
    }

    /**
     * Method for getting user by name
     *
     * @param name name
     * @return Station with selected name, null if there is no Station in DB with this name
     */
    @SuppressWarnings("unchecked")
    public Station getStationByName(String name) {
        Session session;
        try {
            session = this.sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        Query query = session.createQuery("from Station s where s.name=:name");
        query.setParameter("name", name);
        List<Station> stationList = ((org.hibernate.query.Query) query).list();
        session.close();
        if (stationList.size() == 0) {
            return null;
        }
        return stationList.get(0);
    }

    /**
     * Method for adding Station in DB
     *
     * @param station station
     */
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
        session.close();
    }

}