package com.daniela.user;

import java.util.List;
import java.util.UUID;

public class UserService {
    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getAllUsers() {
        return userDao.getUsers();
    }

    public User getUserById(UUID id) {
        var users = getAllUsers();
        for (User user : users) {
            if(user != null && user.getId().equals(id)) {
                return user;
            }
        }

        return null;
    }
}
