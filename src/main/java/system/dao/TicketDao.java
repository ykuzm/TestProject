package system.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import system.model.Passenger;
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

    @SuppressWarnings("unchecked")
    public List<Ticket> getTicketByPassengerId(int passengerId) {
        Session session;
        try {
            session = this.sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        Query query = session.createQuery("from Ticket t where t.passengerId=:passengerId");
        query.setParameter("passengerId", passengerId);
        List<Ticket> ticketList = ((org.hibernate.query.Query) query).list();
        session.close();
        return ticketList;
    }

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

    @SuppressWarnings("unchecked")
    public Ticket getTicketByPassengerIdAndTrainId(int passengerId, int trainId) {
        Session session;
        try {
            session = this.sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        Query query = session.createQuery("from Ticket t where t.passengerId=:passengerId " +
                "and t.trainId=:trainId");
        query.setParameter("passengerId", passengerId);
        query.setParameter("trainId", trainId);
        List<Ticket> ticketList = ((org.hibernate.query.Query) query).list();
        session.close();
        if (ticketList.size() == 0) {
            return null;
        }
        return ticketList.get(0);
    }

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