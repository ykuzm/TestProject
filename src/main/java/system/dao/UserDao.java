package system.dao;

import org.springframework.stereotype.Repository;
import system.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class UserDao {

    private List<User> users;

    {
        User[] userArray = new User[] {new User("admin", "admin"),
            new User("user1", "user1")};
        users = new ArrayList<User>(Arrays.asList(userArray));
    }

    public List<User> getAllUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }

}
