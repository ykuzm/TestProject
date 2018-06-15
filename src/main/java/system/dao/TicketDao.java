package system.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import system.model.Ticket;
import system.model.Train;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class TicketDao {

    private SessionFactory sessionFactory;

    public TicketDao() { }

    public SessionFactory getSessionFactory() { return sessionFactory; }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Method for getting all tickets from DB
     *
     * @return full list of tickets from DB
     */
    @SuppressWarnings("unchecked")
    public List<Ticket> getAllTickets() {
        Session session;
        try {
            session = this.sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        List<Ticket> ticketList = session.createQuery("from Ticket" ).list();
        session.close();
        return ticketList;
    }

    /**
     * Method for getting ticket by user id
     *
     * @param userId user id
     * @return list of tickets for User with selected id
     */
    @SuppressWarnings("unchecked")
    public List<Ticket> getTicketByUserId(int userId) {
        Session session;
        try {
            session = this.sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        Query query = session.createQuery("from Ticket t where t.userId=:userId");
        query.setParameter("userId", userId);
        List<Ticket> ticketList = ((org.hibernate.query.Query) query).list();
        session.close();
        return ticketList;
    }

    /**
     * Method for getting ticket by train id
     *
     * @param trainId train id
     * @return list of tickets for Train with selected id
     */
    @SuppressWarnings("unchecked")
    public List<Ticket> getTicketByTrainId(int trainId) {
        Session session;
        try {
            session = this.sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        Query query = session.createQuery("from Ticket t where t.trainId=:trainId");
        query.setParameter("trainId", trainId);
        List<Ticket> ticketList = ((org.hibernate.query.Query) query).list();
        session.close();
        return ticketList;
    }

    /**
     * Method for getting ticket by user id and train id
     *
     * @param userId user id
     * @param trainId train id
     * @return Ticket with selected user id and train id, null if there is no such ticket
     */
    @SuppressWarnings("unchecked")
    public Ticket getTicketByUserIdAndTrainId(int userId, int trainId) {
        Session session;
        try {
            session = this.sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        Query query = session.createQuery("from Ticket t where t.userId=:userId " +
                "and t.trainId=:trainId");
        query.setParameter("userId", userId);
        query.setParameter("trainId", trainId);
        List<Ticket> ticketList = ((org.hibernate.query.Query) query).list();
        session.close();
        if (ticketList.size() == 0) {
            return null;
        }
        return ticketList.get(0);
    }

    /**
     * Method for adding Ticket in DB
     *
     * @param ticket ticket
     */
    public void addTicket(Ticket ticket) {
        Session session;
        try {
            session = this.sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        Transaction transaction = session.beginTransaction();
        session.persist(ticket);
        transaction.commit();
        session.close();
    }

}