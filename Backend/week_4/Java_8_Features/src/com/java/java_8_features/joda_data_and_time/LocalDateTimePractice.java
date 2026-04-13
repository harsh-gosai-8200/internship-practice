package com.java.java_8_features.joda_data_and_time;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class LocalDateTimePractice {

    public static void main(String[] args) {

        LocalDateTime now = LocalDateTime.now();

        //manually enter the values
        LocalDateTime meeting =
                LocalDateTime.of(2024, 12, 25, 10, 30);

        System.out.println("Now      : " + now);
        System.out.println("Meeting  : " + meeting);

        // Add / subtract
        System.out.println("After 3 hours : " + now.plusHours(3));
        System.out.println("Before 2 days : " + now.minusDays(2));

        // Difference
        long hoursBetween =
                ChronoUnit.HOURS.between(now, meeting);
        System.out.println("Hours until meeting : " + hoursBetween);

        // Formatting
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        System.out.println("Formatted time : " +
                now.format(formatter));
    }
}

