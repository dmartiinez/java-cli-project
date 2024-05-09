package com.daniela.car;

public class CarService {
    private CarDao carDao;

    public CarService(CarDao carDao) {
        this.carDao = carDao;
    }

    public Car[] getAvailableCars(boolean getElectricOnly) {
        var availableCars = carDao.getCars();
        if (!getElectricOnly) {
            return availableCars;
        }

        var electricCars = new Car[availableCars.length];
        int currentIndex = 0;
        for (Car availableCar : availableCars) {
            if (availableCar != null && availableCar.isElectric()) {
                electricCars[currentIndex++] = availableCar;
            }
        }

        return electricCars;
    }

    public void displayAvailableCars() {
        var availableCars = getAvailableCars(false);
        for (Car car : availableCars) {
            if (car != null) {
                System.out.println(car);
            }
        }
    }

    public void removeCar(String registrationNum) {
        var availableCars = getAvailableCars(false);
        for (int i = 0; i < availableCars.length; i++) {
            if (availableCars[i] != null && availableCars[i].getRegistrationNum().equals(registrationNum)) {
                availableCars[i] = null;
                break;
            }
        }
    }

    public void displayElectricCars() {
        var availableElectricCars = this.getAvailableCars(true);
        for (Car car : availableElectricCars) {
            if (car != null) {
                System.out.println(car);
            }
        }
    }

    // checks if the given reg number is valid/available
    public boolean isValidRegNumber(String regNumber) {
        var availableCars = this.getAvailableCars(false);

        for (Car availableCar : availableCars) {
            if (availableCar != null && availableCar.getRegistrationNum().equals(regNumber)) {
                return true;
            }
        }

        return false;
    }
}
