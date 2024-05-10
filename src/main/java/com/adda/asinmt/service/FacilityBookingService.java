package com.adda.asinmt.service;

import com.adda.asinmt.config.Facility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class FacilityBookingService {

    //@Autowired
    private Facility facility = new Facility();

    public final Map<String, Facility> facilities;
    public FacilityBookingService(Facility facility, Map<String, Facility> facilities){ this.facility = facility;
        this.facilities = facilities;
    }

    @Autowired
    public FacilityBookingService() {
        // Initialize facilities
        this.facilities = new HashMap<>();
        this.facilities.put("Clubhouse", new Facility("Clubhouse", 100, 500));
        this.facilities.put("Tennis", new Facility("Tennis", 50));
    }

    public String bookFacility(String facilityName, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        Facility facility = facilities.get(facilityName);
        if (facility == null) {
            return "Facility not found";
        }

        // Check if the facility is already booked for the given time slot
        if (isBooked(startDateTime, endDateTime, facility)) {
            return "Booking Failed, Already Booked";
        }

        // Book the facility
        double amount = calculateAmount(startDateTime, endDateTime, facility);
        markAsBooked(startDateTime, endDateTime, facility);
        return "Booked, Rs. " + amount;
    }

    public boolean isBooked(LocalDateTime startDateTime, LocalDateTime endDateTime,Facility facility) {
        for (Map.Entry<LocalDateTime, LocalDateTime> entry : facility.bookings.entrySet()) {
            LocalDateTime bookedStart = entry.getKey();
            LocalDateTime bookedEnd = entry.getValue();
            if (startDateTime.isBefore(bookedEnd) && endDateTime.isAfter(bookedStart)) {
                return true;
            }
        }
        return false;
    }

    public void markAsBooked(LocalDateTime startDateTime, LocalDateTime endDateTime, Facility facility) {
        facility.bookings.put(startDateTime, endDateTime);
    }

    public double calculateAmount(LocalDateTime startDateTime, LocalDateTime endDateTime, Facility facility) {
        if (startDateTime.isAfter(endDateTime)) {
            throw new IllegalArgumentException("Start date/time must be before end date/time.");
        }

        double amount = 0;
        while (startDateTime.isBefore(endDateTime)) {
            if (startDateTime.getHour() >= 10 && startDateTime.getHour() < 16) {
                amount += facility.peakHourRate;
            } else {
                amount += facility.hourRate;
            }
            startDateTime = startDateTime.plusHours(1);
        }
        return amount;
    }


}
