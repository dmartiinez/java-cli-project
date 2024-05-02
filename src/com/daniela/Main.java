package com.daniela;

import com.daniela.car.Car;
import com.daniela.car.CarService;
import com.daniela.carbooking.CarBooking;
import com.daniela.carbooking.CarBookingService;
import com.daniela.user.UserService;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        var options = """
            1️⃣ - Book Car
            2️⃣ - View All User Booked Cars
            3️⃣ - View All Bookings
            4️⃣ - View Available Cars
            5️⃣ - View Available Electric Cars
            6️⃣ - View all users
            7️⃣ - Exit
        """;
        System.out.println();
        System.out.println(options);
        //Prompt for user input
        try {
            Scanner scanner = new Scanner(System.in);
            int userInput = scanner.nextInt();
            scanner.nextLine();
            CarService carService = new CarService();
            CarBookingService bookingService = new CarBookingService();
            UserService userService = new UserService();

            while (userInput != 7) {
                if (userInput < 1 || userInput > 7) {
                    System.out.println("❌ " + userInput + " is not a valid option.");
                } else {

                    switch (userInput) {
                        case 1:
                            carService.displayAvailableCars();
                            System.out.print("➡\uFE0F Enter Car Registration Number: ");
                            String registrationNum = scanner.nextLine();
                            userService.displayAllUsers();
                            System.out.print("➡\uFE0F Enter User ID: ");
                            UUID userId = UUID.fromString(scanner.nextLine());

                            bookingService.addBooking(new CarBooking(userId, registrationNum));

                            break;
                        case 2:
                            userService.displayAllUsers();
                            System.out.print("➡\uFE0F Enter User ID: ");
                            UUID id = UUID.fromString(scanner.nextLine());
                            var userBookings = bookingService.getBookingsByUser(id);
                            int bookingCount = 0;
                            for (CarBooking userBooking : userBookings) {
                                if (userBooking != null) {
                                    bookingCount++;
                                    System.out.println(userBooking);
                                }
                            }

                            if (bookingCount == 0) {
                                var user = userService.getUserById(id);
                                if (user == null) {
                                    System.out.println("❌ User does not exist.");
                                } else {
                                    System.out.println("❌ User " + user + " has no cars booked.");
                                }
                            }
                            break;
                        case 3:
                            var allBookings = bookingService.getAllBookings();

                            if (bookingService.getBookingCount() == 0) {
                                System.out.println("There are no scheduled bookings at this time.");
                            } else {
                                for (CarBooking booking : allBookings) {
                                    if (booking != null) {
                                        System.out.println(booking);
                                    }
                                }
                            }
                            break;
                        case 4:
                            carService.displayAvailableCars();
                            break;
                        case 5:
                            var availableElectricCars = carService.getAvailableCars(true);
                            for (Car car : availableElectricCars) {
                                if (car != null) {
                                    System.out.println(car);
                                }
                            }
                            break;
                        case 6:
                            userService.displayAllUsers();
                            break;
                        default:
                            break;
                    }
                }

                System.out.println();
                System.out.println(options);
                userInput = scanner.nextInt();
                scanner.nextLine();
            }

        } catch (InputMismatchException e) {
            System.out.println("❌ input must be an integer between 1 and 7.");
        }
    }
}