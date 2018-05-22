package system.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import system.model.Passenger;
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