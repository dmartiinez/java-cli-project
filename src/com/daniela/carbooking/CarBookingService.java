package com.daniela.carbooking;

import com.daniela.car.Car;
import com.daniela.car.CarService;
import com.daniela.user.UserService;

import java.util.UUID;

public class CarBookingService {
    private CarBookingDao carBookingDao = new CarBookingDao();

    public void addBooking(CarBooking booking) {
        if (booking == null) {
            return;
        }

        var userService = new UserService();
        var carService = new CarService();

        if (!userService.isValidUser(booking.getUserId()) || !carService.isValidRegNumber(booking.getCarRegistrationNum())) {
            System.out.println("❌ Booking was not successful. Please provide a valid user and available registration number.");
            return;
        }

        boolean bookingWasSuccessful = carBookingDao.saveBooking(booking);

        if (bookingWasSuccessful) {
            System.out.println("\uD83C\uDF89 Successfully booked car with registration number "
                    + booking.getCarRegistrationNum() + " for user " + userService.getUserById(booking.getUserId()));

            // remove car from available list
            carService.removeCar(booking.getCarRegistrationNum());
        }
    }

    public CarBooking[] getAllBookings() {
        return carBookingDao.getCarBookings();
    }

    public int getBookingCount() {
        return carBookingDao.getNextAvailableBookingSlot();
    }

    public CarBooking[] getBookingsByUser(UUID userId) {
        CarBooking[] currentBookings = carBookingDao.getCarBookings();
        CarBooking[] userBookings = new CarBooking[currentBookings.length];

        int currIndex = 0;
        for (CarBooking currentBooking : currentBookings) {
            if(currentBooking != null && currentBooking.getUserId().equals(userId)) {
               userBookings[currIndex++] = currentBooking;
            }
        }

        return userBookings;
    }

    public void displayAllBookings() {
        if (this.getBookingCount() == 0) {
            System.out.println("There are no scheduled bookings at this time.");
        } else {
            for (CarBooking booking : this.getAllBookings()) {
                if (booking != null) {
                    System.out.println(booking);
                }
            }
        }
    }


    public void displayBookingsByUser(UUID userId) {
        var userBookings = this.getBookingsByUser(userId);
        int bookingCount = 0;
        for (CarBooking userBooking : userBookings) {
            if (userBooking != null) {
                bookingCount++;
                System.out.println(userBooking);
            }
        }

        if (bookingCount == 0) {
            UserService userService = new UserService();
            var user = userService.getUserById(userId);
            if (user == null) {
                System.out.println("❌ User does not exist.");
            } else {
                System.out.println("❌ User " + user + " has no cars booked.");
            }
        }
    }
}
