package com.daniela.carbooking;


import com.daniela.car.Car;
import com.daniela.user.User;

import java.time.LocalDateTime;
import java.util.UUID;

public class CarBooking {
    private UUID userId;
    private String carRegistrationNum;
    private LocalDateTime bookingTime;
    private boolean isCanceled;

    public CarBooking(UUID userId, String carRegistrationNum) {
        this.userId = userId;
        this.carRegistrationNum = carRegistrationNum;
        this.isCanceled = false;
        this.bookingTime = LocalDateTime.now();
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getCarRegistrationNum() {
        return carRegistrationNum;
    }

    public void setCarRegistrationNum(String carRegistrationNum) {
        this.carRegistrationNum = carRegistrationNum;
    }

    public void setCanceled(boolean canceled) {
        isCanceled = canceled;
    }

    @Override
    public String toString() {
        return "CarBooking{" +
                "userId=" + userId +
                ", carRegistrationNum='" + carRegistrationNum + '\'' +
                ", bookingTime=" + bookingTime +
                ", isCanceled=" + isCanceled +
                '}';
    }
}
