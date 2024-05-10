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
                // shift the values after the index to be deleted down until the end of the array
                for (int j = i; j < availableCars.length; j++) {
                    if (j + 1 < availableCars.length && availableCars[j+1] != null) {
                        availableCars[i] = availableCars[j+1];
                        availableCars[j + 1] = null;
                        ++i;
                    } else {
                        // if the next element equals null (break out early) or doesn't exist, then we have reached the end of the array
                        // and we can just delete value without shifting anymore.
                        availableCars[i] = null;
                        break;
                    }
                }
                break;
            }
        }
    }
}
