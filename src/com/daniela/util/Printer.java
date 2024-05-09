package com.daniela.util;

import com.daniela.car.Car;
import com.daniela.car.CarService;
import com.daniela.car.FuelTypeCategory;
import com.daniela.carbooking.CarBooking;
import com.daniela.carbooking.CarBookingService;
import com.daniela.user.User;
import com.daniela.user.UserService;

import java.util.UUID;

public class Printer {
    private final CarBookingService bookingService;
    private final UserService userService;
    private final CarService carService;

    public Printer(CarBookingService bookingService, UserService userService, CarService carService) {
        this.bookingService = bookingService;
        this.userService = userService;
        this.carService = carService;
    }

    public void displayAllUsers() {
        for (User user : userService.getAllUsers()) {
            if (user != null) {
                System.out.println(user);
            }
        }
    }

    public void displayAllBookings() {
        if (bookingService.getBookingCount() == 0) {
            System.out.println("There are no scheduled bookings at this time.");
        } else {
            for (CarBooking booking : bookingService.getAllBookings()) {
                if (booking != null) {
                    System.out.println(booking);
                }
            }
        }
    }

    public void displayBookingsByUser(UUID userId) {
        var userBookings = bookingService.getBookingsByUser(userId);
        int bookingCount = 0;
        for (CarBooking userBooking : userBookings) {
            if (userBooking != null) {
                bookingCount++;
                System.out.println(userBooking);
            }
        }

        if (bookingCount == 0) {
            var user = userService.getUserById(userId);
            if (user == null) {
                System.out.println("❌ User does not exist.");
            } else {
                System.out.println("❌ User " + user + " has no cars booked.");
            }
        }
    }

    public void displayAvailableCars(FuelTypeCategory fuelTypeCategory) {
        for (Car car : carService.getAvailableCars(fuelTypeCategory)) {
            if (car != null) {
                System.out.println(car);
            }
        }
    }
}
