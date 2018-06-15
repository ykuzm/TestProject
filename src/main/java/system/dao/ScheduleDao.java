package system.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import system.model.Schedule;
import system.model.Train;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class ScheduleDao {

    private SessionFactory sessionFactory;

    public ScheduleDao() { }

    public SessionFactory getSessionFactory() { return sessionFactory; }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Method for getting all schedule from DB
     *
     * @return full list of schedule from DB
     */
    @SuppressWarnings("unchecked")
    public List<Schedule> getAllSchedule() {
        Session session;
        try {
            session = this.sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        List<Schedule> sceduleList = session.createQuery("from Schedule" ).list();
        session.close();
        return sceduleList;
    }

    /**
     * Method for getting schedule by train id
     *
     * @param trainId train id
     * @return List of schedule for train with selected id
     */
    @SuppressWarnings("unchecked")
    public List<Schedule> getScheduleByTrainId(int trainId) {
        Session session;
        try {
            session = this.sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        Query query = session.createQuery("from Schedule s where s.trainId=:trainId");
        query.setParameter("trainId", trainId);
        List<Schedule> scheduleList = ((org.hibernate.query.Query) query).list();
        session.close();
        return scheduleList;
    }

    /**
     * Method for getting schedule by station id
     *
     * @param stationId station id
     * @return List of schedule for station with selected id
     */
    @SuppressWarnings("unchecked")
    public List<Schedule> getScheduleByStationId(int stationId) {
        Session session;
        try {
            session = this.sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        Query query = session.createQuery("from Schedule s where s.stationId=:stationId");
        query.setParameter("stationId", stationId);
        List<Schedule> scheduleList = ((org.hibernate.query.Query) query).list();
        session.close();
        return scheduleList;
    }

    /**
     * Method for getting schedule by train id and station id
     *
     * @param trainId  train id
     * @param stationId station id
     * @return schedule for selected train id and station id
     */
    @SuppressWarnings("unchecked")
    public Schedule getScheduleByTrainIdAndStationId(int trainId, int stationId) {
        Session session;
        try {
            session = this.sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        Query query = session.createQuery("from Schedule s where s.trainId=:trainId and" +
                " s.stationId=:stationId");
        query.setParameter("trainId", trainId);
        query.setParameter("stationId", stationId);
        List<Schedule> scheduleList = ((org.hibernate.query.Query) query).list();
        session.close();
        if (scheduleList.size() == 0) {
            return null;
        }
        return scheduleList.get(0);
    }

    /**
     * Method for adding Schedule in DB
     *
     * @param schedule schedule
     */
    public void addSchedule(Schedule schedule) {
        Session session;
        try {
            session = this.sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        Transaction transaction = session.beginTransaction();
        session.persist(schedule);
        transaction.commit();
        session.close();
    }

}