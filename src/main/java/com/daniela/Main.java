package com.daniela;

import com.daniela.car.Car;
import com.daniela.car.CarDao;
import com.daniela.car.CarService;
import com.daniela.carbooking.CarBooking;
import com.daniela.carbooking.CarBookingDao;
import com.daniela.carbooking.CarBookingService;
import com.daniela.user.*;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {

        UserDao userDao = new UserFakerDataAccessService();
        UserService userService = new UserService(userDao);

        CarDao carDao = new CarDao();
        CarBookingDao carBookingDao = new CarBookingDao();

        CarService carService = new CarService(carDao);
        CarBookingService bookingService = new CarBookingService(carBookingDao, carService);

        Scanner scanner = new Scanner(System.in);
        boolean keepPrompting = true;

        while (keepPrompting) {
            try {
                displayMenu();
                String userInput = scanner.nextLine();

                switch (Integer.parseInt(userInput)) {
                    case 1 -> bookCar(bookingService, userService, scanner);
                    case 2 -> displayBookingsByUser(userService, bookingService, scanner);
                    case 3 -> displayAllBookings(bookingService);
                    case 4 -> displayAvailableCars(bookingService, false);
                    case 5 -> displayAvailableCars(bookingService, true);
                    case 6 -> displayAllUsers(userService);
                    case 7 -> keepPrompting = false;
                    default -> System.out.println("❌ " + userInput + " is not a valid option.");
                }
            } catch (Exception e) {
                System.out.println("❌ " + e.getMessage());
            }
        }

        scanner.close();
    }

    private static void displayAllUsers(UserService userService) {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            System.out.println("❌ No users in the system.");
            return;
        }
        for (User user : users) {
            if (user != null) {
                System.out.println(user);
            }
        }
    }

    private static void displayAllBookings(CarBookingService carBookingService) {
        List<CarBooking> bookings = carBookingService.getAllBookings();
        if (bookings.size() == 0) {
            System.out.println("There are no scheduled bookings at this time.");
        } else {
            for (CarBooking booking : bookings) {
                if (booking != null) {
                    System.out.println(booking);
                }
            }
        }
    }

    private static void displayBookingsByUser(UserService userService, CarBookingService carBookingService, Scanner scanner) {
        displayAllUsers(userService);
        System.out.print("➡\uFE0F Enter User ID: ");
        UUID id = UUID.fromString(scanner.nextLine());

        User user = userService.getUserById(id);
        if (user == null) {
            System.out.println("❌ User with id " + id + " does not exist.");
            return;
        }

        List<Car> userBookings = carBookingService.getBookingsByUser(id);

        if (userBookings.size() == 0) {
            System.out.println("❌ User " + user + " has no cars booked.");
        }

        userBookings.forEach(System.out::println);
    }

    private static void displayAvailableCars(CarBookingService carBookingService, boolean isElectric) {
        List<Car> availableCars = isElectric ? carBookingService.getAvailableElectricCars() : carBookingService.getAllAvailableCars();
        if (availableCars.isEmpty()) {
            System.out.println("❌ No rental cars available.");
            return;
        }
        for (Car car : availableCars) {
            if (car != null) {
                System.out.println(car);
            }
        }
    }

    private static void bookCar(CarBookingService carBookingService, UserService userService, Scanner scanner) {
        displayAvailableCars(carBookingService, false);
        System.out.print("➡\uFE0F Enter Car Registration Number: ");
        String registrationNum = scanner.nextLine();
        displayAllUsers(userService);
        System.out.print("➡\uFE0F Enter User ID: ");
        UUID userId = UUID.fromString(scanner.nextLine());

        try{
            User user = userService.getUserById(userId);
            if (user == null) {
                System.out.println("❌ User with id " + userId + " does not exist.");
            } else {
                UUID bookingId = carBookingService.bookCar(user, registrationNum);
                String confirmationMessage = """
                        🎉 Successfully booked car with registration number %s for user %s
                        Booking ref: %s
                        """.formatted(registrationNum, user, bookingId);
                System.out.println(confirmationMessage);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void displayMenu() {
        System.out.println();
        System.out.println("""
            1️⃣ - Book Car
            2️⃣ - View All User Booked Cars
            3️⃣ - View All Bookings
            4️⃣ - View Available Cars
            5️⃣ - View Available Electric Cars
            6️⃣ - View all users
            7️⃣ - Exit
        """);
    }
}