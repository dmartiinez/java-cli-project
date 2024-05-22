package com.daniela.user;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserFakerDataAccessService implements UserDao {
    private static List<User> users;

    static {
        users = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Faker fakeUser = new Faker();
            users.add(new User(UUID.randomUUID(), fakeUser.name().fullName()));
        }
    }

    @Override
    public List<User> getUsers() {
        return users;
    }
}
