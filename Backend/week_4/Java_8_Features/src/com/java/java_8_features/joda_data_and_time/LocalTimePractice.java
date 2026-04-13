package com.java.java_8_features.joda_data_and_time;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class LocalTimePractice {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        LocalTime now = LocalTime.now();
        System.out.println("Current Time : " + now);

        // Time parts
        System.out.println("Hour   : " + now.getHour());
        System.out.println("Minute : " + now.getMinute());
        System.out.println("Second : " + now.getSecond());

        // basic data required
        System.out.println("Time 1 information ");
        System.out.print("Enter hour for time 1 : ");
        int hour1 = scanner.nextInt();
        System.out.print("Enter minute for time 1 : ");
        int minute1 = scanner.nextInt();
        LocalTime officeStart = LocalTime.of(hour1, minute1);

        System.out.println("Time 1 information ");
        System.out.print("Enter hour for time 2 : ");
        int hour2 = scanner.nextInt();
        System.out.print("Enter minute for time 2 : ");
        int minute2 = scanner.nextInt();
        LocalTime officeEnd = LocalTime.of(hour2, minute2);

        // Comparisons
        System.out.println("Before office start? " +
                now.isBefore(officeStart));
        System.out.println("After office end? " +
                now.isAfter(officeEnd));

        // Time calculations
        System.out.println("After 90 minutes : " +
                now.plusMinutes(90));
        System.out.println("Minus 45 seconds : " +
                now.minusSeconds(45));

    }
}

