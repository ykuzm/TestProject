package system.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import system.model.Road;
import system.model.Station;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RoadDao {

    public SessionFactory sessionFactory;

    public RoadDao() {
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Method for getting all roads from DB
     *
     * @return full list of roads from DB
     */
    @SuppressWarnings("unchecked")
    public List<Road> getAllRoads() {
        Session session;
        try {
            session = this.sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        List<Road> roadList = session.createQuery("from Road" ).list();
        session.close();
        return roadList;
    }

    /**
     * Method for getting road by id
     *
     * @param id id
     * @return Road with selected id, null if there is no Road in DB with this id
     */
    @SuppressWarnings("unchecked")
    public Road getRoadById(int id) {
        Session session;
        try {
            session = this.sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        Query query = session.createQuery("from Road r where r.id=:id");
        query.setParameter("id", id);
        List<Road> roadList = ((org.hibernate.query.Query) query).list();
        session.close();
        if (roadList.size() == 0) {
            return null;
        }
        return roadList.get(0);
    }

    /**
     * Method for getting roads by station id
     *
     * @param stationId station id
     * @return roads with stations with selected id
     */
    @SuppressWarnings("unchecked")
    public List<Road> getRoadByStationId(int stationId) {
        Session session;
        try {
            session = this.sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        Query query = session.createQuery("from Road r where r.stationId1=:stationId1");
        query.setParameter("stationId1", stationId);
        List<Road> roadList = ((org.hibernate.query.Query) query).list();
        session.close();
        return roadList;
    }

    /**
     * Method for getting roads by two stations id
     *
     * @param stationId1 station 1 id
     * @param stationId2 station 2 id
     * @return roads with stations with selected ids
     */
    @SuppressWarnings("unchecked")
    public Road getRoadByStationId1AndStationId2(int stationId1, int stationId2) {
        Session session;
        try {
            session = this.sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        Query query = session.createQuery("from Road r where r.stationId1=:stationId1 and " +
                "r.stationId2=:stationId2");
        query.setParameter("stationId1", stationId1);
        query.setParameter("stationId2", stationId2);
        List<Road> roadList = ((org.hibernate.query.Query) query).list();
        session.close();
        if (roadList.size() == 0) {
            return null;
        }
        return roadList.get(0);
    }

    /**
     * Method for adding Road in DB
     *
     * @param road road
     */
    public void addRoad(Road road) {
        Session session;
        try {
            session = this.sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        Transaction transaction = session.beginTransaction();
        session.persist(road);
        transaction.commit();
        session.close();
    }
}
