package com.daniela.car;

import java.util.Objects;

public class Car {
    private String registrationNum;
    private double rentalPricePerDay;
    private int capacity;
    private Brand brand;
    private boolean isElectric;

    public Car(String registrationNum, double rentalPricePerDay, int capacity, boolean isElectric, Brand brand) {
        this.registrationNum = registrationNum;
        this.rentalPricePerDay = rentalPricePerDay;
        this.capacity = capacity;
        this.isElectric = isElectric;
        this.brand = brand;
    }

    public String getRegistrationNum() {
        return registrationNum;
    }

    public void setRegistrationNum(String registrationNum) {
        this.registrationNum = registrationNum;
    }

    public double getRentalPricePerDay() {
        return rentalPricePerDay;
    }

    public void setRentalPricePerDay(double rentalPricePerDay) {
        this.rentalPricePerDay = rentalPricePerDay;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public boolean isElectric() {
        return isElectric;
    }

    public void setElectric(boolean electric) {
        isElectric = electric;
    }

    @Override
    public String toString() {
        return "Car{" +
                "registrationNum='" + registrationNum + '\'' +
                ", rentalPricePerDay=" + rentalPricePerDay +
                ", capacity=" + capacity +
                ", brand=" + brand +
                ", isElectric=" + isElectric +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Double.compare(rentalPricePerDay, car.rentalPricePerDay) == 0 && capacity == car.capacity && isElectric == car.isElectric && Objects.equals(registrationNum, car.registrationNum) && brand == car.brand;
    }

    @Override
    public int hashCode() {
        return Objects.hash(registrationNum, rentalPricePerDay, capacity, brand, isElectric);
    }
}
