package com.java.java_8_features.stream;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class GroupingByMethodPractice {

    private static final Logger LOGGER =
            Logger.getLogger(GroupingByMethodPractice.class.getName());

    public static void main(String[] args) {
        LOGGER.info("Starting main()");
        Scanner scanner = new Scanner(System.in);
        GroupingByMethodPractice collect = new GroupingByMethodPractice();

        collect.groupByLength(scanner);
        collect.groupByEvenOdd(scanner);
        collect.groupByFirstCharacter(scanner);
        collect.groupByCounting(scanner);
        collect.groupByMapping(scanner);

        scanner.close();
    }

    /**
     * demonstrate groupingBy()
     * group strings by length
     */
    private void groupByLength(Scanner scanner) {
        LOGGER.info("Starting groupByLength()");
        List<String> list = readStringList(scanner);

        Map<Integer, List<String>> result = list.stream()
                .collect(Collectors.groupingBy(String::length));

        LOGGER.info("Grouped by length : " + result);
        System.out.println();
    }

    /**
     * demonstrate groupingBy()
     * group integers into EVEN and ODD
     */
    private void groupByEvenOdd(Scanner scanner) {
        LOGGER.info("Starting groupByEvenOdd()");
        List<Integer> numbers = readIntegerList(scanner);

        Map<String, List<Integer>> result = numbers.stream()
                .collect(Collectors.groupingBy(
                        n -> n % 2 == 0 ? "EVEN" : "ODD"
                ));

        LOGGER.info("Grouped by EVEN / ODD : " + result);
        System.out.println();
    }

    /**
     * demonstrate groupingBy()
     * group strings by first character
     */
    private void groupByFirstCharacter(Scanner scanner) {
        LOGGER.info("Starting groupByFirstCharacter()");
        List<String> list = readStringList(scanner);

        Map<Character, List<String>> result = list.stream()
                .collect(Collectors.groupingBy(
                        s -> s.charAt(0)
                ));

        LOGGER.info("Grouped by first character : " + result);
        System.out.println();
    }

    /**
     * demonstrate groupingBy() with counting()
     * count elements in each group
     */
    private void groupByCounting(Scanner scanner) {
        LOGGER.info("Starting groupByCounting()");
        List<String> list = readStringList(scanner);

        Map<Integer, Long> result = list.stream()
                .collect(Collectors.groupingBy(
                        String::length,
                        Collectors.counting()
                ));

        LOGGER.info("Count by length : " + result);
        System.out.println();
    }

    /**
     * demonstrate groupingBy() with mapping()
     * group and transform values
     */
    private void groupByMapping(Scanner scanner) {
        LOGGER.info("Starting groupByMapping()");
        List<String> list = readStringList(scanner);

        Map<Integer, List<String>> result = list.stream()
                .collect(Collectors.groupingBy(
                        String::length,
                        Collectors.mapping(String::toUpperCase, Collectors.toList())
                ));

        LOGGER.info("Grouped & mapped to uppercase : " + result);
        System.out.println();
    }

    private List<Integer> readIntegerList(Scanner scanner) {
        LOGGER.info("Starting readIntegerList()");
        System.out.print("How many numbers ? ");
        int size = scanner.nextInt();

        List<Integer> list = new ArrayList<>();
        System.out.print("Enter numbers : ");
        for (int i = 0; i < size; i++) {
            list.add(scanner.nextInt());
        }

        scanner.nextLine();
        return list;
    }

    private List<String> readStringList(Scanner scanner) {
        LOGGER.info("Starting readStringList()");
        System.out.print("How many Strings ? ");
        int size = scanner.nextInt();
        scanner.nextLine();

        List<String> list = new ArrayList<>();
        System.out.println("Enter strings : ");
        for (int i = 0; i < size; i++) {
            list.add(scanner.nextLine());
        }

        return list;
    }
}

