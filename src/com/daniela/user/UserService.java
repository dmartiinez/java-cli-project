package com.daniela.user;

import java.util.UUID;

public class UserService {
    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User[] getAllUsers() {
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

    public void displayAllUsers() {
        var allUsers = getAllUsers();
        for (User user : allUsers) {
            if (user != null) {
                System.out.println(user);
            }
        }
    }

    // checks if the given reg number is valid/available
    public boolean isValidUser(UUID userId) {
        var users = this.getAllUsers();

        for (User user : users) {
            if (user != null && user.getId().equals(userId)) {
                return true;
            }
        }

        return false;
    }
}
