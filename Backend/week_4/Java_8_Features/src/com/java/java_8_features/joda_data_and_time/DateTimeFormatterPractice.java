package com.java.java_8_features.joda_data_and_time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DateTimeFormatterPractice {

    public static final Logger logger =
            Logger.getLogger(DateTimeFormatterPractice.class.getName());

    public static void main(String[] args) {

        logger.log(Level.INFO, () -> "Entering main method");

        formatLocalDate();
        formatLocalTime();
        formatLocalDateTime();
        parseDateTime();
        predefinedFormatters();
        zonedDateTimeFormatting();
        interviewPatterns();
    }

    private static void formatLocalDate() {
        logger.log(Level.INFO, () -> "Entering formatLocalDate method");

        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy");

        System.out.println("Formatted LocalDate : " + date.format(formatter));
        System.out.println();
    }

    private static void formatLocalTime() {
        logger.log(Level.INFO, () -> "Entering formatLocalTime method");

        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("HH:mm:ss");

        System.out.println("Formatted LocalTime : " + time.format(formatter));
        System.out.println();
    }

    private static void formatLocalDateTime() {
        logger.log(Level.INFO, () -> "Entering formatLocalDateTime method");

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        System.out.println("Formatted LocalDateTime : " + now.format(formatter));
        System.out.println();
    }

    private static void parseDateTime() {
        logger.log(Level.INFO, () -> "Entering parseDateTime method");

        String dateTimeStr = "25-12-2024 10:30:45";
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        LocalDateTime dateTime =
                LocalDateTime.parse(dateTimeStr, formatter);

        System.out.println("Parsed LocalDateTime : " + dateTime);
        System.out.println();
    }

    private static void predefinedFormatters() {
        logger.log(Level.INFO, () -> "Entering predefinedFormatters method");

        LocalDateTime now = LocalDateTime.now();

        System.out.println("ISO_DATE        : " +
                now.format(DateTimeFormatter.ISO_DATE));
        System.out.println("ISO_DATE_TIME   : " +
                now.format(DateTimeFormatter.ISO_DATE_TIME));
        System.out.println("ISO_LOCAL_DATE  : " +
                now.format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println();
    }

    private static void zonedDateTimeFormatting() {
        logger.log(Level.INFO, () -> "Entering zonedDateTimeFormatting method");

        ZonedDateTime zonedDateTime =
                ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss z");

        System.out.println("ZonedDateTime formatted : " +
                zonedDateTime.format(formatter));
        System.out.println();
    }

    private static void interviewPatterns() {
        logger.log(Level.INFO, () -> "Entering interviewPatterns method");

        LocalDateTime now = LocalDateTime.now();

        System.out.println("dd/MM/yyyy       : " +
                now.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        System.out.println("yyyy-MM-dd       : " +
                now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        System.out.println("hh:mm a          : " +
                now.format(DateTimeFormatter.ofPattern("hh:mm a")));
        System.out.println("EEEE, MMM dd yyyy: " +
                now.format(DateTimeFormatter.ofPattern("EEEE, MMM dd yyyy")));
        System.out.println();
    }
}
