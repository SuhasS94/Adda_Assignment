package com.adda.asinmt.controller;

import com.adda.asinmt.service.FacilityBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
//2012-12-25 01:00
@Controller
public class FacilityBookingController implements CommandLineRunner {


    @Autowired
    private FacilityBookingService facilityBookingService;


//  private final Map<String, Facility> facilities;
//
//    @Autowired
//    public FacilityBookingController() {
//        // Initialize facilities
//        this.facilities = new HashMap<>();
//        this.facilities.put("Clubhouse", new Facility("Clubhouse", 100, 500));
//        this.facilities.put("Tennis", new Facility("Tennis", 50));
//    }

    @Override
    public void run(String... args) {
        startBookingCLI();
    }

    public void startBookingCLI() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Facility Booking System!");
        while (true) {
            System.out.println("Enter facility name (Clubhouse / Tennis Court):");
            String facilityName = scanner.nextLine();
            if (!facilityBookingService.facilities.containsKey(facilityName)) {
                System.out.println("Invalid facility name. Please try again.");
                continue;
            }

            System.out.println("Enter start date and time (yyyy-MM-dd HH:mm):");
            LocalDateTime startDateTime = LocalDateTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

            System.out.println("Enter end date and time (yyyy-MM-dd HH:mm):");
            LocalDateTime endDateTime = LocalDateTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

            String result = facilityBookingService.bookFacility(facilityName, startDateTime, endDateTime);
            System.out.println("Booking result: " + result);

            System.out.println("Do you want to make another booking? (Yes to book/Any to stop process)");
            String choice = scanner.nextLine();
            if (!choice.equalsIgnoreCase("yes")) {
                System.out.println("THANK YOU");
                break;
            }
        }
        scanner.close();
        System.exit(0);
    }

//    private String bookFacility(String facilityName, LocalDateTime startDateTime, LocalDateTime endDateTime) {
//        Facility facility = facilities.get(facilityName);
//        if (facility == null) {
//            return "Facility not found";
//        }
//
//        // Check if the facility is already booked for the given time slot
//        if (isBooked(startDateTime, endDateTime)) {
//            return "Booking Failed, Already Booked";
//        }
//
//        // Book the facility
//        double amount = calculateAmount(startDateTime, endDateTime);
//        .markAsBooked(startDateTime, endDateTime);
//        return "Booked, Rs. " + amount;
//    }

    // Facility class representing each facility
//    private static class Facility {
//        private final String name;
//        private final double hourRate;
//        private double peakHourRate;
//        private final Map<LocalDateTime, LocalDateTime> bookings;
//
//        public Facility(String name, double hourRate) {
//            this.name = name;
//            this.hourRate = hourRate;
//            this.peakHourRate = hourRate;
//            this.bookings = new HashMap<>();
//        }
//
//        public Facility(String name, double hourRate, double peakHourRate) {
//            this(name, hourRate);
//            this.peakHourRate = peakHourRate;
//        }

//        public boolean isBooked(LocalDateTime startDateTime, LocalDateTime endDateTime) {
//            for (Map.Entry<LocalDateTime, LocalDateTime> entry : bookings.entrySet()) {
//                LocalDateTime bookedStart = entry.getKey();
//                LocalDateTime bookedEnd = entry.getValue();
//                if (startDateTime.isBefore(bookedEnd) && endDateTime.isAfter(bookedStart)) {
//                    return true;
//                }
//            }
//            return false;
//        }
//
//        public void markAsBooked(LocalDateTime startDateTime, LocalDateTime endDateTime) {
//            bookings.put(startDateTime, endDateTime);
//        }
//
//        public double calculateAmount(LocalDateTime startDateTime, LocalDateTime endDateTime) {
//            if (startDateTime.isAfter(endDateTime)) {
//                throw new IllegalArgumentException("Start date/time must be before end date/time.");
//            }
//
//            double amount = 0;
//            while (startDateTime.isBefore(endDateTime)) {
//                if (startDateTime.getHour() >= 10 && startDateTime.getHour() < 16) {
//                    amount += peakHourRate;
//                } else {
//                    amount += hourRate;
//                }
//                startDateTime = startDateTime.plusHours(1);
//            }
//            return amount;
//        }
//    }
}
