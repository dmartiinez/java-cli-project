package com.daniela.carbooking;

import com.daniela.car.CarService;
import com.daniela.user.UserService;

import java.util.UUID;

public class CarBookingService {
    private CarBookingDao carBookingDao = new CarBookingDao();

    public void addBooking(CarBooking booking) {
        if (booking == null) {
            return;
        }

        boolean bookingWasSuccessful = carBookingDao.saveBooking(booking);

        if (bookingWasSuccessful) {
            var userService = new UserService();
            var carService = new CarService();
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
}
