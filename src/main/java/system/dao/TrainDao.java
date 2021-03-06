package system.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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

    /**
     * Method for getting all trains from DB
     *
     * @return full list of trains from DB
     */
    @SuppressWarnings("unchecked")
    public List<Train> getAllTrains() {
        Session session;
        try {
            session = this.sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        List<Train> trainList = session.createQuery("from Train" ).list();
        session.close();
        return trainList;
    }

    /**
     * Method for getting train by id
     *
     * @param id id
     * @return Train with selected id, null if there is no Train in DB with this id
     */
    @SuppressWarnings("unchecked")
    public Train getTrainById(int id) {
        Session session;
        try {
            session = this.sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        Query query = session.createQuery("from Train t where t.id=:id");
        query.setParameter("id", id);
        List<Train> trainList = ((org.hibernate.query.Query) query).list();
        session.close();
        if (trainList.size() == 0) {
            return null;
        }
        return trainList.get(0);
    }

    /**
     * Method for getting train by number
     *
     * @param number number
     * @return Train with selected number, null if there is no Train in DB with this number
     */
    @SuppressWarnings("unchecked")
    public Train getTrainByNumber(int number) {
        Session session;
        try {
            session = this.sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        Query query = session.createQuery("from Train t where t.number=:number");
        query.setParameter("number", number);
        List<Train> trainList = ((org.hibernate.query.Query) query).list();
        session.close();
        if (trainList.size() == 0) {
            return null;
        }
        return trainList.get(0);
    }

    /**
     * Method for adding Train in DB
     *
     * @param train train
     */
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
        session.close();
    }

}