package com.java.java_8_features.joda_data_and_time;

import java.util.Calendar;
import java.util.Date;

public class CalenderPractice {

    public static void main(String[] args) {

        Calendar calendar = Calendar.getInstance();

        // Current date
        System.out.println("Current Date : " + calendar.getTime());

        // Date fields
        System.out.println("Year  : " + calendar.get(Calendar.YEAR));
        System.out.println("Month : " + (calendar.get(Calendar.MONTH) + 1));
        System.out.println("Day   : " + calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println("Hour  : " + calendar.get(Calendar.HOUR_OF_DAY));

        // Add time
        calendar.add(Calendar.DAY_OF_MONTH, 10);
        calendar.add(Calendar.HOUR, 5);
        System.out.println("After addition : " + calendar.getTime());

        // Convert Calendar to Date
        Date date = calendar.getTime();
        System.out.println("Date object : " + date);

        // Set custom date
        calendar.set(2025, Calendar.JANUARY, 1);
        System.out.println("Custom Date : " + calendar.getTime());
    }
}
