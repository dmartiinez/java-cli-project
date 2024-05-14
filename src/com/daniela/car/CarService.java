package com.daniela.car;

import java.util.ArrayList;
import java.util.List;

public class CarService {
    private CarDao carDao;

    public CarService(CarDao carDao) {
        this.carDao = carDao;
    }

    public List<Car> getAllAvailableCars() {
        return carDao.getCars();
    }

    public List<Car> getAvailableElectricCars() {
        List<Car> cars = carDao.getCars();
        List<Car> electricCars = new ArrayList<>();
        for (Car car : cars) {
            if (car != null && car.isElectric()) {
                electricCars.add(car);
            }
        }

        return electricCars;
    }


    public void removeCar(String regNumber) {
        carDao.removeCar(regNumber);
    }
}