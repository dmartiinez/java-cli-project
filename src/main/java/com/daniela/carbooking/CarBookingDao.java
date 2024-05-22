package com.daniela.carbooking;

import java.util.ArrayList;
import java.util.List;

public class CarBookingDao {
    private static List<CarBooking> carBookings;

    static {
        carBookings = new ArrayList<>();
    }

    public void saveBooking(CarBooking booking) {
        carBookings.add(booking);
    }

    public List<CarBooking> getCarBookings() {
        return carBookings;
    }
}
