package com.daniela.carbooking;

import com.daniela.car.CarService;
import com.daniela.user.UserService;
import com.daniela.util.Validator;

import java.util.UUID;

public class CarBookingService {
    private final CarBookingDao carBookingDao;
    private final UserService userService;
    private final CarService carService;
    private final Validator validator;

    public CarBookingService(CarBookingDao carBookingDao, UserService userService, CarService carService) {
        this.carBookingDao = carBookingDao;
        this.userService = userService;
        this.carService = carService;
        this.validator = new Validator(carService, userService);
    }

    public void addBooking(CarBooking booking) {
        if (booking == null) {
            return;
        }

        if (!validator.isValidUser(booking.getUserId()) || !validator.isValidRegNumber(booking.getCarRegistrationNum())) {
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
}
