package com.daniela.util;

import com.daniela.car.Car;
import com.daniela.car.CarService;
import com.daniela.car.FuelTypeCategory;
import com.daniela.user.User;
import com.daniela.user.UserService;

import java.util.UUID;

public class Validator {
    private final CarService carService;
    private final UserService userService;

    public Validator(CarService carService, UserService userService) {
        this.carService = carService;
        this.userService = userService;
    }

    // checks if the given reg number is valid/available
    public boolean isValidUser(UUID userId) {
        var users = userService.getAllUsers();

        for (User user : users) {
            if (user != null && user.getId().equals(userId)) {
                return true;
            }
        }

        return false;
    }

    // checks if the given reg number is valid/available
    public boolean isValidRegNumber(String regNumber) {
        var availableCars = carService.getAvailableCars(FuelTypeCategory.ALL);

        for (Car availableCar : availableCars) {
            if (availableCar != null && availableCar.getRegistrationNum().equals(regNumber)) {
                return true;
            }
        }

        return false;
    }
}
