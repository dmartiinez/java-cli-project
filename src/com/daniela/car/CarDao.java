package com.daniela.car;

import java.util.ArrayList;
import java.util.List;

public class CarDao {
    private static List<Car> cars;

    static {
        cars = new ArrayList<>();
        cars.add(new Car("1234", 69.99, 4, false, Brand.HONDA));
        cars.add(new Car("4567", 120.00, 5, true, Brand.TESLA));
        cars.add(new Car("7890", 75.00, 4, false, Brand.CADILLAC));
        cars.add(new Car("2468", 85.00, 7, true, Brand.TOYOTA));

    }

    public List<Car> getCars() {
        return cars;
    }

    public void removeCar(String regNumber) {
        cars.removeIf(r -> r.getRegistrationNum().equals(regNumber));
    }
}
