package com.daniela.user;

import java.util.UUID;

public class UserDoa {
    private static User[] users;

    static {
        users = new User[] {
                new User(UUID.fromString("89583aa9-812c-44a2-bfbf-b0db7fec81bf"), "Daniela Martinez"),
                new User(UUID.fromString("3fe00b16-0d47-45ea-b7ae-0d3a67421beb"), "Manuel Chavez"),
                new User(UUID.fromString("14c4eb37-09b1-445c-9368-bca108cf3dff"), "Luca Chavez"),
                new User(UUID.fromString("13b10708-f1a2-456e-afad-d97492f309ba"), "Alexa Thomas"),
                new User(UUID.fromString("e58eebd5-1860-48d2-80a8-a08ba436742a"), "Olivia Chavez")
        };
    }

    public User[] getUsers() {
        return users;
    }


}
