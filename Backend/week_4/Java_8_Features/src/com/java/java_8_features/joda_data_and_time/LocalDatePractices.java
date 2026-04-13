package com.java.java_8_features.joda_data_and_time;

import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class LocalDatePractices {

    public static void main(String[] args) {

        LocalDate today = LocalDate.now();
        Scanner scanner = new Scanner(System.in);
        System.out.println("information about birthday ");
        System.out.println("Enter year : ");
        int year = scanner.nextInt();
        System.out.println("Enter month : ");
        int month = scanner.nextInt();
        System.out.println("Enter day of month : ");
        int date = scanner.nextInt();
        LocalDate birthday = LocalDate.of(year, month, date);

        System.out.println("Today        : " + today);
        System.out.println("Birthday     : " + birthday);

        // Date parts
        System.out.println("Year         : " + today.getYear());
        System.out.println("Month        : " + today.getMonth());
        System.out.println("Day of Week  : " + today.getDayOfWeek());

        // Comparisons
        System.out.println("Is today after birthday? " +
                today.isAfter(birthday));
        System.out.println("Is leap year? " +
                today.isLeapYear());

        // Date calculations
        System.out.println("Next week    : " + today.plusWeeks(1));
        System.out.println("Last month   : " + today.minusMonths(1));

        // Difference between dates
        long daysBetween =
                ChronoUnit.DAYS.between(birthday, today);
        System.out.println("Days lived   : " + daysBetween);

        // DayOfWeek usage
        DayOfWeek day = today.getDayOfWeek();
        System.out.println("Today is weekend? " +
                (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY));
    }
}

