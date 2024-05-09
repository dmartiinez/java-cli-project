package com.daniela.user;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.UUID;

public class UserFileDateAccessService implements UserDao {
    @Override
    public User[] getUsers() {
        File usersFile = new File("src/com/daniela/users.csv");

        var users = new User[10];
        int currIndex = 0;
        String line;

        try{

            FileReader fileReader = new FileReader(usersFile);
            BufferedReader reader = new BufferedReader(fileReader);

           while((line = reader.readLine()) != null) {
               // parse by comma to get UUID and name
               String[] userInfo = line.split(",");
               if (userInfo.length != 2) {
                   System.out.println("Invalid number of arguments provided for line \"" + line + "\". Expecting format \"UUID, name\"");
                   continue;
               }

               // TODO: check if user ID already exists and either generate a new ID or skip adding user
               UUID userId = UUID.fromString(userInfo[0]);
               String userName = userInfo[1];
               users[currIndex++] = new User(userId, userName);
           }

           reader.close();
        } catch (IOException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        return users;
    }
}
