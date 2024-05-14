package com.daniela.carbooking;

import com.daniela.car.Car;
import com.daniela.car.CarService;
import com.daniela.user.User;
import com.daniela.user.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class CarBookingService {
    private final CarBookingDao carBookingDao;
    private final CarService carService;

    public CarBookingService(CarBookingDao carBookingDao, CarService carService) {
        this.carBookingDao = carBookingDao;
        this.carService = carService;
    }

    public UUID bookCar(User user, String registrationNumber) {
        List<Car> availableCars = getAllAvailableCars();

        if (availableCars.isEmpty()) {
            throw new IllegalStateException("❌ Car with registration number " + registrationNumber + " is not available");
        }

        for (Car car: availableCars) {
            if (car != null && car.getRegistrationNum().equals(registrationNumber)) {
                UUID bookingId = UUID.randomUUID();
                carBookingDao.saveBooking(
                        new CarBooking(bookingId, user, car, LocalDateTime.now(), false)
                );
                return bookingId;
            }
        }

        throw new IllegalStateException("❌ Car with registration number " + registrationNumber + " is not available");
    }

    public List<CarBooking> getAllBookings() {
        return carBookingDao.getCarBookings();
    }


    public List<Car> getBookingsByUser(UUID userId) {
        List<CarBooking> currentBookings = carBookingDao.getCarBookings();
        List<Car> userCars = new ArrayList<>();

        for (CarBooking currentBooking : currentBookings) {
            if (currentBooking != null && currentBooking.getUser().getId().equals(userId)) {
                userCars.add(currentBooking.getCar());
            }
        }

        return userCars;
    }

    public List<Car> getAllAvailableCars() {
        return getCars(carService.getAllAvailableCars());
    }

    public List<Car> getAvailableElectricCars() {
        return getCars(carService.getAvailableElectricCars());
    }

    public List<Car> getCars(List<Car> cars) {
        if (cars.isEmpty()) {
            Collections.emptyList();
        }

        List<CarBooking> carBookings = carBookingDao.getCarBookings();

        // no cars are booked so they are all available
        if (carBookings.isEmpty()) {
            return cars;
        }

        List<Car> availableCars = new ArrayList<>();

        for (Car car : cars) {
            boolean carIsBooked= false;
            for (CarBooking carBooking : carBookings) {
                if (carBooking != null && carBooking.getCar().equals(car)) {
                    carIsBooked = true;
                    break;
                }
            }

            if (!carIsBooked) {
                availableCars.add(car);
            }
        }

        return availableCars;
    }
}
