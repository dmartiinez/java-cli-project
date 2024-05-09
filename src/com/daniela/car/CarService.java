package com.daniela.car;

public class CarService {
    private CarDao carDao;

    public CarService(CarDao carDao) {
        this.carDao = carDao;
    }

    public Car[] getAvailableCars(FuelTypeCategory fuelType) {
        var availableCars = carDao.getCars();

        switch (fuelType) {
            case ELECTRIC:
                var electricCars = new Car[availableCars.length];
                int currentIndex = 0;
                for (Car availableCar : availableCars) {
                    if (availableCar != null && availableCar.isElectric()) {
                        electricCars[currentIndex++] = availableCar;
                    }
                }

                return electricCars;
            case ALL:
            default:
                return availableCars;

        }
    }

    public void removeCar(String registrationNum) {
        var availableCars = getAvailableCars(FuelTypeCategory.ALL);
        for (int i = 0; i < availableCars.length; i++) {
            if (availableCars[i] != null && availableCars[i].getRegistrationNum().equals(registrationNum)) {
                availableCars[i] = null;
                break;
            }
        }
    }
}
