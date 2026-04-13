package com.java.java_8_features.stream;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class PartitioningByMethodPractices {

    private static final Logger LOGGER =
            Logger.getLogger(PartitioningByMethodPractices.class.getName());

    public static void main(String[] args) {
        LOGGER.info("Starting main()");
        Scanner scanner = new Scanner(System.in);
        PartitioningByMethodPractices collect = new PartitioningByMethodPractices();

        collect.partitionEvenOdd(scanner);
        collect.partitionByLength(scanner);
        collect.partitionWithCounting(scanner);
        collect.partitionWithMapping(scanner);

        scanner.close();
    }

    /**
     * demonstrate partitioningBy()
     * partition numbers into EVEN and ODD
     */
    private void partitionEvenOdd(Scanner scanner) {
        LOGGER.info("Starting partitionEvenOdd()");
        List<Integer> numbers = readIntegerList(scanner);

        Map<Boolean, List<Integer>> result = numbers.stream()
                .collect(Collectors.partitioningBy(
                        n -> n % 2 == 0
                ));

        LOGGER.info("Partitioned EVEN(true) / ODD(false) : " + result);
        System.out.println();
    }

    /**
     * demonstrate partitioningBy()
     * partition strings by length > 5
     */
    private void partitionByLength(Scanner scanner) {
        LOGGER.info("Starting partitionByLength()");
        List<String> list = readStringList(scanner);

        Map<Boolean, List<String>> result = list.stream()
                .collect(Collectors.partitioningBy(
                        s -> s.length() > 5
                ));

        LOGGER.info("Partitioned by length > 5 : " + result);
        System.out.println();
    }

    /**
     * demonstrate partitioningBy() with counting()
     */
    private void partitionWithCounting(Scanner scanner) {
        LOGGER.info("Starting partitionWithCounting()");
        List<Integer> numbers = readIntegerList(scanner);

        Map<Boolean, Long> result = numbers.stream()
                .collect(Collectors.partitioningBy(
                        n -> n > 10,
                        Collectors.counting()
                ));

        LOGGER.info("Count partitioned by > 10 : " + result);
        System.out.println();
    }

    /**
     * demonstrate partitioningBy() with mapping()
     */
    private void partitionWithMapping(Scanner scanner) {
        LOGGER.info("Starting partitionWithMapping()");
        List<String> list = readStringList(scanner);

        Map<Boolean, List<String>> result = list.stream()
                .collect(Collectors.partitioningBy(
                        s -> s.startsWith("A"),
                        Collectors.mapping(String::toUpperCase, Collectors.toList())
                ));

        LOGGER.info("Partitioned & mapped to uppercase : " + result);
        System.out.println();
    }

    private List<Integer> readIntegerList(Scanner scanner) {
        //LOGGER.info("Starting readIntegerList()");
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
        //LOGGER.info("Starting readStringList()");
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

