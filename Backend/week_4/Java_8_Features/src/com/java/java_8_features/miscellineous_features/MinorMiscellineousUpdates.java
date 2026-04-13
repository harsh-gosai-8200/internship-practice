package com.java.java_8_features.miscellineous_features;

import java.util.*;
import java.util.logging.Logger;

public class MinorMiscellineousUpdates {

    private static final Logger logger =
            Logger.getLogger(MinorMiscellineousUpdates.class.getName());

    public static void main(String[] args) {
        logger.info("Entering main method..");
        List<String> names = createList();

        demonstrateListMethods(names);
        demonstrateIterator(names);
        demonstrateIterable(names);
        demonstrateCollection(names);
        demonstrateComparator();
        demonstrateArrays();
        demonstrateStringJoin();
        demonstrateMathMethods();
        demonstrateNumberMethods();
        demonstrateBooleanMethods();
        demonstrateObjectsMethods();
    }

    /**
     * Creating static list
     * @return list with static values
     */
    private static List<String> createList() {
        List<String> list = new ArrayList<>();
        list.add("Rahul");
        list.add("Anita");
        list.add("Vikram");
        list.add("Neha");
        list.add(null);
        return list;
    }

    /**
     * Demonstrating List replaceAll() and sort()
     * @param list
     */
    private static void demonstrateListMethods(List<String> list) {
        logger.info("Entering demonstrateListMethods method..");
        System.out.println("\nDemonstrating List replaceAll & sort..");

        list.replaceAll(value -> value == null ? "Unknown" : value.toUpperCase());
        list.sort(Comparator.naturalOrder());
        System.out.println(list);
    }

    /**
     * Demonstrating Iterator forEachRemaining()
     * @param list
     */
    private static void demonstrateIterator(List<String> list) {
        logger.info("Entering demonstrateIterator method..");
        System.out.println("\nDemonstrating Iterator forEachRemaining..");

        Iterator<String> iterator = list.iterator();
        iterator.forEachRemaining(System.out::println);
    }

    /**
     * Demonstrating Iterable methods forEach() and spliterator()
     * @param list
     */
    private static void demonstrateIterable(List<String> list) {
        logger.info("Entering demonstrateIterable method..");
        System.out.println("\nDemonstrating Iterable forEach & spliterator..");

        list.forEach(System.out::println);

        System.out.println();
        Spliterator<String> spliterator = list.spliterator();
        spliterator.forEachRemaining(System.out::println);
    }

    /**
     * Demonstrating Collection stream(), parallelStream() and removeIf()
     * @param list
     */
    private static void demonstrateCollection(List<String> list) {
        logger.info("Entering demonstrateCollection method..");
        System.out.println("\nDemonstrating Collection methods..");

        list.stream()
                .filter(name -> name.startsWith("R"))
                .forEach(System.out::println);

        System.out.println();
        list.parallelStream()
                .forEach(System.out::println);

        System.out.println();
        list.removeIf(name -> name.equalsIgnoreCase("UNKNOWN"));
        System.out.println("After removeIf : " + list);
    }

    /**
     * Demonstrating Comparator utility methods
     */
    private static void demonstrateComparator() {
        logger.info("Entering demonstrateComparator method..");
        System.out.println("\nDemonstrating Comparator methods..");

        List<String> list = Arrays.asList("Banana", "Apple", null, "Mango");

        list.stream()
                .sorted(Comparator.nullsLast(Comparator.naturalOrder()))
                .forEach(System.out::println);

        System.out.println();
        list.stream()
                .filter(Objects::nonNull)
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);
    }

    /**
     * Demonstrating Arrays utility methods
     */
    private static void demonstrateArrays() {
        logger.info("Entering demonstrateArrays method..");
        System.out.println("\nDemonstrating Arrays methods..");

        int[] arr = new int[5];

        Arrays.setAll(arr, i -> i * 2);
        System.out.println("setAll : " + Arrays.toString(arr));

        Arrays.parallelSetAll(arr, i -> i + 1);
        System.out.println("parallelSetAll : " + Arrays.toString(arr));

        Arrays.parallelSort(arr);
        System.out.println("parallelSort : " + Arrays.toString(arr));

        Arrays.parallelPrefix(arr, Integer::sum);
        System.out.println("parallelPrefix : " + Arrays.toString(arr));
    }

    /**
     * Demonstrating String join()
     */
    private static void demonstrateStringJoin() {
        logger.info("Entering demonstrateStringJoin method..");
        System.out.println("\nDemonstrating String join..");

        String result = String.join(", ", "Java", "Spring", "Hibernate");
        System.out.println(result);
    }

    /**
     * Demonstrating Math utility methods
     */
    private static void demonstrateMathMethods() {
        logger.info("Entering demonstrateMathMethods method..");
        System.out.println("\nDemonstrating Math methods..");

        System.out.println("addExact : " + Math.addExact(10, 20));
        System.out.println("subtractExact : " + Math.subtractExact(20, 10));
        System.out.println("multiplyExact : " + Math.multiplyExact(5, 4));
        System.out.println("incrementExact : " + Math.incrementExact(10));
        System.out.println("decrementExact : " + Math.decrementExact(10));
        System.out.println("negateExact : " + Math.negateExact(10));
        System.out.println("toIntExact : " + Math.toIntExact(100L));
        System.out.println("floorDiv : " + Math.floorDiv(7, 3));
        System.out.println("floorMod : " + Math.floorMod(7, 3));
        System.out.println("nextDown : " + Math.nextDown(10.5));
    }

    /**
     * Demonstrating Number class static methods
     */
    private static void demonstrateNumberMethods() {
        logger.info("Entering demonstrateNumberMethods method..");
        System.out.println("\nDemonstrating Number methods..");

        System.out.println("Integer sum : " + Integer.sum(10, 20));
        System.out.println("Integer min : " + Integer.min(10, 20));
        System.out.println("Integer max : " + Integer.max(10, 20));

        System.out.println("Double sum : " + Double.sum(1.5, 2.5));
        System.out.println("Double min : " + Double.min(1.5, 2.5));
        System.out.println("Double max : " + Double.max(1.5, 2.5));
    }

    /**
     * Demonstrating Boolean logical methods
     */
    private static void demonstrateBooleanMethods() {
        logger.info("Entering demonstrateBooleanMethods method..");
        System.out.println("\nDemonstrating Boolean methods..");

        System.out.println("logicalAnd : " +
                Boolean.logicalAnd(true, false));
        System.out.println("logicalOr : " +
                Boolean.logicalOr(true, false));
        System.out.println("logicalXor : " +
                Boolean.logicalXor(true, false));
    }

    /**
     * Demonstrating Objects utility methods
     */
    private static void demonstrateObjectsMethods() {
        logger.info("Entering demonstrateObjectsMethods method..");
        System.out.println("\nDemonstrating Objects methods..");

        String value = null;

        System.out.println("isNull : " + Objects.isNull(value));
        System.out.println("nonNull : " + Objects.nonNull("Java"));
    }
}

