package com.java.java_8_features.joda_data_and_time;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class TimeZonePractices {

    public static void main(String[] args) {

        // Current system timezone
        ZoneId systemZone = ZoneId.systemDefault();
        System.out.println("System Zone : " + systemZone);

        // Specific zones
        ZoneId india = ZoneId.of("Asia/Kolkata");
        ZoneId london = ZoneId.of("Europe/London");

        ZonedDateTime indiaTime = ZonedDateTime.now(india);
        ZonedDateTime londonTime = ZonedDateTime.now(london);

        System.out.println("India Time  : " + indiaTime);
        System.out.println("London Time : " + londonTime);

        // Formatting
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm z");
        System.out.println("Formatted India Time : " +
                indiaTime.format(formatter));

        // Available zones 
        Set<String> zones = ZoneId.getAvailableZoneIds();
        zones.stream().limit(5).forEach(System.out::println);
    }
}

