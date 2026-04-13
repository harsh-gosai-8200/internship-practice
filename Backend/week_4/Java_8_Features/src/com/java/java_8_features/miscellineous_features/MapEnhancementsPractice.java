package com.java.java_8_features.miscellineous_features;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class MapEnhancementsPractice {

    private static final Logger logger = Logger.getLogger(MapEnhancementsPractice.class.getName());

    public static void main(String[] args) {
        logger.info("Entering main method..");
        Map<Integer, String> map = createMap();
        printMap("Initial map : ", map);

        iterateUsingForEach(map);
        sortByKey(map);
        sortByValue(map);
        demonstrateGetOrDefault(map);
        demonstrateComputeIfAbsent(map);
        demonstrateComputeIfPresent(map);
        demonstrateCompute(map);
        demonstrateRemove(map);
        demonstrateReplace(map);
        demonstrateReplaceAll(map);
    }

    /**
     * Demonstrating replaceAll..
     * get the map and perform operation the print result
     * @param map
     */
    private static void demonstrateReplaceAll(Map<Integer, String> map) {
        logger.info("Entering demonstrateReplaceAll method..");
        System.out.println("\nDemonstrating replaceAll..");
        map.replaceAll((key, value)->value+"[Emp]");
        printMap("After replacing all : " , map);
    }

    /**
     * Demonstrating replace..
     * get the map and perform operation the print result
     * @param map
     */
    private static void demonstrateReplace(Map<Integer, String> map) {
        logger.info("Entering demonstrateReplace method..");
        System.out.println("\nDemonstrating replace..");
        map.replace(101, "Rahul Jain");
        printMap("After replacing : " , map);
    }

    /**
     * Demonstrating remove..
     * get the map and perform operation the print result
     * @param map
     */
    private static void demonstrateRemove(Map<Integer, String> map) {
        logger.info("Entering demonstrateRemove method..");
        System.out.println("\nDemonstrating remove..");
        boolean removed = map.remove(104,"Neha");
        System.out.println("Removed 104-Neha: " + removed);
        printMap("After removing : " , map);
    }

    /**
     * Demonstrating compute..
     * get the map and perform operation the print result
     * @param map
     */
    private static void demonstrateCompute(Map<Integer, String> map) {
        logger.info("Entering demonstrateCompute method..");
        System.out.println("\nDemonstrating compute..");
        map.compute(103,(key, value)->value==null? "new Employee":value.toUpperCase());
        printMap("After computing : " , map);
    }

    /**
     * Demonstrating compute if present..
     * get the map and perform operation the print result
     * @param map
     */
    private static void demonstrateComputeIfPresent(Map<Integer, String> map) {
        logger.info("Entering demonstrateComputeIfPresent method..");
        System.out.println("\nDemonstrating compute if present..");
        map.computeIfPresent(105,(k,v)->v="Jeet");
        printMap("After computin : " , map);
    }

    /**
     * Demonstrating compute if absent..
     * get the map and perform operation the print result
     * @param map
     */
    private static void demonstrateComputeIfAbsent(Map<Integer, String> map) {
        logger.info("Entering demonstrateComputeIfAbsent method..");
        System.out.println("\nDemonstrating compute if absent..");
        map.computeIfAbsent(105,key->"Gosai");
        printMap("After computing : ", map);
    }

    /**
     * Demonstaring get or default..
     * get the map and perform operation the print result
     * @param map
     */
    private static void demonstrateGetOrDefault(Map<Integer, String> map) {
        logger.info("Entering demonstrateGetOrDefault method..");
        System.out.println("\nDemonstaring get or default..");
        String value =map.getOrDefault(105, "Harsh");
        System.out.println("Employee 105 : "+ value);
        printMap("After computing :", map);
    }

    /**
     * Sorting map by values
     * get the map and perform operation the print result
     * @param map
     */
    private static void sortByValue(Map<Integer, String> map) {
        logger.info("Entering sortByValue method..");
        System.out.println("\nSorting map by values");
        map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(System.out::println);
    }

    /**
     * Sorting map by keys..
     * get the map and perform operation the print result
     * @param map
     */
    private static void sortByKey(Map<Integer, String> map) {
        logger.info("Entering sortByKey method..");
        System.out.println("\nSorting map by keys..");
        map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(System.out::println);
    }

    /**
     * Iterating map using forEach..
     * get the map and perform operation the print result
     * @param map
     */
    private static void iterateUsingForEach(Map<Integer, String> map) {
        logger.info("Entering iterateUsingForEach method..");
        System.out.println("\nIterating map using forEach..");
        map.forEach((key,value)-> System.out.println("key:"+key+" value:"+value));
    }

    /**
     * create map with static value
     * @return simple static valued map
     */
    private static Map<Integer, String> createMap(){
        Map<Integer, String> map = new HashMap<>();
        map.put(101, "Rahul");
        map.put(102, "Anita");
        map.put(103, "Vikram");
        map.put(104, "Neha");
        return map;
    }

    /**
     * printing the given map with its title
     * @param title
     * @param map
     */
    public static void printMap(String title, Map<Integer, String> map){
        System.out.println(title);
        map.forEach((k,v)-> System.out.println(k+"->"+v));
    }

}
