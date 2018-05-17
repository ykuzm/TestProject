package system.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import system.model.Passenger;
import system.model.Train;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class TrainDao {

    private SessionFactory sessionFactory;

    public TrainDao() { }

    public SessionFactory getSessionFactory() { return sessionFactory; }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @SuppressWarnings("unchecked")
    public List<Train> getAllTrains() {
        Session session;
        try {
            session = this.sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        List<Train> trainList = session.createQuery("from Train" ).list();
        return trainList;
    }

    @SuppressWarnings("unchecked")
    public List<Train> getTrainById(int id) {
        Session session;
        try {
            session = this.sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        Query query = session.createQuery("from Train t where t.id=:id");
        query.setParameter("id", id);
        List<Train> trainList = ((org.hibernate.query.Query) query).list();
        return trainList;
    }

    @SuppressWarnings("unchecked")
    public List<Train> getTrainByNumber(int number) {
        Session session;
        try {
            session = this.sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        Query query = session.createQuery("from Train t where t.number=:number");
        query.setParameter("number", number);
        List<Train> trainList = ((org.hibernate.query.Query) query).list();
        return trainList;
    }

    public void reduceFreeSeats(Train train) {
        Session session;
        try {
            session = this.sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        int freeSeats = train.getFreeSeats() - 1;
        System.out.println(freeSeats);
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("update Train set freeSeats=:freeSeats where id=:id");
        query.setParameter("freeSeats", freeSeats);
        query.setParameter("id", train.getId());
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    public void addTrain(Train train) {
        Session session;
        try {
            session = this.sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        Transaction transaction = session.beginTransaction();
        session.persist(train);
        transaction.commit();
    }

}