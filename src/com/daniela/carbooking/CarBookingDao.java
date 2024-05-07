package com.daniela.carbooking;

public class CarBookingDao {
    private static CarBooking[] carBookings;
    private static final int MAX_NUM_BOOKINGS = 3;
    private static int nextAvailableBookingSlot = 0;

    static {
        carBookings = new CarBooking[MAX_NUM_BOOKINGS];
    }

    public boolean saveBooking(CarBooking booking) {
        if (nextAvailableBookingSlot == MAX_NUM_BOOKINGS) {
            System.out.println("Sorry, we are not taking any more bookings at this time.");
            return false;
        }

        carBookings[nextAvailableBookingSlot++] = booking;

        return true;

    }

    public CarBooking[] getCarBookings() {
        return carBookings;
    }

    public static int getNextAvailableBookingSlot() {
        return nextAvailableBookingSlot;
    }
}
