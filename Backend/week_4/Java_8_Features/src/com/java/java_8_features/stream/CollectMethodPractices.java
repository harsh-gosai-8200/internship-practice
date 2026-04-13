package com.java.java_8_features.stream;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class CollectMethodPractices {

    private static final Logger LOGGER = Logger.getLogger(CollectMethodPractices.class.getName());

    public static void main(String[] args) {
        LOGGER.info("Starting main()");
        Scanner scanner = new Scanner(System.in);
        CollectMethodPractices collect = new CollectMethodPractices();

        collect.collectToListMethod(scanner);
        collect.collectorToSetMethod(scanner);
        collect.collectToMapMethod(scanner);
        collect.collectGroupingBy(scanner);
        collect.counting(scanner);
        collect.joining(scanner);
        collect.averaging(scanner);

        scanner.close();
        //LOGGER.info("Exiting main()");
    }

    /**
     * demonstrate use of averaging() method of stream Collectors
     * get list of integer and calculate average of integer
     * @param scanner
     */
    private void averaging(Scanner scanner) {
        LOGGER.info("Starting averaging()");
        List<Integer> numbers = readInegerList(scanner);

        double average = numbers.stream()
                .collect(Collectors.averagingInt(Integer::intValue));
        //System.out.println("Even numbers : " + average);
        LOGGER.info("Average of values : " + average);
        //LOGGER.info("Exiting averaging()");
        System.out.println();
    }

    /**
     * demonstrate use of joining() method of stream Collectors
     * join strings into a single string
     * @param scanner
     */
    private void joining(Scanner scanner) {
        LOGGER.info("Starting joining()");
        List<String> list = readStringList(scanner);

        String joind = list.stream()
                .collect(Collectors.joining());

        //System.out.println("Grouped by length : " + joind);
        LOGGER.info("Joined string : " + joind);
        //LOGGER.info("Exiting joining()");
        System.out.println();
    }

    /**
     * demonstrate use of counting() method of stream Collectors
     * get list of integer and counts element by matching condition
     * @param scanner
     */
    private void counting(Scanner scanner) {
        LOGGER.info("Starting counting()");
        List<Integer> numbers = readInegerList(scanner);

        long count = numbers.stream()
                .filter(n->n%2==0)
                .collect(Collectors.counting());
        //System.out.println("Even numbers : " + count);
        LOGGER.info("Even numbers : " + count);
        //LOGGER.info("Exiting counting()");
        System.out.println();
    }

    /**
     * demonstrate use of grouppingBy method of stream Collectors
     * get list of names and groupping them by length
     * @param scanner
     */
    private void collectGroupingBy(Scanner scanner) {
        LOGGER.info("Starting collectGroupingBy()");
        List<String> list = readStringList(scanner);

        Map<Integer, List<String>> groupped = list.stream()
                .collect(Collectors.groupingBy(String::length));

        //System.out.println("Grouped by length : " + groupped);
        LOGGER.info("Grouped by length : " + groupped);
        //LOGGER.info("Exiting collectGroupingBy()");
        System.out.println();
    }

    /**
     * demonstrate use of toSet method of stream Collectors
     * get map of names and convert all values into uppercase
     * @param scanner
     */
    private void collectToMapMethod(Scanner scanner) {
        LOGGER.info("Starting collectingToMapMethod()");
        Map<Integer, String> map = new HashMap<>();
        System.out.println("How many entry ? ");
        int entry = scanner.nextInt();

        for(int i = 0; i<entry; i++){
            System.out.printf("Enter id : ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Enter name : ");
            String name = scanner.nextLine();

            map.put(id, name);
        }

        Map<Integer, String> upperCaseMap = map.entrySet()
                .stream().collect(Collectors.toMap(Map.Entry::getKey,
                        e->e.getValue().toUpperCase()));

        //System.out.println("Collected map : " + upperCaseMap);
        LOGGER.info("Collected map : " + upperCaseMap);
        //LOGGER.info("Exiting collectingToMapMethod()");
        System.out.println();
    }

    /**
     * demonstrate use of toSet method of stream Collectors
     * get list of string and give unique values
     * @param scanner
     */
    private void collectorToSetMethod(Scanner scanner) {
        LOGGER.info("Starting collectingToSetMethod()");
        List<String > names = readStringList(scanner);

        Set<String> uniqueNames = names.stream()
                .collect(Collectors.toSet());

        //System.out.printf("Unique string values are : " + uniqueNames);
        LOGGER.info("Unique string values are : " + uniqueNames);
        //LOGGER.info("Exiting collectingToSetMethod()");
        System.out.println();
    }

    /**
     * demonstrate use of toList method of stream Collectors
     * get list of integer and filter values grater than given value
     * @param scanner
     */
    private void collectToListMethod(Scanner scanner) {
        LOGGER.info("Starting collectingToListMethod()");
        List<Integer> numbers = readInegerList(scanner);

        System.out.printf("Enter minimun value : ");
        int min = scanner.nextInt();
        scanner.nextLine();

        List<Integer> result = numbers.stream()
                .filter(n->n>min)
                .collect(Collectors.toList());
        //System.out.println("Filtered List : " + result);
        LOGGER.info("Filtered List : " + result);
        //LOGGER.info("Exiting collectingToListMethod()");
        System.out.println();

    }

    /**
     * Create list of Integer and return
     * @param scanner
     * @return List
     */
    private List<Integer> readInegerList(Scanner scanner) {
        LOGGER.info("Starting readIntegerList()");
        System.out.printf("How many numbers ?");
        int size = scanner.nextInt();
        System.out.printf("Enter numbers : ");

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i<size; i++){
            list.add(scanner.nextInt());
        }

        scanner.nextLine();
        //LOGGER.info("Exiting readIntegerList()");
        return list;
    }

    /**
     * Create list of String and return
     * @param scanner
     * @return List
     */
    private List<String> readStringList(Scanner scanner) {
        LOGGER.info("Starting readStringList()");
        System.out.printf("How many Strings ?");
        int size = scanner.nextInt();
        System.out.printf("Enter strings : ");

        List<String> list = new ArrayList<>();
        for (int i = 0; i<size; i++){
            list.add(scanner.nextLine());
        }

        scanner.nextLine();
        return list;
    }
}
