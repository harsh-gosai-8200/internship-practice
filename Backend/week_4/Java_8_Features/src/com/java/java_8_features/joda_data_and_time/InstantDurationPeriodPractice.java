package com.java.java_8_features.joda_data_and_time;

import java.time.*;

public class InstantDurationPeriodPractice {

    public static void main(String[] args) throws InterruptedException {

        // Instant (timestamp)
        Instant start = Instant.now();
        Thread.sleep(1200);
        Instant end = Instant.now();

        Duration duration = Duration.between(start, end);
        System.out.println("Duration millis : " + duration.toMillis());
        System.out.println("Duration seconds: " + duration.getSeconds());

        // Duration between times
        LocalTime t1 = LocalTime.of(10, 0);
        LocalTime t2 = LocalTime.of(12, 30);
        Duration timeDiff = Duration.between(t1, t2);
        System.out.println("Hours diff : " + timeDiff.toHours());

        // Period between dates
        LocalDate startDate = LocalDate.of(2015, 1, 1);
        LocalDate endDate = LocalDate.now();

        Period period = Period.between(startDate, endDate);
        System.out.println("Period diff : " +
                period.getYears() + " Years " +
                period.getMonths() + " Months " +
                period.getDays() + " Days");

        // Adding period
        System.out.println("After 6 months : " +
                startDate.plus(Period.ofMonths(6)));
    }
}
