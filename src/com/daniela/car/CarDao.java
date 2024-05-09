package com.daniela.car;

public class CarDao {
    private static Car[] cars;

    static {
        cars = new Car[] {
                new Car("1234", 69.99, 4, false, Brand.HONDA),
                new Car("4567", 120.00, 5, true, Brand.TESLA),
                new Car("7890", 75.00, 4, false, Brand.CADILLAC),
                new Car("2468", 85.00, 7, true, Brand.TOYOTA)
        };
    }

    public Car[] getCars() {
        return cars;
    }
}
