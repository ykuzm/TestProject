package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.dao.UserDao;
import system.exceptions.CantRegisterException;
import system.exceptions.NotFoundInDatabaseException;
import system.model.Ticket;
import system.model.Train;
import system.model.User;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private TrainService trainService;

    @Autowired
    private TicketService ticketService;

    public UserService() { }

    public UserDao getUserDao() { return userDao; }

    public void setUserDao(UserDao userDao) { this.userDao = userDao; }

    public TrainService getTrainService() {
        return trainService;
    }

    public void setTrainService(TrainService trainService) {
        this.trainService = trainService;
    }

    public TicketService getTicketService() {
        return ticketService;
    }

    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    /**
     * Method for getting all users from DB
     *
     * @return full list of users from DB
     */
    public List<User> getAllUsers(){ return userDao.getAllUsers(); }

    /**
     * Method for getting user by id
     *
     * @param id id
     * @return User with selected id, null if there is no User in DB with this id
     */
    public User getUserById(int id){ return userDao.getUserById(id); }

    /**
     * Method for getting user by login
     *
     * @param login login
     * @return User with selected login, null if there is no User in DB with this login
     */
    public User getUserByLogin(String login){ return userDao.getUserByLogin(login); }

    /**
     * Method for adding User in DB
     *
     * @param user user
     * @throws CantRegisterException if there is User in DB with the same login
     */
    public void addUser(User user) throws CantRegisterException {
        User user1 = userDao.getUserByLogin(user.getLogin());
        if (user1 != null) {
            throw new CantRegisterException("Sorry! But account with this login already exists. Try another one.");
        }
        userDao.addUser(user);
    }

    /**
     * Method for getting all Users, which purchased tickets on the train
     *
     * @param trainNumber train number
     * @return list of Users
     * @throws NotFoundInDatabaseException if there is no train with this number
     */
    public List<User> getUserByTrainNumber(int trainNumber) throws NotFoundInDatabaseException {
        Train train = trainService.getTrainByNumber(trainNumber);
        List<Ticket> ticketList = ticketService.getTicketByTrainId(train.getId());
        List<User> userList = new ArrayList<User>();
        for (Ticket ticket: ticketList) {
            userList.add(getUserById(ticket.getUserId()));
        }
        return userList;
    }

}
