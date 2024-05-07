package com.daniela.user;

import com.daniela.carbooking.CarBooking;

import java.util.UUID;

public class UserService {
    private static UserDoa userDoa = new UserDoa();

    public User[] getAllUsers() {
        return userDoa.getUsers();
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
