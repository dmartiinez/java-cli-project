package com.daniela;

import com.daniela.car.CarDao;
import com.daniela.car.CarService;
import com.daniela.car.FuelTypeCategory;
import com.daniela.carbooking.CarBooking;
import com.daniela.carbooking.CarBookingDao;
import com.daniela.carbooking.CarBookingService;
import com.daniela.user.UserDao;
import com.daniela.user.UserFileDateAccessService;
import com.daniela.user.UserService;
import com.daniela.util.Printer;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        var exceptionCaught = false;
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

        CarDao carDao = new CarDao();
        CarBookingDao carBookingDao = new CarBookingDao();
        UserDao userDao = new UserFileDateAccessService();
        CarService carService = new CarService(carDao);
        UserService userService = new UserService(userDao);
        CarBookingService bookingService = new CarBookingService(carBookingDao, userService, carService);
        Printer printer = new Printer(bookingService, userService, carService);

        try {
            //Prompt for user input
            Scanner scanner = new Scanner(System.in);
            int userInput = scanner.nextInt();
            scanner.nextLine();

            while (userInput != 7) {
                if (userInput < 1 || userInput > 7) {
                    System.out.println("❌ " + userInput + " is not a valid option.");
                } else {
                    switch (userInput) {
                        case 1:
                            printer.displayAvailableCars(FuelTypeCategory.ALL);
                            System.out.print("➡\uFE0F Enter Car Registration Number: ");
                            String registrationNum = scanner.nextLine();
                            printer.displayAllUsers();
                            System.out.print("➡\uFE0F Enter User ID: ");
                            UUID userId = UUID.fromString(scanner.nextLine());

                            bookingService.addBooking(new CarBooking(userId, registrationNum));
                            break;
                        case 2:
                            printer.displayAllUsers();
                            System.out.print("➡\uFE0F Enter User ID: ");
                            UUID id = UUID.fromString(scanner.nextLine());

                            printer.displayBookingsByUser(id);
                            break;
                        case 3:
                            printer.displayAllBookings();
                            break;
                        case 4:
                            printer.displayAvailableCars(FuelTypeCategory.ALL);
                            break;
                        case 5:
                            printer.displayAvailableCars(FuelTypeCategory.ELECTRIC);
                            break;
                        case 6:
                            printer.displayAllUsers();
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

            scanner.close();

        } catch (InputMismatchException e) {
            System.out.println("❌ " + e.getMessage());
            exceptionCaught = true;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            exceptionCaught = true;
        }

        if (exceptionCaught) {
            Main.main(null);
        }
    }
}