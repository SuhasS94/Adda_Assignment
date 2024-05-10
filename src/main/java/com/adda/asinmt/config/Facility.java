package com.adda.asinmt.config;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

// Facility class representing each facility

@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Facility {
    private final String name;
    public final double hourRate;
    public double peakHourRate;
    public final Map<LocalDateTime, LocalDateTime> bookings;

    public Facility(String name, double hourRate) {
        this.name = name;
        this.hourRate = hourRate;
        this.peakHourRate = hourRate;
        this.bookings = new HashMap<>();
    }

    public Facility(String name, double hourRate, double peakHourRate) {
        this(name, hourRate);
        this.peakHourRate = peakHourRate;
    }

}