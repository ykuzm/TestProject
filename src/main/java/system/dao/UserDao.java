package system.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import system.model.Station;
import system.model.User;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class UserDao {

    private SessionFactory sessionFactory;

    public UserDao() { }

    public SessionFactory getSessionFactory() { return sessionFactory; }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Method for getting all users from DB
     *
     * @return full list of users from DB
     */
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        Session session;
        try {
        session = this.sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        List<User> userList = session.createQuery("from User" ).list();
        session.close();
        return userList;
    }

    /**
     * Method for getting user by id
     *
     * @param id id
     * @return User with selected id, null if there is no User in DB with this id
     */
    @SuppressWarnings("unchecked")
    public User getUserById(int id) {
        Session session;
        try {
            session = this.sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        Query query = session.createQuery("from User p where p.id=:id");
        query.setParameter("id", id);
        List<User> userList = ((org.hibernate.query.Query) query).list();
        session.close();
        if (userList.size() == 0) {
            return null;
        }
        return userList.get(0);
    }

    /**
     * Method for getting user by login
     *
     * @param login login
     * @return User with selected login, null if there is no User in DB with this login
     */
    @SuppressWarnings("unchecked")
    public User getUserByLogin(String login) {
        Session session;
        try {
            session = this.sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        Query query = session.createQuery("from User p where p.login=:login");
        query.setParameter("login", login);
        List<User> userList = ((org.hibernate.query.Query) query).list();
        session.close();
        if (userList.size() == 0) {
            return null;
        }
        return userList.get(0);
    }

    /**
     * Method for adding User in DB
     *
     * @param user user
     */
    public void addUser(User user) {
        Session session;
        try {
            session = this.sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        Transaction transaction = session.beginTransaction();
        session.persist(user);
        transaction.commit();
        session.close();
    }

}
