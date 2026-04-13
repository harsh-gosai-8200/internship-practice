package com.java.java_8_features.miscellineous_features;

import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CompletableFuturePractice {

    private static final Logger logger = Logger.getLogger(CompletableFuturePractice.class.getName());

    public static void main(String[] args) {

        logger.info("Main method started");

        Scanner scanner = new Scanner(System.in);

        int num1 = readNumber(scanner, "Enter first number: ");
        int num2 = readNumber(scanner, "Enter second number: ");

        CompletableFuture<Integer> future1 = processFirstNumberAsync(num1);
        CompletableFuture<Integer> future2 = processSecondNumberAsync(num2);

        CompletableFuture<Integer> combinedFuture = combineResultsAsync(future1, future2);

        handleFinalResult(combinedFuture);

        scanner.close();
        logger.info("Main method finished");
    }

    /**
     * Reads an integer value from the user using Scanner.
     *
     * @param scanner Scanner object for reading input
     * @param message Message to show to the user
     * @return user-provided integer value
     */
    private static int readNumber(Scanner scanner, String message) {
        System.out.print(message);
        return scanner.nextInt();
    }

    /**
     * Processes the first number asynchronously.
     * Simulates a long-running operation by adding a delay.
     *
     * @param number input number
     * @return CompletableFuture containing processed result
     */
    private static CompletableFuture<Integer> processFirstNumberAsync(int number) {
        return CompletableFuture.supplyAsync(() -> {
            logger.info("Processing first number asynchronously");
            simulateDelay();
            return number * 2;
        });
    }

    /**
     * Processes the second number asynchronously.
     * Simulates a long-running operation by adding a delay.
     *
     * @param number input number
     * @return CompletableFuture containing processed result
     */
    private static CompletableFuture<Integer> processSecondNumberAsync(int number) {
        return CompletableFuture.supplyAsync(() -> {
            logger.info("Processing second number asynchronously");
            simulateDelay();
            return number * 3;
        });
    }

    /**
     * Combines the results of two asynchronous computations.
     *
     * @param future1 CompletableFuture of first result
     * @param future2 CompletableFuture of second result
     * @return CompletableFuture containing combined result
     */
    private static CompletableFuture<Integer> combineResultsAsync(
            CompletableFuture<Integer> future1,
            CompletableFuture<Integer> future2) {

        return future1.thenCombine(future2, (result1, result2) -> {
            logger.info("Combining async results");
            return result1 + result2;
        });
    }

    /**
     * Handles the final result of the async computation.
     * Includes success logging and centralized exception handling.
     *
     * @param combinedFuture CompletableFuture with final result
     */
    private static void handleFinalResult(CompletableFuture<Integer> combinedFuture) {

        combinedFuture
                .thenAccept(result -> {
                    logger.info("Final result calculated successfully");
                    System.out.println("Final Result: " + result);
                })
                .exceptionally(ex -> {
                    logger.log(Level.SEVERE, "Error occurred during async processing", ex);
                    return null;
                })
                .join(); // wait for completion
    }

    /**
     * Simulates a delay to represent a long-running task
     * such as a database call or external API invocation.
     */
    private static void simulateDelay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            logger.log(Level.WARNING, "Thread interrupted", e);
            Thread.currentThread().interrupt();
        }
    }
}

