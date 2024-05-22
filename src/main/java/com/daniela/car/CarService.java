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
        List<Car> electricCars = cars.stream().filter(car -> car.isElectric()).toList();


        return electricCars;
    }
}