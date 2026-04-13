package com.company.eems.util;

import java.util.Scanner;

/**
 * Utility class for console input handling.
 */
public final class ConsoleUtil {

    private static final Scanner SCANNER = new Scanner(System.in);

    private ConsoleUtil() {}

    /**
     * Reads integer input from console.
     * @param message prompt message
     * @return integer value
     */
    public static int readInt(String message) {
        System.out.print(message);
        while (!SCANNER.hasNextInt()) {
            System.out.print("Invalid input. Enter number: ");
            SCANNER.next();
        }
        return SCANNER.nextInt();
    }

    /**
     * Reads double input from console.
     * @param message prompt message
     * @return double value
     */
    public static double readDouble(String message) {
        System.out.print(message);
        while (!SCANNER.hasNextDouble()) {
            System.out.print("Invalid input. Enter decimal number: ");
            SCANNER.next();
        }
        return SCANNER.nextDouble();
    }

    /**
     * Reads string input from console.
     * @param message prompt message
     * @return string value
     */
    public static String readString(String message) {
        System.out.print(message);
        SCANNER.nextLine(); // consume leftover
        return SCANNER.nextLine();
    }
}
