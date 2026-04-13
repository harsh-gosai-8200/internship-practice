package com.java.java_8_features.stream;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class CollectingAndThenPractice {
    private static final Logger LOGGER = Logger.getLogger(CollectingAndThenPractice.class.getName());

    public static void main(String[] args) {
        LOGGER.info("Starting main()");
        Scanner scanner = new Scanner(System.in);
        CollectingAndThenPractice collect = new CollectingAndThenPractice();

        collect.collectingAndThenToUnmodifiableList(scanner);
        collect.collectingAndThenGetSize(scanner);
        collect.collectingAndThenGetFirstElement(scanner);

        scanner.close();
    }

    /**
     * demonstrate use of collectingAndThen()
     * collect list and return first element
     */
    private void collectingAndThenGetFirstElement(Scanner scanner) {
        LOGGER.info("Entering collectingAndThenGetFirstElement");
        List<Integer> numbers = readIntegerList(scanner);

        Optional<Integer> firstElement = numbers.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> list.stream().findFirst()
                ));

        LOGGER.info("First Element : " + firstElement.orElse(null));
        System.out.println();
    }

    /**
     * demonstrate use of collectingAndThen()
     * collect list and convert it into unmodifiable list
     */
    private void collectingAndThenToUnmodifiableList(Scanner scanner) {
        LOGGER.info("Starting collectingAndThenToUnmodifiableList()");
        List<Integer> numbers = readIntegerList(scanner);

        List<Integer> unmodifiableList = numbers.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        Collections::unmodifiableList
                ));
        LOGGER.info("Unmodifiable List : " + unmodifiableList);
        System.out.println();
    }

    /**
     * demonstrate use of collectingAndThen()
     * collect to set and return size of set
     */
    private void collectingAndThenGetSize(Scanner scanner) {
        LOGGER.info("Starting collectingAndThenGetSize");
        List<String> names = readStringList(scanner);

        int size = names.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toSet(),
                        Set::size));
        LOGGER.info("Unique element count : " + size);
        System.out.println();
    }

    /**
     * Create list of Integer and return
     */
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

    /**
     * Create list of String and return
     */
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
